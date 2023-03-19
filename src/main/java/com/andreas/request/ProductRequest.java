package com.andreas.request;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String id;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Integer quantity;
    private String supplier;
}