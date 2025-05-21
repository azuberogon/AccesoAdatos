package service;

import dao.VideojuegoDAO;
import model.Videojuego;

public class VideojuegoService extends GenericServiceImpl<Videojuego, Integer> {
    public VideojuegoService() {
        super(new VideojuegoDAO());
    }
}
