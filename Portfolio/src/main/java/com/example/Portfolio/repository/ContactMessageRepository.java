package com.example.Portfolio.repository;

import com.example.Portfolio.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByStatusOrderByReceivedAtDesc(ContactMessage.MessageStatus status);
    List<ContactMessage> findAllByOrderByReceivedAtDesc();
    long countByStatus(ContactMessage.MessageStatus status);
}
