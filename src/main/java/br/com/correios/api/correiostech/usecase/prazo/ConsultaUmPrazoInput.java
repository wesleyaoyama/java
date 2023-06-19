package br.com.correios.api.correiostech.usecase.prazo;

public record ConsultaUmPrazoInput(
        String codigoProduto,
        String cepOrigem,
        String cepDestino
) {
}
