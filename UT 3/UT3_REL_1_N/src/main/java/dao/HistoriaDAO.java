package dao;

import model.Historia;

public class HistoriaDAO extends GenericDAOImpl<Historia, Integer> {
    public HistoriaDAO() {
        super(Historia.class);
    }
}
