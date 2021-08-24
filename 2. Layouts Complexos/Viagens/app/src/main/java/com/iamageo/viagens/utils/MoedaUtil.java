package com.iamageo.viagens.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

    public static final String LINGUA = "pt";
    public static final String PAIS = "br";

    public static String getMoedaFormatada(BigDecimal valor) {
        NumberFormat formatoBrasileiro = DecimalFormat.getCurrencyInstance(new Locale(LINGUA, PAIS));
        return formatoBrasileiro.format(valor);
    }

}
