package com.example.wardrobe.logic.service;

import com.example.wardrobe.logic.model.dto.PageDto;
import com.example.wardrobe.logic.model.dto.WardrobeItemDto;

import java.util.Optional;

public interface ClothingService {
    PageDto<WardrobeItemDto> findClothing(Integer page, Integer size, String sort, String direction);

    WardrobeItemDto addItem(WardrobeItemDto itemDto);

    boolean delete(long id);

    Optional<WardrobeItemDto> updateItem(WardrobeItemDto itemDto);
}
