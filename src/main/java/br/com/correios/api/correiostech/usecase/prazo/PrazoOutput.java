package br.com.correios.api.correiostech.usecase.prazo;

import br.com.correios.api.correiostech.domain.prazo.EntregaAosSabado;
import br.com.correios.api.correiostech.domain.prazo.Prazo;

import java.time.LocalDateTime;

public record PrazoOutput(
        String codigoProduto,
        Integer prazoEmDias,
        LocalDateTime entregaAte,
        EntregaAosSabado entregaAosSabado
) {

    public static PrazoOutput from(final Prazo prazo) {
        return new PrazoOutput(prazo.getCodigoProduto(), prazo.getPrazoEmDias(), prazo.getEntregaAte(), prazo.getEntregaSabado());
    }
}
