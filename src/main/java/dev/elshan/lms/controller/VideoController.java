package dev.elshan.lms.controller;

import dev.elshan.lms.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/video")
//@EnableAsync
public class VideoController {

    private final VideoRepository repository;

    @GetMapping
//    @Async
    public ResponseEntity<Resource> getVideo() throws IOException {
        String awsAccessKey = System.getenv("accessKey");
        String awsSecretKey = System.getenv("secretKey");
        AwsCredentials credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        String regionName = "eu-central-1";
        S3Client s3Client = S3Client
                .builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        String range = "bytes=" + 500 + "-" + 1000;

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("lmsdemo1")
                .key("18. L1 Cache Reference Types.mp4")
//                .range(range)
                .build();

        GetObjectResponse response = s3Client.getObject(getObjectRequest).response();

        Resource videoResource = new InputStreamResource(s3Client.getObject(getObjectRequest));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "video/mp4");
        headers.add(HttpHeaders.CONTENT_RANGE, "bytes " + 0 + "-" + 10 + "/" + response.contentLength());

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.valueOf("video/mp4"))
                .body(videoResource);
    }
}
