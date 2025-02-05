package org.example.eventmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userid;
    private String firstname;
    private String lastname;
    private String venue;
    private String Status;
    private Date date;
    private String eventName;
    private String image;
    public History() {}

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getVenue() {
        return venue;
    }

    public String getStatus() {
        return Status;
    }

    public Date getDate() {
        return date;
    }

    public String getEventName() {
        return eventName;
    }

    public String getImage() {
        return image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
