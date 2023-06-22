package br.com.correios.api.correiostech.client.prazo;

import br.com.correios.api.correiostech.client.commons.ApiCorreiosRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "prazoClient",
        url = "${correios.client.prazo-url}",
        configuration = ApiCorreiosRequestConfiguration.class)
public interface PrazoClient {

    @GetMapping(value = "v1/nacional/{codigoProduto}")
    PrazoResponse getPrazo(
            @PathVariable("codigoProduto") String codigoProduto,
            @RequestParam("cepOrigem") String cepOrigem,
            @RequestParam("cepDestino") String cepDestino
    );
}
