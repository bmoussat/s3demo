package com.storage.s3demo.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.Md5Utils;
import com.storage.s3demo.localstack.EndpointRegistrar;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class S3ObjectRepository {

	@Value("${localaws.region}")
	private String region;

  @Bean
  public AmazonS3 localstackS3Client() {
    return AmazonS3ClientBuilder.standard()
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(EndpointRegistrar.getS3Endpoint(), region))
      .withClientConfiguration(new ClientConfiguration().withRequestTimeout(50000))
      .disableChunkedEncoding()
      .withPathStyleAccessEnabled(true)
      .build();
  }
  
  @SneakyThrows
  public void save(final String bucketName, final String objectKey, final byte[] objectContent, final String objectContentType) { 
    try (final InputStream byteArrayInputStream = new ByteArrayInputStream(objectContent)) {
      final ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentLength(objectContent.length);
      objectMetadata.setContentType(objectContentType);
      localstackS3Client().putObject(new PutObjectRequest(bucketName, objectKey, byteArrayInputStream, objectMetadata));
    }
  }
  
  @SneakyThrows
  public List<String> getFilesNames(final String bucketName) {
  	  ListObjectsV2Result result = localstackS3Client().listObjectsV2(bucketName);
  	  List<S3ObjectSummary> objects = result.getObjectSummaries();
  	  return     objects.stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
  }
}
