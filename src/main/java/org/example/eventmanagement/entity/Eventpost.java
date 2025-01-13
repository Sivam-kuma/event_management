package org.example.eventmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Eventpost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    long userid;
    String name;
    String description;
    String venue;
    String image;
    String category;
    String artist;
    String fare;

    public Eventpost() {
    }

    public long getUserid() {
        return userid;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getArtist() {
        return artist;
    }

    public String getFare() {
        return fare;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}
