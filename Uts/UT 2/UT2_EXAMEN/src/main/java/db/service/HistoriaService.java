package db.service;

import constants.GenericConstants;
import db.dao.HistoriaDAO;
import db.model.Historia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.GenericUtils;

import static constants.HistoriaConstants.ERROR_TITULO_NULO;
import static constants.HistoriaConstants.TABLE_TITULO_MAX_LONG;

public class HistoriaService extends GenericServiceImpl<Historia, Integer> {

    private static final Logger logger = LogManager.getLogger(HistoriaService.class);

    public HistoriaService() {
        super(new HistoriaDAO());
    }

    @Override
    public int insertar(Historia historia) {
        int comprobarHistoria = comprobarHistoria(historia);
        if (comprobarHistoria != 0)
            return comprobarHistoria;
        return super.insertar(historia);
    }

    @Override
    public int actualizar(Historia historia) {
        int comprobarHistoria = comprobarHistoria(historia);
        if (comprobarHistoria != 0)
            return comprobarHistoria;
        return super.actualizar(historia);
    }

    private int comprobarHistoria(Historia historia) {
        if (historia == null) {
            logger.warn("No se pueden insertar historias nulas");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Comprobar el título
        if (historia.getTitulo() == null
                || historia.getTitulo().isEmpty()
                || historia.getTitulo().isBlank()) {
            logger.warn("No se admiten títulos nulos o vacíos");
            return ERROR_TITULO_NULO;
        } else if (historia.getTitulo().length() > TABLE_TITULO_MAX_LONG)
            historia.setTitulo(GenericUtils.limitarString(historia.getTitulo(), TABLE_TITULO_MAX_LONG));

        return 0;
    }
}
