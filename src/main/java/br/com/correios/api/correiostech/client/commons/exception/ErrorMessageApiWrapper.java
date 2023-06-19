package br.com.correios.api.correiostech.client.commons.exception;

import java.util.List;

public class ErrorMessageApiWrapper {
    private String msg;
    private List<String> msgs;
    private String causa;
    private String path;

    public ErrorMessageApiWrapper() {
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String var1) {
        this.msg = var1;
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
