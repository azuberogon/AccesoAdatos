package service;

import dao.ContieneDAO;
import model.Contiene;
import model.ContieneID;

public class ContieneService extends GenericServiceImpl<Contiene, ContieneID>{
    public ContieneService()
    {
        super(new ContieneDAO());
    }
}
