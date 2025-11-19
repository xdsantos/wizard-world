package com.wizard.world.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
@UtilityClass
public class InputReaderUtils {

    public static List<Integer> readNumberChoices(int maxOption) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> choices = new ArrayList<>();

        while (choices.isEmpty()) {
            log.info("Choose one or more ingredients by number (comma-separated, e.g. 1,3,5): ");
            String input = scanner.nextLine();

            try {
                choices = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .filter(choice -> choice > 0 && choice <= maxOption)
                        .distinct()
                        .toList();

                if (choices.isEmpty()) {
                    log.info("Invalid selection. Please enter numbers corresponding to the listed options.");
                }
            } catch (NumberFormatException e) {
                log.error("Invalid input. Please enter numbers separated by commas. e.g. 1,3,5");
                choices.clear();
            }
        }

        return choices;
    }


}
