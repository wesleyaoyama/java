package br.com.correios.api.correiostech.client.prepostagem;

import br.com.correios.api.correiostech.client.prepostagem.model.*;
import br.com.correios.api.correiostech.configuration.WebServerConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@Disabled // nao executa no CI
@ActiveProfiles("test")
@SpringBootTest(classes = WebServerConfig.class)
class PrePostagemClientIT {

    @Autowired
    private PrePostagemClient prePostagemClient;

    @Test
    void givenAValidParam_whenCallsCreatePrePostagem_thenReturnPrePostagemWithId() {
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

        final var actualResponse = prePostagemClient.create(request);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertNotNull(actualResponse.id());
    }
}