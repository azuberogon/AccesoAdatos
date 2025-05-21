package dao;

import model.Contiene;
import model.ContieneID;

public class ContieneDAO extends GenericDaoImpl<Contiene, ContieneID> {
    public ContieneDAO() {
        super(Contiene.class);
    }
}
