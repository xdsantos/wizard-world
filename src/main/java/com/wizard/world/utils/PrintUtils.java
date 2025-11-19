package com.wizard.world.utils;

import com.wizard.world.model.Elixir;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@UtilityClass
public class PrintUtils {

    public void printElixirs(String ingredient, List<Elixir> elixirs) {
        if (elixirs == null || elixirs.isEmpty()) {
            log.info("No elixirs found for ingredient: {}", ingredient);
        } else {
            log.info("=== Elixirs containing {} ===", ingredient);
            for (int i = 0; i < elixirs.size(); i++) {
                log.info("{}. {}\n{}", i + 1, elixirs.get(i).getName(), elixirs.get(i));
                log.info("--------------------------------------------------"); // separator
            }
        }
    }
}
