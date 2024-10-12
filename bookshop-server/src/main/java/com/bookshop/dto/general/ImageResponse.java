package com.bookshop.dto.general;

import lombok.Data;

import java.time.Instant;

@Data
public class ImageResponse {
    private Long id;
    private String name;
    private String path;
    private String contentType;
    private Long size;
    private String group;
    private Boolean isThumbnail;
    private Boolean isEliminated;
    private Instant createdAt;
    private Instant updatedAt;
}