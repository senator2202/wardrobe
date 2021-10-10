package com.example.wardrobe.app.model.repository;

import com.example.wardrobe.app.model.entity.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<ItemColor, Long> {

    Optional<ItemColor> findItemColorByName(String name);
}
