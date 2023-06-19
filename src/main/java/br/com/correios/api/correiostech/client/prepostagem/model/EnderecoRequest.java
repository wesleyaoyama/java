package br.com.correios.api.correiostech.client.prepostagem.model;

public record EnderecoRequest(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf
) {
}
