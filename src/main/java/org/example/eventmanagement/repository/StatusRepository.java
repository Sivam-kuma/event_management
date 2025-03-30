package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.Eventpost;
import org.example.eventmanagement.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("SELECT e FROM Eventpost e JOIN Status s ON e.id = s.id WHERE s.status = true AND s.usersid = :uid")
    List<Eventpost> findBookedEvents(@Param("uid") long uid);

}
