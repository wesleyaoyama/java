package br.com.correios.api.correiostech.client.prepostagem.model;

public record PessoaResponse(
        Long id,
        String nome,
        String dddTelefone,
        String telefone,
        String dddCelular,
        String celular,
        String email,
        String cpfCnpj,
        EnderecoResponse endereco
) {
}
