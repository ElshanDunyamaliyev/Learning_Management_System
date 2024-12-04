package dev.elshan.lms.controller;

import dev.elshan.lms.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/video")
public class VideoController {

    private final ResourceLoader resourceLoader;

    @GetMapping(produces = "video/mp4")
    public Mono<Resource> getVideo(@RequestHeader("Range") String range) {
        String awsAccessKey = System.getenv("accessKey");
        String awsSecretKey = System.getenv("secretKey");
        AwsCredentials credentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        String regionName = "eu-central-1";
        S3Client s3Client = S3Client
                .builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("lmsdemo1")
                .key("18. L1 Cache Reference Types.mp4")
                .range(range)
                .build();

        ResponseInputStream<GetObjectResponse> s3ObjectStream = s3Client.getObject(getObjectRequest);
        GetObjectResponse response = s3ObjectStream.response();

        System.out.println(range);

        return Mono.fromSupplier(() -> resourceLoader.
                getResource("https://lmsdemo1.s3.eu-central-1.amazonaws.com/18.+L1+Cache+Reference+Types.mp4"));

    }

}
