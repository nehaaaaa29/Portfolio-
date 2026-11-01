package com.example.Portfolio.repository;

import com.example.Portfolio.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllByOrderByDisplayOrderAsc();
}

