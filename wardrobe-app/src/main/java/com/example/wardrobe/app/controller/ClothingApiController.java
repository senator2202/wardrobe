package com.example.wardrobe.app.controller;

import com.example.wardrobe.app.model.dto.WardrobeItemDto;
import com.example.wardrobe.app.service.ClothingService;
import com.example.wardrobe.app.util.AjaxHtmlGenerator;
import com.example.wardrobe.app.service.impl.ClothingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clothing")
public class ClothingApiController {

    private final ClothingService clothingService;

    @Autowired
    public ClothingApiController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping
    public List<WardrobeItemDto> getWardrobeItems(@RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size,
                                                  @RequestParam(required = false) String sort,
                                                  @RequestParam(required = false) String direction) {
        return clothingService.findClothing(page, size, sort, direction).getContent();
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteItem(@RequestParam long id) {
        boolean result = clothingService.delete(id);
        return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }

    @GetMapping("/html")
    public String getWardrobeItemsHtml(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(required = false, defaultValue = "8") Integer size,
                                                  @RequestParam(required = false) String sort,
                                                  @RequestParam(required = false) String direction) {
        List<WardrobeItemDto> items = clothingService.findClothing(page, size, sort, direction).getContent();
        return AjaxHtmlGenerator.generateHtml(items);
    }
}
