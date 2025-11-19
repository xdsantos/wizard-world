package com.wizard.world.mapper;

import com.wizard.world.generated.model.IngredientDto;
import com.wizard.world.model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class IngredientMapperTest {

    private IngredientMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new IngredientMapper();
    }

    // ------------------------------------------------------------
    // TEST: Normal mapping
    // ------------------------------------------------------------
    @Test
    void testMapToIngredient_NormalCase() {
        IngredientDto dto = new IngredientDto()
                .id(UUID.randomUUID())
                .name("Mandrake");

        Ingredient ingredient = mapper.mapToIngredient(dto);

        assertNotNull(ingredient);
        assertEquals(dto.getId(), ingredient.getId());
        assertEquals("Mandrake", ingredient.getName());
    }

    // ------------------------------------------------------------
    // TEST: Null ID or Name
    // ------------------------------------------------------------
    @Test
    void testMapToIngredient_NullFields() {
        IngredientDto dto = new IngredientDto()
                .id(null)
                .name(null);

        Ingredient ingredient = mapper.mapToIngredient(dto);

        assertNotNull(ingredient);
        assertNull(ingredient.getId());
        assertNull(ingredient.getName());
    }

    // ------------------------------------------------------------
    // TEST: Object integrity
    // ------------------------------------------------------------
    @Test
    void testMapToIngredient_ObjectIntegrity() {
        IngredientDto dto = new IngredientDto()
                .id(UUID.randomUUID())
                .name("Newt spleens");

        Ingredient ingredient = mapper.mapToIngredient(dto);

        assertEquals(dto.getId(), ingredient.getId());
        assertEquals("Newt spleens", ingredient.getName());
    }
}
