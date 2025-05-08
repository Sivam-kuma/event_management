package org.example.eventmanagement.service;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.repository.EventpostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
          String name, String description, String venue,
          String image, String category, String artist,
          String fare, long userid,
          Pageable pageable  // Added
  ) {  // Removed page/size
    return eventpostRepository.findByDynamicFilters(
            userid, name, description, venue,
            image, category, artist, fare,
            null, pageable
    );
  }

  public Page<Eventpost> getAllEvents(
          String name, String description, String venue,
          String image, String category, String artist,
          String fare, String searchQuery, Long userid,
          Pageable pageable  // Added
  ) {
    return eventpostRepository.findByDynamicFilters(
            userid, name, description, venue,
            image, category, artist, fare,
            searchQuery, pageable
    );
  }

  public List<String> getAllArtist() {
    return eventpostRepository.findArtists();
  }

  public List<String> getAllCategory() {
    return eventpostRepository.findCategories();
  }
  public List<Eventpost> updateCount( long id){
    Eventpost eventposts = eventpostRepository.findById(id);
        eventposts.setCount(eventposts.getCount() + 1);
         eventpostRepository.save(eventposts);
        return eventpostRepository.findAll();
  }
   public List<Eventpost> trendingEvents(){
    return eventpostRepository.findTop5ByOrderByCountDesc();
   }
  }


