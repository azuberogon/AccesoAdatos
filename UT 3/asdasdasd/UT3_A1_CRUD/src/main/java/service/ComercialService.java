package service;

import dao.ComercialDAO;
import model.Comercial;


public class ComercialService extends GenericServiceImpl<Comercial, Long> {
    public ComercialService() {
        super(new ComercialDAO());
    }
}

