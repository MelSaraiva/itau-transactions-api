package com.itau.api.transactions.controller.dto;

public record EstatisticaResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max

) {
}
