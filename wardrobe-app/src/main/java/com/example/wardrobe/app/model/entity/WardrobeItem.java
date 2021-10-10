package com.example.wardrobe.app.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wardrobe_item")
@Getter
@Setter
@NoArgsConstructor
public class WardrobeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "item_size", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "size")
    private List<String> sizes;

    @Column(name = "price")
    private int price;

    @OneToOne
    @JoinColumn(name = "color_id")
    private ItemColor color;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WardrobeItemType wardrobeItemType;

    @ElementCollection
    @CollectionTable(name = "item_image", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "image_path")
    private Set<String> imageSources;

    public enum WardrobeItemType {
        DRESS, SUIT, PANTS, BLOUSE
    }
}
