package br.com.correios.api.correiostech.client.token.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

public class ApiClientErrorException extends FeignException {
    private Logger log = LoggerFactory.getLogger(ApiClientErrorException.class);
    protected List<String> msgs;
    protected String causa;
    protected String path;

    public ApiClientErrorException(int var1, String var2, List<String> var3, String var4, String var5) {
        super(var1, var2);
        this.msgs = var3;
        this.causa = var4;
        this.path = var5;
        this.log.debug("ApiClientErrorException msgs: {}, causa: {}, path: {}", new Object[]{var3, var4, var5});
    }

    public ApiClientErrorException(int var1, String var2) {
        super(var1, var2);
        this.log.debug("ApiClientErrorException status: {}, message: {}, ", var1, var2);
    }

    public List<String> getMsgs() {
        return this.msgs;
    }

    public void setMsgs(List<String> var1) {
        this.msgs = var1;
    }

    public String getCausa() {
        return this.causa;
    }

    public void setCausa(String var1) {
        this.causa = var1;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String var1) {
        this.path = var1;
    }
}
