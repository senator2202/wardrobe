package com.example.wardrobe.logic.service.impl;

import com.example.wardrobe.logic.model.dto.PageDto;
import com.example.wardrobe.logic.model.dto.WardrobeItemDto;
import com.example.wardrobe.logic.model.entity.ItemColor;
import com.example.wardrobe.logic.model.entity.WardrobeItem;
import com.example.wardrobe.logic.model.repository.ClothingRepository;
import com.example.wardrobe.logic.model.repository.ColorRepository;
import com.example.wardrobe.logic.service.ClothingService;
import com.example.wardrobe.logic.util.ObjectConverter;
import com.example.wardrobe.logic.util.PageableProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ClothingServiceImpl implements ClothingService {

    private final ClothingRepository clothingRepository;
    private final ColorRepository colorRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository, ColorRepository colorRepository) {
        this.clothingRepository = clothingRepository;
        this.colorRepository = colorRepository;
    }

    @Override
    public PageDto<WardrobeItemDto> findClothing(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PageableProvider.pageable(page, size);
        Page<WardrobeItem> itemsPage = clothingRepository.findAll(pageable);
        return ObjectConverter.pageToPageDto(itemsPage);
    }

    @Override
    @Transactional
    public WardrobeItemDto addItem(WardrobeItemDto itemDto) {
        WardrobeItem newItem = new WardrobeItem();
        ItemColor color = colorRepository
                .findItemColorByName(itemDto.getColor().getName().toUpperCase())
                .orElse(null);
        newItem.setName(itemDto.getName());
        newItem.setPrice(itemDto.getPrice());
        newItem.setSizes(itemDto.getSizes());
        newItem.setWardrobeItemType(itemDto.getType());
        newItem.setColor(color);
        if (itemDto.getImageSources().isEmpty()) {
            itemDto.getImageSources().add("/images/product/default.png");
        }
        newItem.setImageSources(itemDto.getImageSources());
        WardrobeItem saved = clothingRepository.save(newItem);
        return ObjectConverter.itemToItemDto(saved);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        boolean itemFound = false;
        if (clothingRepository.existsById(id)) {
            clothingRepository.deleteById(id);
            itemFound = true;
        }
        return itemFound;
    }

    @Override
    @Transactional
    public Optional<WardrobeItemDto> updateItem(WardrobeItemDto itemDto) {
        Optional<WardrobeItem> itemOptional = clothingRepository.findById(itemDto.getId());
        if (itemOptional.isPresent()) {
            WardrobeItem foundItem = itemOptional.get();
            Optional<ItemColor> colorOptional =
                    colorRepository.findItemColorByName(itemDto.getColor().getName().toUpperCase());
            colorOptional.ifPresent(foundItem::setColor);
            foundItem.setName(itemDto.getName());
            foundItem.setWardrobeItemType(itemDto.getType());
            foundItem.setPrice(itemDto.getPrice());
            if (itemDto.getImageSources().isEmpty()) {
                itemDto.getImageSources().add("/images/product/default.png");
            }
            foundItem.setImageSources(new HashSet<>(itemDto.getImageSources()));
            foundItem.setSizes(new ArrayList<>(itemDto.getSizes()));
            WardrobeItem updated = clothingRepository.save(foundItem);
            return Optional.of(ObjectConverter.itemToItemDto(updated));
        }
        return Optional.empty();
    }
}
