package com.example.wardrobe.app.controller;

import com.example.wardrobe.app.model.dto.PageDto;
import com.example.wardrobe.app.service.ClothingService;
import com.example.wardrobe.app.model.dto.WardrobeItemDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static com.example.wardrobe.app.controller.ClothingController.CLOTHING_ATTRIBUTE;
import static com.example.wardrobe.app.controller.ClothingController.PAGES;

/**
 * The type Admin controller.
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    public static final String IMAGE_SOURCES = "imageSources";
    public static final String IMAGE_SOURCES_SEPARATOR = ", ";
    private static final String ADMIN_MAIN = "/admin/main";
    private static final String ADMIN_WARDROBE = "/admin/wardrobe";
    private static final String NEW_WARDROBE_ITEM = "wardrobeItem";
    private final ClothingService clothingService;

    public AdminController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping
    public String getAdminMain() {
        return ADMIN_MAIN;
    }

    @GetMapping("/wardrobe")
    public String showAddItemForm(Model model,
                                  @RequestParam(required = false, defaultValue = "0") Integer page,
                                  @RequestParam(required = false, defaultValue = "8") Integer size,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) String direction) {
        PageDto<WardrobeItemDto> pageDto = clothingService.findClothing(page, size, sort, direction);
        model.addAttribute(CLOTHING_ATTRIBUTE, pageDto.getContent());
        model.addAttribute(PAGES, pageDto.getPageList());
        model.addAttribute(NEW_WARDROBE_ITEM, new WardrobeItemDto());
        return ADMIN_WARDROBE;
    }

    @PostMapping("/wardrobe")
    public String postWardrobeItem(@ModelAttribute WardrobeItemDto item, HttpServletRequest request) {
        filterSizes(item, request);
        clothingService.addItem(item);
        return "redirect:" + ADMIN_WARDROBE;
    }

    /**
     * Method removes elements from size set that were not checked on the form.
     *
     * @param item    the item
     * @param request the request
     */
    private void filterSizes(WardrobeItemDto item, HttpServletRequest request) {
        Set<String> parameters = request.getParameterMap().keySet();
        item.getSizes().removeIf(size -> !parameters.contains(size));
        if(!request.getParameter(IMAGE_SOURCES).isEmpty()) {
            item.setImageSources(Set.of(request.getParameter(IMAGE_SOURCES).split(IMAGE_SOURCES_SEPARATOR)));
        }
    }

    @PutMapping("/wardrobe/{id}")
    public String updateItem(@PathVariable long id, WardrobeItemDto itemDto, HttpServletRequest request) {
        filterSizes(itemDto, request);
        clothingService.updateItem(itemDto);
        return "redirect:" + ADMIN_WARDROBE;
    }
}
