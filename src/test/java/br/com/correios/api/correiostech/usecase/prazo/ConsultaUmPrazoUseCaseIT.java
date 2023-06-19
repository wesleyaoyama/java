package br.com.correios.api.correiostech.usecase.prazo;

import br.com.correios.api.correiostech.configuration.WebServerConfig;
import br.com.correios.api.correiostech.domain.prazo.EntregaAosSabado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled // nao executa no CI
@ActiveProfiles("test")
@SpringBootTest(classes = WebServerConfig.class)
class ConsultaUmPrazoUseCaseIT {

    @Autowired
    private ConsultaUmPrazoUseCase useCase;

    @Test
    void givenAValidParam_whenCallsConsultaUmPrazoUseCase_thenReturnPrazo() {
        // given
        final var expectedCodigoProduto = "04162";
        final var cepOrigem = "70902000";
        final var cepDestino = "71930000";
        final var expectedPrazoEntrega = 1;
        final var expectedEntregaSabado = EntregaAosSabado.SIM;

        final var input = new ConsultaUmPrazoInput(expectedCodigoProduto, cepOrigem, cepDestino);

        // when
        final var actualOutput = useCase.executa(input);

        // then
        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedCodigoProduto, actualOutput.codigoProduto());
        Assertions.assertEquals(expectedPrazoEntrega, actualOutput.prazoEmDias());
        Assertions.assertEquals(expectedEntregaSabado, actualOutput.entregaAosSabado());
    }
}