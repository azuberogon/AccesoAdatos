package dao;

import model.Comercial;

public class ComercialDAO extends GenericDaoImpl<Comercial,Long> {
    public ComercialDAO(){
        super(Comercial.class);
    }
}
