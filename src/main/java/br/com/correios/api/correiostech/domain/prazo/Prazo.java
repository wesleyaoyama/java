package br.com.correios.api.correiostech.domain.prazo;

import java.time.LocalDateTime;

public class Prazo {

    private final String codigoProduto;
    private final Integer prazoEmDias;
    private final LocalDateTime entregaAte;
    private final EntregaAosSabado entregaAosSabado;


    public Prazo(String codigoProduto, Integer prazoEmDias, LocalDateTime entregaAte, EntregaAosSabado entregaAosSabado) {
        this.codigoProduto = codigoProduto;
        this.prazoEmDias = prazoEmDias;
        this.entregaAte = entregaAte;
        this.entregaAosSabado = entregaAosSabado;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public Integer getPrazoEmDias() {
        return prazoEmDias;
    }

    public LocalDateTime getEntregaAte() {
        return entregaAte;
    }

    public EntregaAosSabado getEntregaSabado() {
        return entregaAosSabado;
    }

    @Override
    public String toString() {
        return "Prazo{" +
                "codigoProduto='" + codigoProduto + '\'' +
                ", prazoEmDias=" + prazoEmDias +
                ", entregaAte=" + entregaAte +
                ", entregaAosSabado=" + entregaAosSabado +
                '}';
    }
}
