package br.com.correios.api.correiostech.client.prepostagem.model;

import java.util.List;

public record CriaPrePostagemRequest(
        String codigoServico,
        String numeroNotaFiscal,
        String numeroCartaoPostagem,
        String chaveNFe,
        List<ServicoAdicionalRequest> listaServicoAdicional,
        List<DeclaracaoConteudoRequest> itensDeclaracaoConteudo,
        String pesoInformado,
        String alturaInformada,
        String larguraInformada,
        String comprimentoInformado,
        String diametroInformado,
        String codigoFormatoObjetoInformado,
        Integer cienteObjetoNaoProibido,
        String solicitarColeta,
        String logisticaReversa,
        PessoaRequest destinatario,
        PessoaRequest remetente
) {
}
