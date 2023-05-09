package com.pdfcampus.pdfcampus.service;

import com.pdfcampus.pdfcampus.dto.MypageDto;
import com.pdfcampus.pdfcampus.entity.User;
import com.pdfcampus.pdfcampus.repository.MypageRepository;
import org.springframework.stereotype.Service;

@Service
public class MypageService {
    private final MypageRepository mypageRepository;
    public MypageService(MypageRepository mypageRepository) {
        this.mypageRepository = mypageRepository;
    }

    public MypageDto getMypageData(String uid) { // 데이터베이스에서 사용자에 대한 데이터를 가져오도록 레포지토리 호출
        Integer uidInt = Integer.parseInt(uid);
        System.out.println(uidInt);
        User user = mypageRepository.findByUid(uidInt);
        return new MypageDto(user.getUid(), user.getUsername(), user.isSubscribed(), user.getProductName(),user.getSubscribeDate(), user.getJoinedDate());
    }
}
