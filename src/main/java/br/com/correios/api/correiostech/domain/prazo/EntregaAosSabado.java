package br.com.correios.api.correiostech.domain.prazo;

import java.util.Arrays;
import java.util.Optional;

public enum EntregaAosSabado {

    SIM("S"), NAO("N");


    private final String codigo;

    EntregaAosSabado(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Optional<EntregaAosSabado> of(final String codigo) {
        return Arrays.stream(EntregaAosSabado.values())
                .filter(it -> it.codigo.equals(codigo))
                .findFirst();
    }
}
