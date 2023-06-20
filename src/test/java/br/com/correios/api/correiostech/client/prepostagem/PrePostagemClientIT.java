package br.com.correios.api.correiostech.client.prepostagem;

import br.com.correios.api.correiostech.client.prepostagem.model.*;
import br.com.correios.api.correiostech.client.token.exception.ApiClientErrorException;
import br.com.correios.api.correiostech.configuration.WebServerConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@Disabled // nao executa no CI
@ActiveProfiles("test")
@SpringBootTest(classes = WebServerConfig.class)
class PrePostagemClientIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrePostagemClientIT.class);

    @Autowired
    private PrePostagemClient prePostagemClient;

    @Test
    void givenAValidParam_whenCallsCreatePrePostagem_thenReturnPrePostagemWithId() {
        // given
        final var request = new CriaPrePostagemRequest(
                "04162",
                "12345789012",
                "",
                "12345678901234567890123456789012345678901234",
                List.of(new ServicoAdicionalRequest("001")),
                List.of(new DeclaracaoConteudoRequest("Produto XYZ", 10, 190.23)),
                "1000",
                "1",
                "8",
                "13",
                "0",
                "2",
                1,
                "N",
                "N",
                new PessoaRequest(
                        "Nome do destinatário de exemplo",
                        "11",
                        "12345678",
                        "11",
                        "912345678",
                        "email@exemplocorreios.com.br",
                        "00000000191",
                        new EnderecoRequest(
                                "70002900",
                                "Endereço do destinatário de exemplo",
                                "123456",
                                "complemento do destinatário de exemplo",
                                "bairro do destinatário",
                                "cidade do destinatário",
                                "DF"
                        )

                ),
                new PessoaRequest(
                        "Nome do remetente de exemplo",
                        "11",
                        "12345678",
                        "11",
                        "912345678",
                        "email@exemplocorreios.com.br",
                        "00000000191",
                        new EnderecoRequest(
                                "70002900",
                                "Endereço do remetente de exemplo",
                                "123456",
                                "complemento do remetente de exemplo",
                                "bairro do remetente",
                                "cidade do remetente",
                                "DF"
                        )

                )
        );

        // when
        final var actualResponse = prePostagemClient.cria(request);

        // then
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.id());

        LOGGER.debug("CriaPrePostagemResponse {}",actualResponse);
    }

    @Test
    void givenAValidId_whenCallsCancel_thenReturnMessage() {
        // given
        final var expectedMessage = "Cancelamento realizado com sucesso!";
        final var request = newCriaPrePostagemRequest();

        final var criaPrePostagemResponse = prePostagemClient.cria(request);

        Assertions.assertNotNull(criaPrePostagemResponse.id());

        final var idPrePostagem = criaPrePostagemResponse.id();

        // when
        final var response = prePostagemClient.cancela(idPrePostagem);

        // then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedMessage, response.resultadoCancelamento());
    }

    @Test
    void givenAValidParam_whenCallsGeraRotulo_thenReturnRecibo() {
        // given
        final var criaPrePostagemRequest = newCriaPrePostagemRequest();
        final var criaPrePostagemResponse = prePostagemClient.cria(criaPrePostagemRequest);

        Assertions.assertNotNull(criaPrePostagemResponse.id());

        final var idPrePostagem = criaPrePostagemResponse.id();

        final var request = new GeraRotuloRequest(
                List.of(idPrePostagem),
                "P",
                "ET"
        );

        // when
        final var actualResponse = prePostagemClient.geraRotulo(request);

        // then
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.idRecibo());
        Assertions.assertFalse(actualResponse.idRecibo().isBlank());
    }

    @Test
    void givenAnInvalidRecibo_whenCallsGetRotulo_thenReturnRotulo() throws InterruptedException {
        // given
        final var idRecibo = "recibo-invalido";
        final var expectedErrorMessage = "Recibo id recibo-invalido não localizado no cache. Solicite nova geração do rótulo.";

        // when
        final var actualException = Assertions.assertThrows(ApiClientErrorException.class, () -> prePostagemClient.getRotulo(idRecibo));

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(400, actualException.status());
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    void givenAValidIdRecibo_whenCallsGetRotulo_thenReturnRotulo() throws InterruptedException {
        // given
        final var criaPrePostagemRequest = newCriaPrePostagemRequest();
        final var criaPrePostagemResponse = prePostagemClient.cria(criaPrePostagemRequest);

        Assertions.assertNotNull(criaPrePostagemResponse.id());

        final var idPrePostagem = criaPrePostagemResponse.id();

        final var request = new GeraRotuloRequest(
                List.of(idPrePostagem),
                "P",
                "ET"
        );
        final var rotulo = prePostagemClient.geraRotulo(request);
        final var idRecibo = rotulo.idRecibo();
        Thread.sleep(500);

        // when
        final var actualResponse = prePostagemClient.getRotulo(idRecibo);

        // then
        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.nome());
        Assertions.assertFalse(actualResponse.dados().isBlank());
    }

    private CriaPrePostagemRequest newCriaPrePostagemRequest() {
        return new CriaPrePostagemRequest(
                "04162",
                "12345789012",
                "",
                "12345678901234567890123456789012345678901234",
                List.of(new ServicoAdicionalRequest("001")),
                List.of(new DeclaracaoConteudoRequest("Produto XYZ", 10, 190.23)),
                "1000",
                "1",
                "8",
                "13",
                "0",
                "2",
                1,
                "N",
                "N",
                new PessoaRequest(
                        "Nome do destinatário de exemplo",
                        "11",
                        "12345678",
                        "11",
                        "912345678",
                        "email@exemplocorreios.com.br",
                        "00000000191",
                        new EnderecoRequest(
                                "70002900",
                                "Endereço do destinatário de exemplo",
                                "123456",
                                "complemento do destinatário de exemplo",
                                "bairro do destinatário",
                                "cidade do destinatário",
                                "DF"
                        )

                ),
                new PessoaRequest(
                        "Nome do remetente de exemplo",
                        "11",
                        "12345678",
                        "11",
                        "912345678",
                        "email@exemplocorreios.com.br",
                        "00000000191",
                        new EnderecoRequest(
                                "70002900",
                                "Endereço do remetente de exemplo",
                                "123456",
                                "complemento do remetente de exemplo",
                                "bairro do remetente",
                                "cidade do remetente",
                                "DF"
                        )

                )
        );
    }
}