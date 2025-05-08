package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.service.EventpostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    EventpostService eventpostService;
    @GetMapping("/dashboard")
    public ResponseEntity<Page<Eventpost>> getUserDashboard(
            @RequestParam(required = false) Long userid,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String venue,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String fare,
            @RequestParam(required = false) String searchQuery,
            @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        Page<Eventpost> events = eventpostService.getAllEvents(
                name, description, venue, image,
                category, artist, fare,
                searchQuery, userid,
                pageable  // Added pageable parameter
        );
        return ResponseEntity.ok(events);
    }
    @GetMapping("/artist")
    public ResponseEntity<List<String>> getArtist() {
        List<String> artist = eventpostService.getAllArtist();
        if (artist.size() > 0) {
            return ResponseEntity.ok(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = eventpostService.getAllCategory();
        if(categories.size() > 0) {
            return ResponseEntity.ok(categories);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/booking/{id}")
    public ResponseEntity<List<Eventpost>> updateBooking(@PathVariable Long id) {
        try{
            List<Eventpost>  postlist=eventpostService.updateCount(id);
            return ResponseEntity.ok(postlist);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/trending")
    public ResponseEntity<List<Eventpost>> getTrending() {
        try{
            return ResponseEntity.ok(eventpostService.trendingEvents());
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
