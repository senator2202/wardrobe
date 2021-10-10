package com.example.wardrobe.logic.util;

import com.example.wardrobe.logic.model.dto.ItemColorDto;
import com.example.wardrobe.logic.model.dto.PageDto;
import com.example.wardrobe.logic.model.dto.WardrobeItemDto;
import com.example.wardrobe.logic.model.entity.WardrobeItem;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ObjectConverter {

    private ObjectConverter() {
    }

    public static WardrobeItemDto itemToItemDto(WardrobeItem item) {
        return new WardrobeItemDto(
                item.getId(),
                item.getName(),
                new ArrayList<>(item.getSizes()),
                item.getPrice(),
                new ItemColorDto(item.getColor().getId(), item.getColor().getName(), item.getColor().getHexCode()),
                item.getWardrobeItemType(),
                item.getImageSources() != null && !item.getImageSources().isEmpty()
                        ? new HashSet<>(item.getImageSources())
                        : Set.of("/images/product/1.png")
        );
    }

    public static PageDto<WardrobeItemDto> pageToPageDto(Page<WardrobeItem> page) {
        List<WardrobeItemDto> dtoList = page.getContent().stream()
                .map(ObjectConverter::itemToItemDto)
                .collect(Collectors.toList());
        List<String> pageList = IntStream
                .rangeClosed(1, page.getTotalPages())
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toList());
        return new PageDto<>(dtoList, pageList);
    }
}
