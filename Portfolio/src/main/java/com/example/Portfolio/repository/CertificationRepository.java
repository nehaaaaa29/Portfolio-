package com.example.Portfolio.repository;
import com.example.Portfolio.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface CertificationRepository extends JpaRepository<Certification, Long> {
        List<Certification> findAllByOrderByDisplayOrderAsc();
    }

