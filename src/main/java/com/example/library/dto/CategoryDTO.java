package com.example.library.dto;

public class CategoryDTO {
    private String name;
    private String description;
    private String code;
    private Long parentCategoryId;

    public CategoryDTO() {}

    public CategoryDTO(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getParentCategoryId() { return parentCategoryId; }
    public void setParentCategoryId(Long parentCategoryId) { this.parentCategoryId = parentCategoryId; }
} 