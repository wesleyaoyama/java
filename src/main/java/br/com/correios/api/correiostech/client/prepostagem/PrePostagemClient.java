package br.com.correios.api.correiostech.client.prepostagem;

import br.com.correios.api.correiostech.client.commons.ApiCorreiosRequestConfiguration;
import br.com.correios.api.correiostech.client.prepostagem.model.CriaPrePostagemRequest;
import br.com.correios.api.correiostech.client.prepostagem.model.CriaPrePostagemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "prePostagemClient", url = "${correios.client.prepostagem-url}",
        configuration = ApiCorreiosRequestConfiguration.class)
public interface PrePostagemClient {

    @PostMapping(value = "v1/prepostagens")
    CriaPrePostagemResponse create(@RequestBody CriaPrePostagemRequest request);
}
