package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contiene")
public class Contiene {
    @EmbeddedId
    private ContieneID contieneID = new ContieneID(); // Clave compuesta
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoID")
    @JoinColumn(name = "pedidoID", nullable = false)
    private Pedido pedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videojuegoID")
    @JoinColumn(name = "videojuegoID", nullable = false)
    private Videojuego videojuego;
}