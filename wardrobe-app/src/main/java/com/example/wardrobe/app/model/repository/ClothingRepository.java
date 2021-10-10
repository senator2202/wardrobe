package com.example.wardrobe.app.model.repository;

import com.example.wardrobe.app.model.entity.WardrobeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends JpaRepository<WardrobeItem, Long> {
}
