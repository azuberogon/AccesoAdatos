package service;
import dao.ComercialDAO;
import model.Comercial;


import dao.ComercialDAO;
import model.Comercial;

import java.util.List;

public class ComercialService {
    private final ComercialDAO comercialDAO;

    public ComercialService() {
        this.comercialDAO = new ComercialDAO();
    }

    /**
     * Obtener todos los comerciales.
     */
    public List<Comercial> obtenerTodos() {
        return comercialDAO.obtenerTodos();
    }

    /**
     * Guardar un comercial.
     */
    public void guardar(Comercial comercial) {
        comercialDAO.guardar(comercial);
    }

    /**
     * Actualizar un comercial existente.
     */
    public void actualizar(Comercial comercial) {
        comercialDAO.actualizar(comercial);
    }

    /**
     * Eliminar un comercial por ID.
     */
    public void eliminar(Long id) {
        comercialDAO.eliminar(id);
    }
}
