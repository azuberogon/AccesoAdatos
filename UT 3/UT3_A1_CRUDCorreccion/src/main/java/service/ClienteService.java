package service;
import dao.ClienteDAO;
import model.Cliente;
public class ClienteService extends GenericServiceImpl<Cliente, Long>{
    public ClienteService() {
        super(new ClienteDAO());
    }


}
