package com.pdfcampus.pdfcampus.Controller.SignupController;


import com.pdfcampus.pdfcampus.dto.SignupDto;
import com.pdfcampus.pdfcampus.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignupController {
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<Object> execSignup(@RequestBody SignupDto signupDto){
        boolean isUserIdDuplicated = signupService.isUserIdDuplicated(signupDto.getUserId()); // 아이디 중복검사를 실행하는 서비스 호출

        if (!isUserIdDuplicated) { // 사용가능한 아이디
            signupService.joinUser(signupDto); // 회원정보를 데이터베이스에 저장하는 서비스 호출

            SignupResponse response = new SignupResponse(signupDto.getAccessToken(), signupDto.getRefreshToken(), signupDto.getUserId());
            return ResponseEntity.ok().body(response);
        }
        else { // 중복되는 아이디
            ApiError error = new ApiError("E400", "중복되는 아이디");
            return ResponseEntity.badRequest().body(new SignupResponse(null, null, null, error));
        }
    }
}


