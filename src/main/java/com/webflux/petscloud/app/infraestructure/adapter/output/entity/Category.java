package com.webflux.petscloud.app.infraestructure.adapter.output.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@DynamoDBDocument
public class Category {
	@DynamoDBAttribute
	private String id;
	@DynamoDBAttribute
	private String name;
	public Category() {}
}
