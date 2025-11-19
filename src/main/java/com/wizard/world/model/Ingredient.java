package com.wizard.world.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
public class Ingredient {
    UUID id;
    String name;
}
