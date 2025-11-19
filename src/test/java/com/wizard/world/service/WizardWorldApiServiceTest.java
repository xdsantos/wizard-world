package com.wizard.world.service;

import com.wizard.world.generated.api.ElixirsApi;
import com.wizard.world.generated.api.IngredientsApi;
import com.wizard.world.generated.model.ElixirDto;
import com.wizard.world.generated.model.IngredientDto;
import com.wizard.world.mapper.ElixirMapper;
import com.wizard.world.mapper.IngredientMapper;
import com.wizard.world.model.Elixir;
import com.wizard.world.model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WizardWorldApiServiceTest {

    @Mock
    private IngredientsApi ingredientsApi;

    @Mock
    private ElixirsApi elixirsApi;

    @Mock
    private IngredientMapper ingredientMapper;

    @Mock
    private ElixirMapper elixirMapper;

    @InjectMocks
    private WizardWorldApiService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ------------------------------------------------------------
    // TEST: getAllIngredients()
    // ------------------------------------------------------------
    @Test
    void testGetAllIngredients() {
        // given
        IngredientDto dtoA = new IngredientDto().name("A");
        IngredientDto dtoB = new IngredientDto().name("B");

        Ingredient ingredientA = Ingredient.builder().name("A").build();
        Ingredient ingredientB = Ingredient.builder().name("B").build();

        when(ingredientsApi.ingredientsGet(null))
                .thenReturn(Flux.just(dtoA, dtoB));

        when(ingredientMapper.mapToIngredient(dtoA)).thenReturn(ingredientA);
        when(ingredientMapper.mapToIngredient(dtoB)).thenReturn(ingredientB);

        // when
        List<Ingredient> result = service.getAllIngredients();

        // then
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());
        assertEquals("B", result.get(1).getName());

        verify(ingredientsApi).ingredientsGet(null);
        verify(ingredientMapper).mapToIngredient(dtoA);
        verify(ingredientMapper).mapToIngredient(dtoB);
    }

    // ------------------------------------------------------------
    // TEST: getElixirByIngredientName()
    // ------------------------------------------------------------
    @Test
    void testGetElixirByIngredientName() {
        // given
        String ingredientName = "Mandrake";

        ElixirDto dto1 = new ElixirDto().name("Potion A");
        ElixirDto dto2 = new ElixirDto().name("Potion B");

        Elixir elixir1 = Elixir.builder().name("Potion A").build();
        Elixir elixir2 = Elixir.builder().name("Potion B").build();

        when(elixirsApi.elixirsGet(null, null, ingredientName, null, null))
                .thenReturn(Flux.just(dto1, dto2));

        when(elixirMapper.mapToElixir(dto1)).thenReturn(elixir1);
        when(elixirMapper.mapToElixir(dto2)).thenReturn(elixir2);

        // when
        List<Elixir> result = service.getElixirByIngredientName(ingredientName);

        // then
        assertEquals(2, result.size());
        assertEquals("Potion A", result.get(0).getName());
        assertEquals("Potion B", result.get(1).getName());

        verify(elixirsApi).elixirsGet(null, null, ingredientName, null, null);
        verify(elixirMapper).mapToElixir(dto1);
        verify(elixirMapper).mapToElixir(dto2);
    }
}
