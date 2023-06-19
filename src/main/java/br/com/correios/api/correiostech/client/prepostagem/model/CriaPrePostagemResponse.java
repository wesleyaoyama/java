package br.com.correios.api.correiostech.client.prepostagem.model;

import java.util.List;

public record CriaPrePostagemResponse(
        String id,
        PessoaResponse remetente,
        PessoaResponse destinatario,
        String idCorreios,
        String numeroCartaoPostagem,
        String codigoObjeto,
        String codigoServico,
        List<ServicoAdicionalResponse> listaServicoAdicional,
        Integer modalidadePagamento,
        String numeroNotaFiscal,
        String chaveNFe,
        List<DeclaracaoConteudoResponse> itensDeclaracaoConteudo,
        String pesoInformado,
        String codigoFormatoObjetoInformado,
        String alturaInformada,
        String larguraInformada,
        String comprimentoInformado,
        String diametroInformado,
        Integer cienteObjetoNaoProibido,
        String solicitarColeta,
        String logisticaReversa,
        Integer statusAtual,
        String dataHoraStatusAtual,
        String descStatusAtual,
        String dataHora,
        String tipoRotulo,
        String sistemaOrigem,
        String tipoObjeto,
        String prazoPostagem
) {
}
