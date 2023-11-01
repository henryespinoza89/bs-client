package com.webflux.petscloud.app.infraestructure.adapter.output.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@DynamoDBTable(tableName = "product")
public class Product {
	@DynamoDBHashKey
	private String productId;
	@DynamoDBAttribute
	private String name;
	@DynamoDBAttribute
	private Double price;
	@DynamoDBAttribute
	private String createAt;
	@DynamoDBAttribute
	private Category category;
	@DynamoDBAttribute
	private String photo;
	public Product() {}
}
