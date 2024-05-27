package com.microservice.stockprice.controller;

import com.microservice.stockprice.constants.RabbitMQConstants;
import com.microservice.stockprice.dto.StockDTO;
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
@RequestMapping("stock")
@Log4j2
@RequiredArgsConstructor
public class StockController {

    private final RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity changeStock(@RequestBody StockDTO stockDTO) {
        log.info(stockDTO.toString());
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_STOCK, stockDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
