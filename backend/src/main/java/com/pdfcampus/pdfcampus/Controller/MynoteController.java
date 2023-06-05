package com.pdfcampus.pdfcampus.Controller;


import com.pdfcampus.pdfcampus.dto.MynoteAssignDto;
import com.pdfcampus.pdfcampus.dto.MynoteDto;
import com.pdfcampus.pdfcampus.service.DetailService;
import com.pdfcampus.pdfcampus.service.MynoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
public class MynoteController {

    private final MynoteService mynoteService;
    private final DetailService detailService;
    @Autowired
    public MynoteController(MynoteService mynoteService, DetailService detailService) {
        this.mynoteService = mynoteService;
        this.detailService = detailService;
    }

    @PostMapping("/mynote/assign")
    public ResponseEntity<Map<String, Object>> mynoteAssign(@RequestBody MynoteAssignDto mynoteAssignDto)
    {
        try {
            List<MynoteDto> mynoteDataList = mynoteService.getMynoteByUserId(userId);

            List<Map<String, Object>> noteList = new ArrayList<>();
            for (MynoteDto mynoteDto : mynoteDataList) {
                Map<String, Object> noteMap = new LinkedHashMap<>();
                noteMap.put("noteId", mynoteDto.getNoteId());
                noteMap.put("noteTitle", mynoteDto.getNoteTitle());

                noteMap.put("createdAt", mynoteDto.getCreatedAt());
                noteMap.put("modifiedAt", mynoteDto.getModifiedAt());
                noteMap.put("isSale", mynoteDto.isSale());

                Map<String, Object> bookInfoMap = new LinkedHashMap<>();
                bookInfoMap.put("author", mynoteDto.getBookInfo().getAuthor());
                bookInfoMap.put("publisher", mynoteDto.getBookInfo().getPublisher());
                bookInfoMap.put("publicationYear", mynoteDto.getBookInfo().getPublicationYear());
                bookInfoMap.put("bookCover", mynoteDto.getBookInfo().getBookCover());

                noteMap.put("bookInfo", bookInfoMap);
                noteList.add(noteMap);
            }

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("data", Map.of("noteList", noteList));

            Map<String, String> apiStatus = new LinkedHashMap<>();
            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            responseBody.put("apiStatus", apiStatus);

            // HTTP 상태 코드 200 OK와 함께 responseBody 맵 객체를 반환함
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.putIfAbsent("data", Map.of("noteList", List.of()));

            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", e.getMessage());
            apiStatus.put("errorCode", "N400");
            responseBody.put("apiStatus", apiStatus);

            // HTTP 상태 코드 400 Bad Request와 함께 responseBody 맵 객체를 반환함
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @DeleteMapping("/mynote/delete")
    public ResponseEntity<Map<String, Object>> deleteData(@RequestParam("userId") String userId, @RequestParam("noteId") String noteId) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> apiStatus = new HashMap<>();
        try {
            //이미 판매중인 노트는 판매불가
            if(detailService.isBought(userId, noteId)) {
                apiStatus.put("errorMessage", "Note already on sale cannot be deleted");
                apiStatus.put("errorCode", "E400");
                errorResponse.put("apiStatus", apiStatus);
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }

            // 데이터 삭제
            boolean deleted = mynoteService.deleteNote(userId, noteId);

            //aws s3 8282note 버킷에서 데이터 삭제

            if (deleted) {
                //정상적으로 삭제
                apiStatus.put("errorMessage", "Note content deletion completed");
                apiStatus.put("errorCode", "N200");
                errorResponse.put("apiStatus", apiStatus);
                return new ResponseEntity<>(errorResponse, HttpStatus.OK);
            } else {
                // 삭제할 데이터가 없는 경우
                apiStatus.put("errorMessage", "Data not found");
                apiStatus.put("errorCode", "E500");
                errorResponse.put("apiStatus", apiStatus);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            apiStatus.put("errorMessage", e.getMessage());
            apiStatus.put("errorCode", "E500");
            errorResponse.put("apiStatus", apiStatus);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mynote/{userId}")
    public ResponseEntity<Map<String, Object>> getMyNoteList(@PathVariable String userId) {
        try {
            List<MynoteDto> mynoteDataList = mynoteService.getMynoteByUserId(userId);

            List<Map<String, Object>> noteList = new ArrayList<>();
            for (MynoteDto mynoteDto : mynoteDataList) {
                Map<String, Object> noteMap = new LinkedHashMap<>();
                noteMap.put("noteId", mynoteDto.getNoteId());
                noteMap.put("noteTitle", mynoteDto.getNoteTitle());
                noteMap.put("createdAt", mynoteDto.getCreatedAt());
                noteMap.put("modifiedAt", mynoteDto.getModifiedAt());
                noteMap.put("isSale", mynoteDto.isSale());

                Map<String, Object> bookInfoMap = new LinkedHashMap<>();
                bookInfoMap.put("author", mynoteDto.getBookInfo().getAuthor());
                bookInfoMap.put("publisher", mynoteDto.getBookInfo().getPublisher());
                bookInfoMap.put("publicationYear", mynoteDto.getBookInfo().getPublicationYear());
                bookInfoMap.put("bookCover", mynoteDto.getBookInfo().getBookCover());

                noteMap.put("bookInfo", bookInfoMap);
                noteList.add(noteMap);
            }

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("data", Map.of("noteList", noteList));

            Map<String, String> apiStatus = new LinkedHashMap<>();
            apiStatus.put("errorMessage", "");
            apiStatus.put("errorCode", "N200");
            responseBody.put("apiStatus", apiStatus);

            // HTTP 상태 코드 200 OK와 함께 responseBody 맵 객체를 반환함
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.putIfAbsent("data", Map.of("noteList", List.of()));

            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", e.getMessage());
            apiStatus.put("errorCode", "N400");
            responseBody.put("apiStatus", apiStatus);

            // HTTP 상태 코드 400 Bad Request와 함께 responseBody 맵 객체를 반환함
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}
