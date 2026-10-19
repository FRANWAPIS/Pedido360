package com.Pedido360.productos;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {
    private final ProductoRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public OrderCreatedListener(ProductoRepository repo) { this.repo = repo; }

    @RabbitListener(queues = "productos.order.created")
    public void recibir(String mensaje) throws Exception {
        JsonNode json = mapper.readTree(mensaje);
        Long productoId = json.get("productoId").asLong();
        int cantidad = json.get("cantidad").asInt();

        repo.findById(productoId).ifPresent(p -> {
            p.setStock(p.getStock() - cantidad);
            repo.save(p);
            System.out.println("Stock actualizado: " + p.getNombre() + " -> " + p.getStock());
        });
    }
}