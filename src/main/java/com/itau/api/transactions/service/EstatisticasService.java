package com.itau.api.transactions.service;

import com.itau.api.transactions.controller.dto.EstatisticaResponseDTO;
import com.itau.api.transactions.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticaResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {
        log.info("Iniciando o processamento de estatísticas das transações");

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = transacoes.stream().
                mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatísticas retornadas com sucesso!");
        return new EstatisticaResponseDTO(estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getMin(),
                estatisticas.getMax(),
                estatisticas.getAverage());
    }
}
