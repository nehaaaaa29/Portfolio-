package com.example.Portfolio.dto;

import com.example.Portfolio.entity.Skill;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
public class SkillDto {
    @Data
    public static class Request {
        @NotBlank private String name;
        private Skill.SkillCategory category;
        @Min(0) @Max(100) private int proficiencyPercent;
        private String iconUrl;
        private int displayOrder;
    }

    @Data
    public static class Response {
        private Long id;
        private String name;
        private Skill.SkillCategory category;
        private int proficiencyPercent;
        private String iconUrl;
        private int displayOrder;
    }
}
