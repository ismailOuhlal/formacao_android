package com.iamageo.viagens.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtils {

    public static final String DATA_E_MES = "dd/MM";

    public static String periodoEmTexto(int dias) {
        Calendar dataDeIda = Calendar.getInstance();
        Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, dias);
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat(DATA_E_MES);
        String dataFomatadaDeIda = formatoBrasileiro.format(dataDeIda.getTime());
        String dataFormatadaVolta = formatoBrasileiro.format(dataVolta.getTime());
        return dataFomatadaDeIda + " - " + dataFormatadaVolta + " de " + dataVolta.get(Calendar.YEAR);

    }
}
