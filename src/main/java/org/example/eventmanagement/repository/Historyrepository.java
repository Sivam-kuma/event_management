package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Historyrepository extends JpaRepository<History, Integer> {
  Page<History> findByUserid(Long userid, Pageable pageable);
}
