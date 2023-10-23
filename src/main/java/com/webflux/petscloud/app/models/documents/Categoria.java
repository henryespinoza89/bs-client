package com.webflux.petscloud.app.models.documents;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@DynamoDBDocument
public class Categoria {
	@DynamoDBAttribute
	private String id;
	@DynamoDBAttribute
	private String nombre;
}
