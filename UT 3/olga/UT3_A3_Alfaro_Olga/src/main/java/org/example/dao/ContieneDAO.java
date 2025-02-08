package org.example.dao;

import org.example.model.ContieneID;
import org.example.model.Contiene;

public class ContieneDAO extends GenericDAOImpl<Contiene, ContieneID> {
    public ContieneDAO(){
        super(Contiene.class);
    }

}
