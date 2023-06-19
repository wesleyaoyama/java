package br.com.correios.api.correiostech.client.commons.exception.decoder;

import br.com.correios.api.correiostech.client.commons.exception.ErrorMessageApiWrapper;
import br.com.correios.api.correiostech.client.commons.exception.MessageApiGateway;

public class ErrorMessageApiGatewayDecoder implements ErrorMessageApiDecoder<MessageApiGateway> {
    public ErrorMessageApiGatewayDecoder() {
    }

    public ErrorMessageApiWrapper toErrorMessageApiWrapper(String var1, MessageApiGateway var2) {
        ErrorMessageApiWrapper var3 = new ErrorMessageApiWrapper();
        var3.setMsg((String) var2.getMsgs().get(0));
        var3.setMsgs(var2.getMsgs());
        var3.setCausa(var2.getCausa());
        var3.setPath(var2.getPath());
        return var3;
    }

    public Class<MessageApiGateway> getClazz() {
        return MessageApiGateway.class;
    }
}
