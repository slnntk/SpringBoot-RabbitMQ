package com.microservice.stockprice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceDTO implements Serializable {
    private String productCode;
    private double price;
}
