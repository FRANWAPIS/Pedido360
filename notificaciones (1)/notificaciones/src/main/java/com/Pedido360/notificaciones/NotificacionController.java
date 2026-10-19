package com.Pedido360.notificaciones;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final EmailService emailService;

    public NotificacionController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar")
    public String enviar(@RequestParam String para) {
        try {
            emailService.enviar(para, "Pedido360 - Prueba",
                "Hola, esta es una notificacion de prueba de Pedido360.");
            return "Correo enviado a " + para;
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}