package com.example.Portfolio.controller;
import com.example.Portfolio.dto.ApiResponse;
import com.example.Portfolio.dto.SkillDto;
import com.example.Portfolio.entity.Skill;
import com.example.Portfolio.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SkillDto.Response>>> getAllSkills(
            @RequestParam(required = false) Skill.SkillCategory category) {
        List<SkillDto.Response> skills = (category != null)
                ? skillService.getSkillsByCategory(category)
                : skillService.getAllSkills();
        return ResponseEntity.ok(ApiResponse.success(skills));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SkillDto.Response>> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(skillService.getSkillById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SkillDto.Response>> createSkill(
            @Valid @RequestBody SkillDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Skill created", skillService.createSkill(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SkillDto.Response>> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody SkillDto.Request request) {
        return ResponseEntity.ok(ApiResponse.success("Skill updated",
                skillService.updateSkill(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.ok(ApiResponse.success("Skill deleted", null));
    }
}


