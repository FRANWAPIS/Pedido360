package com.Pedido360.productos;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoRepository repo;

    public ProductoController(ProductoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Producto> listar() { return repo.findAll(); }

    @PostMapping
    public Producto crear(@RequestBody Producto p) { return repo.save(p); }
}