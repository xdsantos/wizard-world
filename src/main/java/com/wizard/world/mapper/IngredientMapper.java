package com.wizard.world.mapper;

import com.wizard.world.generated.model.IngredientDto;
import com.wizard.world.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public Ingredient mapToIngredient(IngredientDto ingredientDto) {
        return Ingredient.builder()
                .id(ingredientDto.getId())
                .name(ingredientDto.getName())
                .build();
    }
}
