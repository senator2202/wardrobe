package com.example.wardrobe.app.util;

import com.example.wardrobe.app.model.dto.WardrobeItemDto;

import java.util.List;
import java.util.Set;

public class AjaxHtmlGenerator {

    public static final String ITEMS_HTML = """
            <section class="product">
                    <div class="product__photo">
                        <div class="photo-container">
                            <div class="photo-main">
                                <div class="controls">
                                    <span>%s</span> <span>%s</span>
                                    <span id="expandBtn" role="button" tabindex="0" class="material-icons" onclick="toggleInfo(this)">arrow_forward_ios</span>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <img src="%s" alt="Весна">
                                </div>
                            </div>
                            <div class="photo-album">
                                <ul>
                                    %s
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="product__info">
                        <div class="title">
                            <h1>%s %s</h1>
                        </div>
                        <div class="price">
                            Br <span>%s</span>
                        </div>
                        <div class="description">
                            <h3>BENEFITS</h3>
                            <ul>
                                <li>Apples are nutricious</li>
                                <li>Apples may be good for weight loss</li>
                                <li>Apples may be good for bone health</li>
                                <li>They're linked to a lowest risk of diabetes</li>
                            </ul>
                        </div>
                        <button class="buy--btn">ADD TO CART</button>
                    </div>
                </section>
            """;

    public static String generateHtml(List<WardrobeItemDto> items) {
        StringBuilder result = new StringBuilder();
        items.forEach(item -> {
            String imageSourcesHtml = generatePhotoAlbum(item.getImageSources());
            String html = String.format(ITEMS_HTML, item.getType(), item.getName(), item.getImageSources().toArray()[0], imageSourcesHtml, item.getType(), item.getName(), item.getPrice());
            result.append(html);
        });
        return result.toString();
    }

    public static String generatePhotoAlbum(Set<String> imageSources) {
        final StringBuilder result = new StringBuilder();
        imageSources.forEach(source -> {
            result.append(String.format("""
                    <li>
                        <img src="%s" alt="Весна" onclick="replaceImage(this)">
                    </li>
                    """, source));
        });
        return result.toString();
    }
}
