package com.kaankaplan.basketballplayermanagementproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record PlayerDto(String name, String surname, Integer positionId) {}
