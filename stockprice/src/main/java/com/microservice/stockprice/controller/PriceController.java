package com.microservice.stockprice.controller;

import com.microservice.stockprice.constants.RabbitMQConstants;
import com.microservice.stockprice.dto.PriceDTO;
import com.microservice.stockprice.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price")
@Log4j2
@RequiredArgsConstructor
public class PriceController {

    private final RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity changePrice(@RequestBody PriceDTO priceDTO) {
        log.info(priceDTO.toString());
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_PRICE, priceDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
