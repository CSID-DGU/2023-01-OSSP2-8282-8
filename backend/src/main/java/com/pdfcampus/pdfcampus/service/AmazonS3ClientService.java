package com.pdfcampus.pdfcampus.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@Service
public class AmazonS3ClientService {
    private AmazonS3 s3client;

    @Autowired
    public AmazonS3ClientService(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public byte[] downloadFile(String bucketName, String keyName) {
        S3Object s3object = s3client.getObject(bucketName, keyName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        byte[] content;
        try {
            content = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download content from S3", e);
        }
        return content;
    }

}
