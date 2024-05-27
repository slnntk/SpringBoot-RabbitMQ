package com.microservice.stockprice.connections;

import com.microservice.stockprice.constants.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

@Component
public class RabbitMQQConnection {

    private static final String EXCHANGE_NAME = "amq.direct";

    private final AmqpAdmin amqpAdmin;

    public RabbitMQQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }


    private Queue createQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange createDirectExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding createBinding(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    public void setupQueuesAndBindings() {
        DirectExchange exchange = createDirectExchange();

        Queue queueStock = createQueue(RabbitMQConstants.QUEUE_STOCK);
        Queue queuePrice = createQueue(RabbitMQConstants.QUEUE_PRICE);

        Binding bindingStock = createBinding(queueStock, exchange);
        Binding bindingPrice = createBinding(queuePrice, exchange);

        amqpAdmin.declareExchange(exchange);

        amqpAdmin.declareQueue(queueStock);
        amqpAdmin.declareQueue(queuePrice);

        amqpAdmin.declareBinding(bindingStock);
        amqpAdmin.declareBinding(bindingPrice);
    }

}
