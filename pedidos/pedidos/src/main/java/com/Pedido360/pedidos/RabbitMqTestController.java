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
              "productoId": 1,
              "cantidad": 2
            }
            """;

        try {
            rabbitTemplate.convertAndSend(
                "tienda.events",
                "order.created",
                evento
            );
            return "Evento order.created enviado a RabbitMQ";
        } catch (Exception e) {
            Throwable causa = e;
            while (causa.getCause() != null) {
                causa = causa.getCause();
            }
            return "ERROR: " + causa.getClass().getSimpleName() + " - " + causa.getMessage();
        }
    }
}
