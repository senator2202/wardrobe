package com.example.wardrobe.app.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class PageDto<T> {

    private List<T> content;
    private List<String> pageList;

    public PageDto(List<T> content, List<String> pageList) {
        this.content = content;
        this.pageList = pageList;
    }
}
