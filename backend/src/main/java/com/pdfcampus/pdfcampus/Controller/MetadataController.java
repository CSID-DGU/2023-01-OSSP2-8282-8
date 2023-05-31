package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.PageDto;
import com.pdfcampus.pdfcampus.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note/metadata")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookMetadata(@PathVariable Integer bookId) {
        List<PageDto> pageDtoList = metadataService.getMetadataByBookId(bookId);

        if (pageDtoList == null || pageDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pageDtoList);
    }
}

