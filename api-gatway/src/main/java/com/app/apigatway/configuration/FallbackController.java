package com.app.apigatway.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cb")
public class FallbackController {

    @GetMapping("/inventoryServiceFallBack")
    public String inventoryServiceFallBack(Exception ex) {
        return "Le microservice inventory n'est pas disponible. Réessayez ultérieurement.";
    }

    @GetMapping("/pharmacyServiceFallBack")
    public String pharmacyServiceFallBack(Exception ex) {
        return "Le microservice pharmacy n'est pas disponible. Réessayez ultérieurement.";
    }

    @GetMapping("/rendezVousServiceFallBack")
    public String rendezVousServiceFallBack(Exception ex) {
        return "Le microservice rendez-vous n'est pas disponible. Réessayez ultérieurement.";
    }

    @GetMapping("/reportServiceFallBack")
    public String reportServiceFallBack(Exception ex) {
        return "Le microservice report n'est pas disponible. Réessayez ultérieurement.";
    }

    @GetMapping("/notificationServiceFallBack")
    public String notificationServiceFallBack(Exception ex) {
        return "Le microservice notifiction n'est pas disponible. Réessayez ultérieurement.";
    }
}
