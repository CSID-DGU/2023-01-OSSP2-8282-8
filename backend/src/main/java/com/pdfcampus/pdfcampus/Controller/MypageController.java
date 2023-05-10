package com.pdfcampus.pdfcampus.Controller;

import com.pdfcampus.pdfcampus.dto.MypageDto;
import com.pdfcampus.pdfcampus.service.MypageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class MypageController {
    private final MypageService mypageService;

    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping("/mypage/{userId}")
    public ResponseEntity<Map<String, Object>> getMypageData(@PathVariable String userId) {
        try {
            MypageDto mypageData = mypageService.getMypageData(userId);
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> responseData = new LinkedHashMap<>();
            Map<String, Object> subscribeInfo = new LinkedHashMap<>();

            if(mypageData.isSubscribed()){ //구독한 사용자에 경우 subscribeInfo 구성
                subscribeInfo.put("productName", mypageData.getProductName());
                subscribeInfo.put("subscribeDate", mypageData.getSubscribeDate());
            }

            responseData.put("username", mypageData.getUsername());
            responseData.put("isSubscribed", mypageData.isSubscribed());
            responseData.put("joinedDate", mypageData.getJoinedDate());
            responseData.put("subscribedInfo", subscribeInfo);

            response.put("data", responseData);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 기타 예외가 발생한 경우
            Map<String, Object> responseBody = new LinkedHashMap<>();

            // 500
            Map<String, String> apiStatus = new HashMap<>();
            apiStatus.put("errorMessage", "서버 오류가 발생했습니다.");
            apiStatus.put("errorCode", "N500");
            responseBody.put("apiStatus", apiStatus);

            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}