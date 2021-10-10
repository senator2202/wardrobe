package com.example.wardrobe.app.model.dto;

import com.example.wardrobe.app.model.entity.WardrobeItem;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class WardrobeItemDto {

    private static final List<String> DEFAULT_SIZE_LIST = List.of("42", "44", "46", "48", "50", "52", "54", "56",
            "58", "60", "62", "64", "66", "68", "70", "S", "M", "L", "XL", "XXL", "XXXL", "XXXXL");
    private long id;
    private String name;
    private List<String> sizes = Lists.newArrayList(DEFAULT_SIZE_LIST);
    private int price;
    private ItemColorDto color;
    private WardrobeItem.WardrobeItemType type;
    private Set<String> imageSources = new HashSet<>();

    public WardrobeItemDto(long id, String name, List<String> sizes, int price, ItemColorDto color, WardrobeItem.WardrobeItemType type, Set<String> imageSources) {
        this.id = id;
        this.name = name;
        this.sizes = sizes;
        this.price = price;
        this.color = color;
        this.type = type;
        this.imageSources = imageSources;
    }
}
