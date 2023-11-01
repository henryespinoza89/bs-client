package com.webflux.petscloud.app.infraestructure.adapter.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DynamoDBConfiguration {
  @Bean
  public DynamoDBMapper dynamoDBMapper() {
    return new DynamoDBMapper(buildAmazonDynamoDB());
  }
  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return buildAmazonDynamoDB();
  }
  private AmazonDynamoDB buildAmazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
        new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-1.amazonaws.com", "us-east-1")
    ).withCredentials(
        new AWSStaticCredentialsProvider(
            new BasicAWSCredentials("AKIAZ3KTE7FMLX5XLFU5", "52JqOlmd2mjfJlyaV1OPzMajNXfG9qnUqj5urDZZ")
        )
    ).build();
  }
}
