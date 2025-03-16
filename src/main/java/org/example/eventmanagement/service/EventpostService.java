package org.example.eventmanagement.service;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.repository.EventpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventpostService {
  @Autowired
  private EventpostRepository eventpostRepository;
  public Eventpost addEventpost(Eventpost eventpost) {
    List<Eventpost> eventposts = eventpostRepository.findAll();
    for (Eventpost existingEvent : eventposts) {
      if (existingEvent.getArtist().equals(eventpost.getArtist()) &&
              existingEvent.getCategory().equals(eventpost.getCategory()) &&
              existingEvent.getDescription().equals(eventpost.getDescription()) &&
              existingEvent.getName().equals(eventpost.getName()) &&
              existingEvent.getVenue().equals(eventpost.getVenue()) &&
              existingEvent.getFare().equals(eventpost.getFare())) {
        return null; // Immediately exits the function
      }
    }
// If no match found, proceed to save the new record
    return eventpostRepository.save(eventpost);

  }
  public Page<Eventpost> getEventpostByUserid(
          String name, String description, String venue, String image,
          String category, String artist, String fare, long userid, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return eventpostRepository.findByDynamicFilters(
            userid, name, description, venue, image, category, artist, fare, null, pageable
    );
  }
  public Page<Eventpost> getAllEvents(
          String name, String description, String venue, String image,
          String category, String artist, String fare,
          String searchQuery, // Add searchQuery parameter
          Long userid, int page, int size) {
    Pageable pageable;
    if (size == -1) {
      pageable = Pageable.unpaged(); // Fetch all events (no pagination)
    } else {
      pageable = PageRequest.of(page, size); // Fetch paginated events
    }
    return eventpostRepository.findByDynamicFilters(
            userid, name, description, venue, image, category, artist, fare, searchQuery, pageable
    );
  }

  public List<String> getAllArtist() {
    return eventpostRepository.findArtists();
  }

  public List<String> getAllCategory() {
    return eventpostRepository.findCategories();
  }
  }


