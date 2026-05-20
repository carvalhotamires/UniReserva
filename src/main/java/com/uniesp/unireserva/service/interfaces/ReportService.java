package com.uniesp.unireserva.service.interfaces;

import java.time.LocalDate;
import java.util.Map;

public interface ReportService {
    Map<String, Object> getEstatisticasGerais(LocalDate inicio, LocalDate fim);

    byte[] gerarPdfReservasPorPeriodo(LocalDate inicio, LocalDate fim);

    byte[] gerarRelatorioReservas(LocalDate inicio, LocalDate fim);
}
