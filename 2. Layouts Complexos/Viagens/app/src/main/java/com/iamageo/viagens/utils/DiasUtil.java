package com.iamageo.viagens.utils;

import com.iamageo.viagens.model.Pacote;

import org.jetbrains.annotations.NotNull;

public class DiasUtil {

    public static final String PLURAL = " dias";
    public static final String SINGULAR = " dia";

    @NotNull
    public static String getDiasEmTexto(int dias) {

        if(dias > 1) {
            return dias + PLURAL;
        }
        return dias + SINGULAR;
    }

}
