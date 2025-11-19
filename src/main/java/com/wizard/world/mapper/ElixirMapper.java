package com.wizard.world.mapper;

import com.wizard.world.generated.model.ElixirDto;
import com.wizard.world.model.Elixir;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ElixirMapper {

    public static final String NONE = "None";

    public Elixir mapToElixir(ElixirDto elixirDto) {
        return Elixir.builder()
                .name(elixirDto.getName())
                .difficult(elixirDto.getDifficulty().getValue())
                .sideEffects(StringUtils.isBlank(elixirDto.getSideEffects()) ? NONE : elixirDto.getSideEffects())
                .effect(elixirDto.getEffect())
                .build();
    }
}



