package br.com.correios.api.correiostech.client.commons.exception.decoder;

import br.com.correios.api.correiostech.client.commons.exception.ErrorMessageApiWrapper;

public interface ErrorMessageApiDecoder<T> {

    Class<T> getClazz();

    ErrorMessageApiWrapper toErrorMessageApiWrapper(String var1, T var2);
}
