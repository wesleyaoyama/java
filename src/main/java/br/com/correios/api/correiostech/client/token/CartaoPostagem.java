package br.com.correios.api.correiostech.client.token;

import java.util.List;

public class CartaoPostagem {

    private String numero;
    private String contrato;
    private Integer dr;
    private List<Integer> api;

    public CartaoPostagem() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public List<Integer> getApi() {
        return api;
    }

    public void setApi(List<Integer> api) {
        this.api = api;
    }
}
