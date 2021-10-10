package com.example.wardrobe.app.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemColorDto {

    private long id;
    private String name;
    private String hexCode;

    public ItemColorDto(long id, String name, String hexCode) {
        this.id = id;
        this.name = name;
        this.hexCode = hexCode;
    }
}
