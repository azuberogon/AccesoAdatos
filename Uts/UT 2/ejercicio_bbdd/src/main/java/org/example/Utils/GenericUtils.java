package org.example.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GenericUtils {

    // Método para truncar el valor de un String
    public static String limitarString(String cadena, int maxLength) {
        if (cadena == null) {
            return null;
        }
        if (cadena.length() <= maxLength) {
            return cadena;
        }
        return cadena.substring(0, maxLength - 3) + "...";
    }

    // Método para verificar si el valor es válido para un campo DECIMAL(M,N), teniendo en cuenta
// únicamente la parte entera (la decimal se truncará automáticamente por MySQL)
    public static boolean esValidoParaDecimal(Double valor, Integer enteros) {
        if (valor == null || enteros == null || enteros < 0) {
            return false; // Null or negative precision is not valid
        }

        // Obtener la parte entera del valor como BigDecimal
        BigDecimal parteEntera = BigDecimal.valueOf(Math.abs(valor)).setScale(0, RoundingMode.DOWN);

        // Verificar si el número de dígitos en la parte entera es menor o igual al valor permitido
        return parteEntera.precision() <= enteros;
    }

}
