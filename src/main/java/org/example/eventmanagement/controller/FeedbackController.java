package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.Feedback;
import org.example.eventmanagement.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

@GetMapping("/history")
    public ResponseEntity<String> addFeedback(Feedback feedback) {
        try {
             feedbackService.save(feedback);
            return ResponseEntity.ok("Feedback saved successfully");
        }
        catch(Exception e) {
            return ResponseEntity.status(500).body("not updated");
        }

    }
}
