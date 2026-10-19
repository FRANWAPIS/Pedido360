package com.Pedido360.auditoria;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class AuditoriaListener {
    private final AuditoriaRepository repo;

    public AuditoriaListener(AuditoriaRepository repo) { this.repo = repo; }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${app.rabbit.queue}", durable = "true"),
        exchange = @Exchange(value = "${app.rabbit.exchange}", type = "topic", durable = "true"),
        key = "#"))
    public void recibir(Message msg) {
        String cuerpo = new String(msg.getBody(), StandardCharsets.UTF_8);
        String rk = msg.getMessageProperties().getReceivedRoutingKey();
        repo.save(new Auditoria(cuerpo, rk));
        System.out.println("AUDITORIA guardó evento: " + cuerpo);
    }
}