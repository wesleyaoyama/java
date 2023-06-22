package br.com.correios.api.correiostech.client.prazo;

public record PrazoResponse(
        String coProduto,
        Integer prazoEntrega,
        String dataMaxima,
        String entregaDomiciliar,
        String entregaSabado
) {
}
