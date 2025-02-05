package org.example.eventmanagement.service;

import org.example.eventmanagement.entity.Feedback;
import org.example.eventmanagement.repository.Feedbackrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
  @Autowired
    Feedbackrepository feedbackrepository;

  public Feedback save(Feedback feedback) {
      return feedbackrepository.save(feedback);
  }
}
