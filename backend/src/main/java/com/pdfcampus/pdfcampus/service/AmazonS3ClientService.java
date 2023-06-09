package com.pdfcampus.pdfcampus.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.pdfcampus.pdfcampus.entity.Book;
import com.pdfcampus.pdfcampus.entity.Note;
import com.pdfcampus.pdfcampus.repository.DetailBookRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@Service
public class AmazonS3ClientService {
    private AmazonS3 s3client;
    private PdfMetadataService pdfMetadataService;
    private DetailBookRepository detailBookRepository;

    @Autowired
    public AmazonS3ClientService(AmazonS3 s3client, PdfMetadataService pdfMetadataService, DetailBookRepository detailBookRepository) {
        this.s3client = s3client;
        this.pdfMetadataService = pdfMetadataService;
        this.detailBookRepository = detailBookRepository;
    }

    public void downloadAndProcessPdf(String bucketName, String keyName) {
        String bookId = keyName.replace(".pdf", "");
        Book book = detailBookRepository.findByBid(Integer.valueOf(bookId)).orElseThrow(() -> new NullPointerException("Not found"));

        S3Object s3object = s3client.getObject(bucketName, keyName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        byte[] content;
        try {
            Integer bid = book.getBid();
            content = IOUtils.toByteArray(inputStream);
            pdfMetadataService.processPDF(content, bid);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download content from S3", e);
        }
    }

    public void deleteS3Note(String bucketName, String nid) {
        // 객체 삭제

        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, "Book GNOTE1.png");
        s3client.deleteObject(deleteObjectRequest);
    }

    public void copyS3Object(String sourceBucketName, String sourceObjectKey, String destinationBucketName) {
        // 원본 객체의 키를 대상 객체의 키로 사용
        String destinationObjectKey = sourceObjectKey;

        // 객체 복사
        CopyObjectRequest copyObjectRequest = new CopyObjectRequest(sourceBucketName, sourceObjectKey, destinationBucketName, destinationObjectKey);
        CopyObjectResult copyObjectResult = s3client.copyObject(copyObjectRequest);

        // 복사 작업 결과 확인
        System.out.println("Object copied successfully. ETag: " + copyObjectResult.getETag());
    }
}