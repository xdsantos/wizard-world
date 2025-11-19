package com.wizard.world.service;

import com.wizard.world.generated.api.ElixirsApi;
import com.wizard.world.generated.api.IngredientsApi;
import com.wizard.world.mapper.ElixirMapper;
import com.wizard.world.mapper.IngredientMapper;
import com.wizard.world.model.Elixir;
import com.wizard.world.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public record WizardWorldApiService(
        IngredientsApi ingredientsApi,
        ElixirsApi elixirsApi,
        IngredientMapper ingredientMapper,
        ElixirMapper elixirMapper
) {

    public List<Ingredient> getAllIngredients() {
        return ingredientsApi.ingredientsGet(null)
                .map(ingredientMapper::mapToIngredient)
                .collectList()
                .block();
    }

    public List<Elixir> getElixirByIngredientName(String ingredientName) {
        return elixirsApi.elixirsGet(null, null, ingredientName, null, null)
                .map(elixirMapper::mapToElixir)
                .collectList()
                .block();
    }
}
