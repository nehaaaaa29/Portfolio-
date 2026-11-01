package com.example.Portfolio.repository;

import com.example.Portfolio.entity.Profile;
import com.example.Portfolio.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Project> findAllByOrderByDisplayOrderAsc();
    List<Project> findByFeaturedTrueOrderByDisplayOrderAsc();
    List<Project> findByStatusOrderByDisplayOrderAsc(Project.ProjectStatus status);


}

