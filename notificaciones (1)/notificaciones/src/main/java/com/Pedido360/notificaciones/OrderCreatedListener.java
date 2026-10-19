package com.Pedido360.notificaciones;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {
    private final EmailService emailService;

    public OrderCreatedListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "notificaciones.order.created")
    public void recibir(String mensaje) {
        System.out.println("Evento recibido en notificaciones: " + mensaje);
        emailService.enviar("friveraff.18@gmail.com", "Pedido360 - Orden creada",
            "Se creo una nueva orden. Detalle: " + mensaje);
    }
}