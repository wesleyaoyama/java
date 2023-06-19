package br.com.correios.api.correiostech.client.prepostagem.model;

public record DeclaracaoConteudoResponse(
        String conteudo,
        Integer quantidade,
        Double valor
) {
}
