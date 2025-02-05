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
    return eventpostRepository.save(eventpost);
  }
  public Page<Eventpost> getEventpostByUserid(String name, String description, String venue, String image, String category, String artist, String fare, long userid, int page, int size) {
    Pageable pageable= PageRequest.of(page, size);
    return eventpostRepository.findByDynamicFilters(userid, name, description, venue, image, category, artist, fare, pageable);
  }
  public Page<Eventpost> getAllEvents(String name, String description, String venue, String image,
                                      String category, String artist, String fare,
                                     Long userid, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return eventpostRepository.findByDynamicFilters(userid,name, description, venue, image, category, artist, fare, pageable);
  }
  }


