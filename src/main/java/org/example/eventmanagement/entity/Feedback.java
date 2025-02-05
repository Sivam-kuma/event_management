package org.example.eventmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long id;
    int numberOfStars;
    String feedback;
    Long userid;

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public String getFeedback() {
        return feedback;
    }

    public Long getUserid() {
        return userid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
