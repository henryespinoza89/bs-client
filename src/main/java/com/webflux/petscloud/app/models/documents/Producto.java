package com.webflux.petscloud.app.models.documents;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@DynamoDBTable(tableName = "product")
public class Producto {
	@DynamoDBHashKey
	private String productId;
	@DynamoDBAttribute
	private String nombre;
	@DynamoDBAttribute
	private Double precio;
	@DynamoDBAttribute
	private String createAt;
	@DynamoDBAttribute
	private Categoria categoria;
	@DynamoDBAttribute
	private String foto;
}
