package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.Feedback;
import org.example.eventmanagement.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

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
