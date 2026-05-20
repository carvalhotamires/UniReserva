package com.uniesp.unireserva.service.impl;

import com.uniesp.unireserva.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements com.uniesp.unireserva.service.interfaces.ReportService {
    private final ReservationRepository reservationRepository;

    @Override
    public Map<String, Object> getEstatisticasGerais(LocalDate inicio, LocalDate fim) {
        Map<String, Object> estatisticas = new HashMap<>();

        long totalReservas = reservationRepository.countByReservationDateBetween(inicio, fim);

        estatisticas.put("totalReservas", totalReservas);
        estatisticas.put("periodo", inicio + " ate " + fim);

        return estatisticas;
    }

    @Override
    public byte[] gerarPdfReservasPorPeriodo(LocalDate inicio, LocalDate fim) {
        return new byte[0];
    }

    @Override
    public byte[] gerarRelatorioReservas(LocalDate inicio, LocalDate fim) {
        return new byte[0];
    }
}