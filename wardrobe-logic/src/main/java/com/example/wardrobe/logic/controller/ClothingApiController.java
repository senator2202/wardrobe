package com.example.wardrobe.logic.controller;

import com.example.wardrobe.logic.model.dto.PageDto;
import com.example.wardrobe.logic.model.dto.WardrobeItemDto;
import com.example.wardrobe.logic.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clothing")
public class ClothingApiController {

    private final ClothingService clothingService;

    @Autowired
    public ClothingApiController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping
    public PageDto<WardrobeItemDto> getWardrobeItems(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestParam(required = false) String sort,
                                                     @RequestParam(required = false) String direction) {
        return clothingService.findClothing(page, size, sort, direction);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteItem(@RequestParam long id) {
        boolean result = clothingService.delete(id);
        return ResponseEntity.status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(result);
    }

    @PostMapping
    public WardrobeItemDto postWardrobeItem(@RequestParam WardrobeItemDto item) {
        return clothingService.addItem(item);
    }

    @PutMapping("/{id}")
    public Optional<WardrobeItemDto> updateItem(@PathVariable long id, @RequestBody WardrobeItemDto itemDto) {
        return clothingService.updateItem(itemDto);
    }
}
