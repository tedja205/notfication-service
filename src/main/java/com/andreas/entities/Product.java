package com.andreas.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "productindex")
@Data
public class Product {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "productName")
    private String productName;

    @Field(type = FieldType.Text, name = "productDescription")
    private String productDescription;

    @Field(type = FieldType.Double, name = "productPrice")
    private BigDecimal productPrice;

    @Field(type = FieldType.Integer, name = "quantity")
    private Integer quantity;

    @Field(type = FieldType.Text, name = "supplier")
    private String supplier;
}

