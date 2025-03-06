package com.andre.apicalculos.service;

import com.andre.apicalculos.entity.dto.EstatisticaRecordDTO;
import com.andre.apicalculos.entity.dto.TransacaoRecordDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    private final TransacaoService transacaoService;

    public EstatisticaRecordDTO calcularEstatisticas(Integer intervaloBusca) {
        log.info("Iniciando busca de transações para cálculo de estatisticas.");

        List<TransacaoRecordDTO> listaTransacoes = transacaoService.obterTransacoes(intervaloBusca);

        if(listaTransacoes.isEmpty()) {
            return new EstatisticaRecordDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = listaTransacoes.stream()
                .mapToDouble(TransacaoRecordDTO::valor)
                .summaryStatistics();

        log.info("Calculando estatisticas.");

        return new EstatisticaRecordDTO(
                estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
