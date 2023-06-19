package br.com.correios.api.correiostech.client.prepostagem.model;

import java.util.List;

public record GeraRotuloRequest(
        List<String> idsPrePostagem,
        String tipoRotulo,
        String formatoRotulo
) {
}
