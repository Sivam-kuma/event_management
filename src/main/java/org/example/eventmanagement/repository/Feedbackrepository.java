package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Feedbackrepository extends JpaRepository<Feedback, Long> {

}
