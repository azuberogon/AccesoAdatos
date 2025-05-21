package org.example.Service;

import org.example.dao.ComercialDAO;
import org.example.entity.ComercialEntity;

public class ComercialService extends GenericServiceImpl<ComercialEntity, Long>{

    public ComercialService() {
        super(new ComercialDAO());
    }
}
