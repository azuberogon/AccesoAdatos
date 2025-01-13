package dao;

import model.Videojuego;

public class VideojuegoDAO extends GenericDAOImpl<Videojuego, Integer> {
    public VideojuegoDAO() {
        super(Videojuego.class);
    }
}

