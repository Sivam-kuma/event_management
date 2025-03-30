package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.entity.Status;
import org.example.eventmanagement.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // ✅ Change to RestController
@RequestMapping("/api/user") // ✅ Apply base path to all endpoints
public class StatusController {

    @Autowired
    private StatusService statusService;

    // ✅ Fix @PathVariable usage
    @GetMapping("/status/{uid}")
    public ResponseEntity<?> statusShow(@PathVariable long uid) {
        try {
            List<Eventpost> eventpostList = statusService.knowStatus(uid);
            if (eventpostList.isEmpty()) {
                return ResponseEntity.noContent().build(); // ✅ Return No Content if no events found
            }
            return ResponseEntity.ok(eventpostList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving events: " + e.getMessage());
        }
    }

    // ✅ Fix incorrect URL mapping
    @PostMapping("/statusUpload")
    public ResponseEntity<?> statusUpload(@RequestBody Status status) {
        try {
            Status savedStatus = statusService.makeStatus(status);
            return ResponseEntity.ok(savedStatus);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving status: " + e.getMessage());
        }
    }
}
