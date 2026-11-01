package com.example.Portfolio.controller;


    import com.example.Portfolio.dto.ApiResponse;
    import com.example.Portfolio.dto.ProjectDto;
    import com.example.Portfolio.entity.Project;
    import com.example.Portfolio.service.ProjectService;
    import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/projects")
    public class ProjectController {

        @Autowired
        private ProjectService projectService;

        @GetMapping
        public ResponseEntity<ApiResponse<List<ProjectDto.Response>>> getAllProjects(
                @RequestParam(required = false) Project.ProjectStatus status,
                @RequestParam(required = false) Boolean featured) {

            List<ProjectDto.Response> projects;
            if (Boolean.TRUE.equals(featured)) {
                projects = projectService.getFeaturedProjects();
            } else if (status != null) {
                projects = projectService.getProjectsByStatus(status);
            } else {
                projects = projectService.getAllProjects();
            }
            return ResponseEntity.ok(ApiResponse.success(projects));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<ProjectDto.Response>> getProjectById(@PathVariable Long id) {
            return ResponseEntity.ok(ApiResponse.success(projectService.getProjectById(id)));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<ProjectDto.Response>> createProject(
                @Valid @RequestBody ProjectDto.Request request) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Project created", projectService.createProject(request)));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<ProjectDto.Response>> updateProject(
                @PathVariable Long id,
                @Valid @RequestBody ProjectDto.Request request) {
            return ResponseEntity.ok(ApiResponse.success("Project updated",
                    projectService.updateProject(id, request)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id) {
            projectService.deleteProject(id);
            return ResponseEntity.ok(ApiResponse.success("Project deleted", null));
        }
    }


