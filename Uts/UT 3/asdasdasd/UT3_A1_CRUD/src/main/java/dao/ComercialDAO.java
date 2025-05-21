package dao;

import model.Comercial;

public class ComercialDAO extends GenericDAOImpl<Comercial, Long> {
    public ComercialDAO() {
        super(Comercial.class);
    }
}