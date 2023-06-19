package br.com.correios.api.correiostech.client.prazo.model;

import br.com.correios.api.correiostech.domain.prazo.EntregaAosSabado;
import br.com.correios.api.correiostech.domain.prazo.Prazo;

import java.time.LocalDateTime;

public record PrazoResponse(
        String coProduto,
        Integer prazoEntrega,
        String dataMaxima,
        String entregaDomiciliar,
        String entregaSabado
) {
    public Prazo toDomain() {
        final var entregaAte = LocalDateTime.parse(dataMaxima);
        final var entregaAosSabados = EntregaAosSabado.of(entregaSabado).orElse(null);
        return new Prazo(coProduto, prazoEntrega, entregaAte, entregaAosSabados);
    }
}
