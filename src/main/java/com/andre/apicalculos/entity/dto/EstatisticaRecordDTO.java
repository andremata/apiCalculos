package com.andre.apicalculos.entity.dto;

public record EstatisticaRecordDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max) {
}
