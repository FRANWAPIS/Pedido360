
package com.Pedido360.pedidos;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RabbitMqTestController {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqTestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/rabbitmq")
    public String publicarEvento() {
        String evento = """
            {
              "event": "order.created",
              "orderId": 1001,
              "mensaje": "Prueba de RabbitMQ"
            }
            """;

        rabbitTemplate.convertAndSend(
            "tienda.events",
            "order.created",
            evento
        );

        return "Evento order.created enviado a RabbitMQ";
    }
}
