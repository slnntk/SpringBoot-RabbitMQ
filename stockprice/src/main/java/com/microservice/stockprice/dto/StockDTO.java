package com.microservice.stockprice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockDTO implements Serializable {
    private String productCode;
    private int quantity;
}
