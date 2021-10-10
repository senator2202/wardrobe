package com.example.wardrobe.app.service.impl;

import com.example.wardrobe.app.model.entity.WardrobeItem;
import com.example.wardrobe.app.model.repository.ClothingRepository;
import com.example.wardrobe.app.model.repository.ColorRepository;
import com.example.wardrobe.app.service.ClothingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

class ClothingServiceImplTest {

    @Mock
    Page<WardrobeItem> itemsPage;
    @Mock
    private ClothingRepository clothingRepository;
    @Mock
    private ColorRepository colorRepository;
    @InjectMocks
    private final ClothingService clothingService = new ClothingServiceImpl(clothingRepository, colorRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    static Stream<Arguments> findClothingArgs() {
        return Stream.of(
                Arguments.of(null, null, null, null),
                Arguments.of(10, null, null, null),
                Arguments.of(null, null, "price", null),
                Arguments.of(null, null, null, "asc"),
                Arguments.of(null, 10, null, null),
                Arguments.of(null, 10, "duration", null),
                Arguments.of(null, 10, "createDate", "desc"),
                Arguments.of(2, 10, "lastUpdateDate", "asc"),
                Arguments.of(2, 10, null, null)
        );
    }

    private static Page<WardrobeItem> createPage(PageRequest pageRequest) {
        return new PageImpl<>(Collections.singletonList(new WardrobeItem()), pageRequest, 15);
    }

    /*@ParameterizedTest
    @MethodSource("findClothingArgs")
    void findClothing(Integer page, Integer size, String sort, String direction) {
        when(clothingRepository.findAll(any(Pageable.class))).thenReturn(itemsPage);
        List<WardrobeItemDto> actual = clothingService.findClothing(page, size, sort, direction);
        assertEquals(Collections.emptyList(), actual);
    }*/
}