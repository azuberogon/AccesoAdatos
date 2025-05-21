package db.service;

import db.dao.FacturaDAO;
import db.model.Factura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.GenericUtils;
import constants.GenericConstants;
import static constants.FacturaConstants.*;

public class FacturaService extends GenericServiceImpl<Factura, Integer> {

    // Crea una instancia del Logger
    private static final Logger logger = LogManager.getLogger(FacturaService.class);

    public FacturaService() {
        super(new FacturaDAO());
    }

    @Override
    public int insertar(Factura factura) {
        int comprobarFactura = comprobarFactura(factura);
        if (comprobarFactura != 0)
            return comprobarFactura;

        return super.insertar(factura);
    }

    @Override
    public Factura obtenerPorId(Integer id) {
        if (id == null) {
            logger.error("No se puede buscar con valores nulos");
            return null;
        }
        return super.obtenerPorId(id);
    }

    @Override
    public int actualizar(Factura factura) {
        int comprobarFactura = comprobarFactura(factura);
        if (comprobarFactura != 0)
            return comprobarFactura;
        return super.actualizar(factura);
    }

    @Override
    public int eliminar(Integer id) {
        if (id == null) {
            logger.error("No se puede eliminar un código nulo");
            return GenericConstants.ERROR_PARAM_NULL;
        }
        return super.eliminar(id);
    }

    private int comprobarFactura(Factura factura) {
        // Comprobar Factura
        if (factura == null) {
            logger.error("No se pueden insertar valores nulos");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Comprobar el código
        if (factura.getCodigo() == null) {
            logger.error("No se admite un código nulo");
            return ERROR_CODIGO_NULO;
        }

        // Comprobar el destinatario
        if (factura.getDestinatario() == null
                || factura.getDestinatario().isEmpty()
                || factura.getDestinatario().isBlank()) {
            logger.error("El destinatario no puede estar vacío o ser nulo");
            return ERROR_DESTINATARIO_NULO;
        } else if (factura.getDestinatario().length() > TABLE_DESTINATARIO_MAX_LONG)
            factura.setDestinatario(GenericUtils.limitarString(factura.getDestinatario(),
                    TABLE_DESTINATARIO_MAX_LONG));

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
