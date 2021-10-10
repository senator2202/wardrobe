package com.example.wardrobe.app.service.impl;

import com.example.wardrobe.app.model.dto.PageDto;
import com.example.wardrobe.app.model.dto.WardrobeItemDto;
import com.example.wardrobe.app.model.entity.ItemColor;
import com.example.wardrobe.app.model.entity.WardrobeItem;
import com.example.wardrobe.app.model.repository.ClothingRepository;
import com.example.wardrobe.app.model.repository.ColorRepository;
import com.example.wardrobe.app.service.ClothingService;
import com.example.wardrobe.app.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ClothingServiceImpl implements ClothingService {

    private static final String CLOTHING = "/api/clothing";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ClothingRepository clothingRepository;
    private final ColorRepository colorRepository;
    @Value("${wardrobe.clothing.url}")
    private String clothingServiceUrl;

    @Autowired
    public ClothingServiceImpl(ClothingRepository clothingRepository, ColorRepository colorRepository) {
        this.clothingRepository = clothingRepository;
        this.colorRepository = colorRepository;
    }

    @Override
    public PageDto<WardrobeItemDto> findClothing(Integer page, Integer size, String sort, String direction) {
        String url = clothingServiceUrl + CLOTHING;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sort", sort)
                .queryParam("direction", direction);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<PageDto<WardrobeItemDto>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
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
    public boolean updateItem(WardrobeItemDto itemDto) {
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
            clothingRepository.save(foundItem);
            return true;
        }
        return false;
    }
}
