package com.example.wardrobe.app.util;

import org.junit.jupiter.api.Test;

import java.util.Set;

class AjaxHtmlGeneratorTest {

    @Test
    void generatePhotoAlbum() {
        String result = AjaxHtmlGenerator.generatePhotoAlbum(Set.of("/static/folder/1.png", "/templates/2.jpg", "/src/3.bmp"));
    }
}