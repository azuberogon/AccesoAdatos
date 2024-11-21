package org.example.db.service;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.example.constants.GenericConstants;
import org.example.db.dao.FacturaDao;
import org.example.db.model.Factura;
import org.example.utils.GenericUtils;

import java.util.List;

import static org.example.constants.FacturaConstants.*;

public class FacturaService extends GenericServiceImpl<Factura,Integer>{
    private static final Logger logger = LogManager.getLogger(FacturaService.class);

    public FacturaService() {
        super(new FacturaDao());
    }
    //mirar clase pero no se
    
    private  int comprobarFacturas(Factura factura){
        if (factura == null){
            logger.error("No se puede insertar valores nulos");
            return GenericConstants.ERROR_PARAM_NULL; // esto es el error dos nulles
        }
        if ( factura.getCodigo() == null){
            logger.error("No se damite un codigo nulo");
            return ERROR_CODIGO_NULO;
        }
        if (factura.getDestinatario() == null || factura.getDestinatario().isBlank()|| factura.getDestinatario().isBlank()){
            logger.error("la factura no puede estar en blanco ni ");
            return ERROR_DESTINATARIO_NULO;
        } else if (factura.getDestinatario().length() > TABLE_DESTINATARIO_MAX_LONG) {
            factura.setDestinatario(GenericUtils.limitarString(factura.getDestinatario(),
                    TABLE_DESTINATARIO_MAX_LONG));
        }

        // Comprobar la cuenta
        if (factura.getCuenta() == null) {
            logger.error("No se admite un número de cuenta nulo");
            return ERROR_CUENTA_NULO;
        }

        // Comprobar importe
        if (factura.getImporte() == null) {
            logger.error("No se admite importe nulo o vacío");
            return ERROR_IMPORTE_NULO;
        }
        else if (!GenericUtils.esValidoParaDecimal(factura.getImporte(), TABLE_IMPORTE_MAX_ENTEROS)) {
            logger.error("No se admiten importes con más de " + TABLE_IMPORTE_MAX_ENTEROS + " números enteros");
            return ERROR_IMPORTE_DEMASIADO_GRANDE;
        }

        // Comprobar fecha y hora
        if (factura.getFechaHora() == null) {
            logger.error("No se admiten fecha y hora nulos");
            return ERROR_FECHA_NULO;
        }

        return 0;
    }

}
