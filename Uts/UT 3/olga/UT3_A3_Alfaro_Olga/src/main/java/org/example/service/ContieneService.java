package org.example.service;

import org.example.dao.ContieneDAO;
import org.example.model.Contiene;
import org.example.model.ContieneID;

public class ContieneService extends GenericServiceImpl<Contiene, ContieneID> {
    public ContieneService(){
        super(new ContieneDAO());
    }
}
