package br.com.correios.api.correiostech.usecase.prazo;

import br.com.correios.api.correiostech.client.prazo.PrazoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsultaUmPrazoUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaUmPrazoUseCase.class);

    private final PrazoClient prazoClient;

    public ConsultaUmPrazoUseCase(PrazoClient prazoClient) {
        this.prazoClient = prazoClient;
    }

    public PrazoOutput executa(ConsultaUmPrazoInput input) {
        final var codigoProduto = input.codigoProduto();
        final var cepOrigem = input.cepOrigem();
        final var cepDestino = input.cepDestino();

        final var prazoResponse = prazoClient.getPrazo(codigoProduto, cepOrigem, cepDestino);
        LOGGER.debug("PrazoResponse: {}", prazoResponse);

        final var prazo = prazoResponse.toDomain();
        LOGGER.debug("Prazo: {}", prazo);

        return PrazoOutput.from(prazo);
    }
}
