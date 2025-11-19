package com.wizard.world.mapper;

import com.wizard.world.generated.model.ElixirDifficulty;
import com.wizard.world.generated.model.ElixirDto;
import com.wizard.world.model.Elixir;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ElixirMapperTest {

    private ElixirMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ElixirMapper();
    }

    // ------------------------------------------------------------
    // TEST: Normal mapping
    // ------------------------------------------------------------
    @Test
    void testMapToElixir_NormalCase() {
        ElixirDto dto = new ElixirDto()
                .name("Memory Potion")
                .difficulty(ElixirDifficulty.ADVANCED)
                .sideEffects("None")
                .effect("Enhances memory");

        Elixir result = mapper.mapToElixir(dto);

        assertEquals("Memory Potion", result.getName());
        assertEquals("Advanced", result.getDifficult());
        assertEquals("None", result.getSideEffects());
        assertEquals("Enhances memory", result.getEffect());
    }

    // ------------------------------------------------------------
    // TEST: Blank side effects → "None"
    // ------------------------------------------------------------
    @Test
    void testMapToElixir_BlankSideEffects() {
        ElixirDto dto = new ElixirDto()
                .name("Test Potion")
                .difficulty(ElixirDifficulty.BEGINNER)
                .sideEffects("   ")   // blank input
                .effect("Test effect");

        Elixir result = mapper.mapToElixir(dto);

        assertEquals("None", result.getSideEffects());
    }

    // ------------------------------------------------------------
    // TEST: Null side effects → "None"
    // ------------------------------------------------------------
    @Test
    void testMapToElixir_NullSideEffects() {
        ElixirDto dto = new ElixirDto()
                .name("Test Potion")
                .difficulty(ElixirDifficulty.MODERATE)
                .sideEffects(null)  // null input
                .effect("Test effect");

        Elixir result = mapper.mapToElixir(dto);

        assertEquals("None", result.getSideEffects());
    }

    // ------------------------------------------------------------
    // TEST: Difficulty value mapping
    // ------------------------------------------------------------
    @Test
    void testMapToElixir_MapsDifficultyCorrectly() {
        ElixirDto dto = new ElixirDto()
                .name("Test Potion")
                .difficulty(ElixirDifficulty.BEGINNER)
                .sideEffects("None")
                .effect("Test effect");

        Elixir result = mapper.mapToElixir(dto);

        assertEquals("Beginner", result.getDifficult());
    }

    // ------------------------------------------------------------
    // TEST: Full object creation sanity check
    // ------------------------------------------------------------
    @Test
    void testMapToElixir_ObjectIntegrity() {
        ElixirDto dto = new ElixirDto()
                .name("Invigoration Draught")
                .difficulty(ElixirDifficulty.BEGINNER)
                .sideEffects("Energetic rebound")
                .effect("Energises the drinker");

        Elixir elixir = mapper.mapToElixir(dto);

        assertNotNull(elixir);
        assertEquals("Invigoration Draught", elixir.getName());
        assertEquals("Beginner", elixir.getDifficult());
        assertEquals("Energetic rebound", elixir.getSideEffects());
        assertEquals("Energises the drinker", elixir.getEffect());
    }
}
