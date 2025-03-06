package com.andre.apicalculos.service;

import com.andre.apicalculos.entity.dto.TransacaoRecordDTO;
import com.andre.apicalculos.exception.UnprocessableEntity;
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

    private final List<TransacaoRecordDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRecordDTO transacao) {
        log.info("Iniciado o processo de gravação da transação. " + transacao);

        if(transacao.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maior que a atual.");
            throw new UnprocessableEntity("Data e hora maior de a atual!");
        }

        if(transacao.valor() < 0) {
            log.error("Valor não pode ser negativo.");
            throw new UnprocessableEntity("Valor não pode ser menor que zero!");
        }

        listaTransacoes.add(transacao);
        log.info("Transação adiciona com sucesso!");
    }

    public void excluirTransacoes() {
        log.info("Excluindo transações.");
        listaTransacoes.clear();
        log.info("Transação excluída com sucesso!");
    }

    public List<TransacaoRecordDTO> obterTransacoes(Integer intervalorBusca) {
        log.info("Obtendo transações para cálculo de estatisticas. Intervalor " + intervalorBusca);

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(60);

        return listaTransacoes.stream()
                .filter(transaccao -> transaccao.dataHora().isAfter(dataHoraIntervalo))
                .toList();
    }
}
