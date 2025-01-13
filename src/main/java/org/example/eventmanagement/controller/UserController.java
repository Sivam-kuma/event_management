package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.service.EventpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    EventpostService eventpostService;
    @GetMapping("/dashboard")
    public ResponseEntity<Page<Eventpost>> getUserDashboard(
            @RequestParam(required = false) Long userid, // Use Long for optional userid
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String venue,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String fare,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Eventpost> events = eventpostService.getAllEvents(name, description, venue, image, category, artist, fare, userid, page, size);
        return ResponseEntity.ok(events);
    }

}
