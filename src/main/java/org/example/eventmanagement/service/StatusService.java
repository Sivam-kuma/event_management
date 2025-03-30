package org.example.eventmanagement.service;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.entity.Status;
import org.example.eventmanagement.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public List<Eventpost> knowStatus(long uid) {
        return statusRepository.findBookedEvents(uid);
    }

    public Status makeStatus(Status status) {
        return statusRepository.save(status);
    }
}
