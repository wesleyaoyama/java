package br.com.correios.api.correiostech.client.prepostagem.model;

public record DeclaracaoConteudoRequest(
        String conteudo,
        Integer quantidade,
        Double valor
) {
}
