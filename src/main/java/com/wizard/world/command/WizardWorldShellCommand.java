package com.wizard.world.command;

import com.wizard.world.model.Ingredient;
import com.wizard.world.service.WizardWorldApiService;
import com.wizard.world.utils.InputReaderUtils;
import com.wizard.world.utils.PrintUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@ShellComponent
public record WizardWorldShellCommand(WizardWorldApiService wizardWorldApiService) {

    @ShellMethod(key = "select-ingredients", value = "Select one or more ingredients to show available elixirs")
    public void getElixirsByIngredientNames() {

        List<Ingredient> ingredients = wizardWorldApiService.getAllIngredients();
        log.info("Choose one or more ingredients by number (comma-separated, e.g. 1,3,5):");
        IntStream.range(0, ingredients.size())
                .forEach(index -> log.info("{}: {}", index + 1, ingredients.get(index).getName()));

        List<Integer> choices = InputReaderUtils.readNumberChoices(ingredients.size());
        List<String> selectedIngredientNames = choices.stream()
                .map(choice -> ingredients.get(choice - 1).getName())
                .toList();

        log.info("Fetching elixirs for ingredients: {}", String.join(", ", selectedIngredientNames));
        selectedIngredientNames.stream()
                .map(ingredient -> Map.entry(ingredient, wizardWorldApiService.getElixirByIngredientName(ingredient)))
                .forEach(entry -> PrintUtils.printElixirs(entry.getKey(), entry.getValue()));
    }
}
