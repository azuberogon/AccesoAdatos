package service;

import dao.HistoriaDAO;
import model.Historia;

public class HistoriaService extends GenericServiceImpl<Historia, Integer> {
    public HistoriaService() {
        super(new HistoriaDAO());
    }
}