package com.example.catch_clone.stores.dto;

import java.time.LocalDateTime;

public record StoreFilesResponseDto(Long storeId, String fileUrl, LocalDateTime createdAt) {

}
