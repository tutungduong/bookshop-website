package com.bookshop.dto.general;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ImageRequest {
    private @Nullable Long id;
    private String name;
    private String path;
    private String contentType;
    private Long size;
    private String group;
    private Boolean isThumbnail;
    private Boolean isEliminated;
}
