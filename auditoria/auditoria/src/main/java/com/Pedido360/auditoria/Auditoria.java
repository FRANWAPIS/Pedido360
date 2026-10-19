package com.Pedido360.auditoria;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 4000)
    private String evento;
    private String cola;
    private LocalDateTime fecha;

    public Auditoria() {}
    public Auditoria(String evento, String cola) {
        this.evento = evento;
        this.cola = cola;
        this.fecha = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public String getEvento() { return evento; }
    public String getCola() { return cola; }
    public LocalDateTime getFecha() { return fecha; }
}