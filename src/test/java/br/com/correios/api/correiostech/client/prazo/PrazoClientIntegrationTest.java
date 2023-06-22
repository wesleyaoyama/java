package br.com.correios.api.correiostech.client.prazo;

import br.com.correios.api.correiostech.client.token.exception.ApiClientErrorException;
import br.com.correios.api.correiostech.configuration.WebServerConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled
@ActiveProfiles("test")
@SpringBootTest(classes = {WebServerConfig.class})
public class PrazoClientIntegrationTest {

    @Autowired
    private PrazoClient prazoClient;


    @Test
    void givenValidParams_whenCallsGetPrazo_thenReturnPrazo() {
        // given
        final var codigoProduto = "04162";
        final var cepOrigem = "70902000";
        final var cepDestino = "71930000";

        // when
        final var actualPrazo = prazoClient.getPrazo(
                codigoProduto, cepOrigem, cepDestino
        );

        // then
        Assertions.assertNotNull(actualPrazo);
        Assertions.assertEquals(codigoProduto, actualPrazo.coProduto());
        Assertions.assertNotNull(actualPrazo.prazoEntrega());
        Assertions.assertNotNull(actualPrazo.dataMaxima());
        Assertions.assertNotNull(actualPrazo.entregaDomiciliar());
        Assertions.assertNotNull(actualPrazo.entregaSabado());

        final var codigoProduto2 = "04162";
        final var cepOrigem2 = "70902000";
        final var cepDestino2 = "05336010";

        // when
        final var actualPrazo2 = prazoClient.getPrazo(
                codigoProduto2, cepOrigem2, cepDestino2
        );
    }

    @Test
    void givenInvalidParam_whenCallsGetPrazo_thenReturnException() {
        // given
        final var codigoProduto = "04162";
        final var cepOrigem = "70902000";
        final var cepDestino = "";
        final var expectedErrorMessage = "PRZ-116: Parâmetro 'cepDestino' obrigatório para obter o prazo.";

        // when
        final var actualException = Assertions.assertThrows(
                ApiClientErrorException.class,
                () -> prazoClient.getPrazo(
                        codigoProduto, cepOrigem, cepDestino
                ));

        // then
        Assertions.assertNotNull(actualException);
        final var actualMessage = actualException.getMsgs().get(0);
        Assertions.assertEquals(expectedErrorMessage, actualMessage);
    }
}
