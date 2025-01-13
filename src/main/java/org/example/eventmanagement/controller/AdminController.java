package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.repository.EventpostRepository;
import org.example.eventmanagement.service.EventpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    EventpostRepository eventpostRepository;
    @Autowired
    EventpostService eventpostService;
    @GetMapping("/dashboard/{userid}")
    public ResponseEntity<Page<Eventpost>> getDashboard(
            @PathVariable long userid,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String venue,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String fare,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Eventpost> eventposts = eventpostService.getEventpostByUserid(
                name, description, venue, image, category, artist, fare, userid, page, size);
        return ResponseEntity.ok(eventposts);
    }

    @PostMapping("/post")
    public ResponseEntity<Eventpost> postEvent(@RequestBody Eventpost eventpost) {
        Eventpost savedEvent = eventpostService.addEventpost(eventpost);
        return ResponseEntity.ok(savedEvent);
    }

}
