package com.itau.api.transactions.service;

import com.itau.api.transactions.controller.dto.TransacaoRequestDTO;
import com.itau.api.transactions.infrastructure.exception.BadRequestEntityException;
import com.itau.api.transactions.infrastructure.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();


    public void adicionarTransacoes(TransacaoRequestDTO request) {

        log.info("Iniciando o processamento de gravar transações");

        if (request.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maior que data e hora atual");
            throw new UnprocessableEntityException("Data e hora maior que data e hora atual");
        }

        if (request.valor() < 0) {
            log.error("Valor da transação não pode ser negativo");
            throw new BadRequestEntityException("Valor da transação não pode ser negativo");
        }

        log.info("Transação adicionada com sucesso! Com valor: {}", request.valor());

        listaTransacoes.add(request);
    }

    public void limparTransacoes() {
        log.info("Iniciando processamento para deletar transações...");

        listaTransacoes.clear();

        log.info("Transações deletadas com sucesso!");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Iniciadas as buscas de transações no intervalo de {} segundos", intervaloBusca);

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno com sucesso!");

        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).toList();

    }

}
