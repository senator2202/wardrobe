package com.example.wardrobe.app.controller;

import com.example.wardrobe.app.model.dto.PageDto;
import com.example.wardrobe.app.model.dto.WardrobeItemDto;
import com.example.wardrobe.app.service.ClothingService;
import com.example.wardrobe.app.service.impl.ClothingServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ClothingController {

    public static final String CLOTHING_ATTRIBUTE = "clothing";
    public static final String INDEX_PAGE = "index";
    public static final String PAGES = "pages";
    private static final String NEW_WARDROBE_ITEM = "wardrobeItem";
    private final ClothingService clothingService;

    public ClothingController(ClothingServiceImpl clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping(value = {
            "/",
            "/clothing"
    })
    public String getWardrobeItems(Model model,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) String sort,
                                   @RequestParam(required = false) String direction) {
        PageDto<WardrobeItemDto> pageDto = clothingService.findClothing(page, size, sort, direction);
        model.addAttribute(CLOTHING_ATTRIBUTE, pageDto.getContent());
        model.addAttribute(PAGES, pageDto.getPageList());
        model.addAttribute(NEW_WARDROBE_ITEM, new WardrobeItemDto());
        return INDEX_PAGE;
    }
}
