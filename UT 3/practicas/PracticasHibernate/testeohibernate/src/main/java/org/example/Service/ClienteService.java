package org.example.Service;

import org.example.dao.ClienteDAO;
import org.example.dao.ComercialDAO;
import org.example.entity.ClienteEntity;
import org.example.entity.ComercialEntity;

public class ClienteService extends GenericServiceImpl<ClienteEntity, Long>{

        public ClienteService() {
                super(new ClienteDAO());
        }


}
