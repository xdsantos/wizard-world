package com.wizard.world.command;

import com.wizard.world.model.Elixir;
import com.wizard.world.model.Ingredient;
import com.wizard.world.service.WizardWorldApiService;
import com.wizard.world.utils.InputReaderUtils;
import com.wizard.world.utils.PrintUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class WizardWorldShellCommandTest {

    @Mock
    private WizardWorldApiService apiService;

    @InjectMocks
    private WizardWorldShellCommand shellCommand;

    MockedStatic<InputReaderUtils> inputReaderMock;
    MockedStatic<PrintUtils> printUtilsMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        inputReaderMock = Mockito.mockStatic(InputReaderUtils.class);
        printUtilsMock = Mockito.mockStatic(PrintUtils.class);
    }

    // -------------------------------------------------------
    // TEST: select-ingredients command
    // -------------------------------------------------------
    @Test
    void testGetElixirsByIngredientNames() {

        // --- Arrange ---
        Ingredient i1 = Ingredient.builder().id(UUID.randomUUID()).name("Mandrake").build();
        Ingredient i2 = Ingredient.builder().id(UUID.randomUUID()).name("Newt spleens").build();
        List<Ingredient> ingredientList = List.of(i1, i2);

        when(apiService.getAllIngredients()).thenReturn(ingredientList);

        // User selects ingredient numbers: "1,2"
        inputReaderMock.when(() -> InputReaderUtils.readNumberChoices(2))
                .thenReturn(List.of(1, 2));

        // Mock elixir results
        List<Elixir> elixirs1 = List.of(Elixir.builder().name("Potion A").build());
        List<Elixir> elixirs2 = List.of(Elixir.builder().name("Potion B").build());

        when(apiService.getElixirByIngredientName("Mandrake")).thenReturn(elixirs1);
        when(apiService.getElixirByIngredientName("Newt spleens")).thenReturn(elixirs2);

        // --- Act ---
        shellCommand.getElixirsByIngredientNames();

        // --- Assert ---
        verify(apiService).getAllIngredients();
        verify(apiService).getElixirByIngredientName("Mandrake");
        verify(apiService).getElixirByIngredientName("Newt spleens");

        // Verify PrintUtils is called correctly
        printUtilsMock.verify(() -> PrintUtils.printElixirs("Mandrake", elixirs1));
        printUtilsMock.verify(() -> PrintUtils.printElixirs("Newt spleens", elixirs2));
    }
}