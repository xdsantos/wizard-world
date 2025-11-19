package com.wizard.world.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class Elixir {
    String name;
    String effect;
    String difficult;
    String sideEffects;

    @Override
    public String toString() {
        return String.format(
                "  Name        : %s%n" +
                "  Effect      : %s%n" +
                "  Difficulty  : %s%n" +
                "  Side Effects: %s%n",
                name, effect, difficult, sideEffects
        );
    }
}
