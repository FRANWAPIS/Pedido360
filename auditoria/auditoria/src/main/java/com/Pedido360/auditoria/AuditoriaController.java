package com.Pedido360.auditoria;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AuditoriaController {
    private final AuditoriaRepository repo;

    public AuditoriaController(AuditoriaRepository repo) { this.repo = repo; }

    @GetMapping("/auditoria")
    public List<Auditoria> listar() { return repo.findAll(); }
}