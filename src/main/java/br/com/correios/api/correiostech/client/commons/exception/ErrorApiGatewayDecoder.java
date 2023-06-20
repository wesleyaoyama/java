package br.com.correios.api.correiostech.client.commons.exception;

import br.com.correios.api.correiostech.client.commons.exception.decoder.ErrorMessageApiDecoder;
import br.com.correios.api.correiostech.client.commons.exception.decoder.ErrorMessageApiGatewayDecoder;
import br.com.correios.api.correiostech.client.token.exception.ApiClientErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ErrorApiGatewayDecoder implements ErrorDecoder {
    private Logger log = LoggerFactory.getLogger(ErrorApiGatewayDecoder.class);
    private ErrorMessageApiDecoder errorMessageApiDecoder;

    public ErrorApiGatewayDecoder() {
        this.errorMessageApiDecoder = new ErrorMessageApiGatewayDecoder();
    }

    public ErrorApiGatewayDecoder(ErrorMessageApiDecoder<?> var1) {
        this.errorMessageApiDecoder = var1;
    }

    public Exception decode(String var1, Response var2) {
        Response.Body var3 = var2.body();
        ErrorMessageApiWrapper var4 = null;
        this.log.debug("decode key: {}, status: {}, ", new Object[]{var1, var2.status(), var2.toString()});
        String var5 = null;
        if (var3 != null) {
            try (InputStream bodyIs = var2.body().asInputStream()) {

                var5 = StreamUtils.copyToString(bodyIs, StandardCharsets.UTF_8);

            } catch (IOException e) {
                var5 = "Nao foi possivel converter response body para string. message %s".formatted(e.getMessage());
            }

            if (var5 != null && var5.length() > 0) {
                try {
                    this.log.debug("body length: {}, {}", var3.length(), var5);
                    ObjectMapper var6 = new ObjectMapper();
                    var4 = this.errorMessageApiDecoder.toErrorMessageApiWrapper(var5, var6.readValue(var5, MessageApiGateway.class));
                } catch (Exception var7) {
                    this.log.error("Não foi possível decodificar a mensagem de erro.\nMensagem de erro não está padronizada.\nOu implemente uma classe ErrorMessageApiDecoder personalizada.\nJson retornado: {} \nexception: {}", var5, var7.getMessage());
                    var4 = new ErrorMessageApiWrapper();
                    var4.setMsg(var5);
                }
            } else {
                this.log.warn("Não foi possível decodificar a mensagem de erro. Corpo(body) vazio.");
            }
        } else {
            this.log.warn("Não foi possível decodificar a mensagem de erro. Corpo(body) vazio.");
        }

        if (var2.status() == 429) {
        }

        if (var2.status() >= 400 && var2.status() <= 499) {
            return this.newApiClientErrorException(var2, var4);
        } else if (var2.status() >= 500 && var2.status() <= 599) {
            return this.newApiClientErrorException(var2, var4);
        } else {
            FeignException var9 = FeignException.errorStatus(var1, var2);
            return var9;
        }
    }

    private ApiClientErrorException newApiClientErrorException(Response var1, ErrorMessageApiWrapper var2) {
        ApiClientErrorException var3 = null;
        if (var2 != null) {
            var3 = new ApiClientErrorException(var1.status(), var2.getMsg(), var2.getMsgs(), var2.getCausa(), var2.getPath());
        } else {
            var3 = new ApiClientErrorException(var1.status(), var1.reason());
        }

        return var3;
    }
}
