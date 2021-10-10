package com.example.wardrobe.app.service;

import com.example.wardrobe.app.model.dto.PageDto;
import com.example.wardrobe.app.model.dto.WardrobeItemDto;

public interface ClothingService {
    PageDto<WardrobeItemDto> findClothing(Integer page, Integer size, String sort, String direction);

    WardrobeItemDto addItem(WardrobeItemDto itemDto);

    boolean delete(long id);

    boolean updateItem(WardrobeItemDto itemDto);
}
