package org.example.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GenericUtils {
    // Método para truncar el valor de un String
    public static String limitarString(String parametro, Integer tamanoMaximo) {
        if (parametro.length() > tamanoMaximo)
            return parametro.substring(0, tamanoMaximo - 3) + "...";
        return parametro;
    }

    // Método para verificar si el valor es válido para un campo DECIMAL(M,N), teniendo en cuenta
    //      únicamente la parte entera (la decimal se truncaría automáticamente por MySQL)
    public static boolean esValidoParaDecimal(Double valor, Integer enteros) {
        // Obtener la parte entera del valor
        BigDecimal parteEntera = BigDecimal.valueOf(valor).setScale(0, RoundingMode.DOWN);

        return parteEntera.precision() <= enteros;
    }
}
