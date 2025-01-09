package org.example;
import org.example.entity.ClienteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Crear la SessionFactory desde el archivo de configuración de Hibernate
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Abrir una sesión
        Session session = sessionFactory.openSession();

        // Verificar si la sesión se inició correctamente
        if (session != null) {
            System.out.println("Se inició la sesión");
        } else {
            System.out.println("Error abriendo la sesión");
        }

        ClienteEntity cliente = new ClienteEntity();
        cliente.setApellido1("juan");


        // Cerrar la sesión y la SessionFactory (opcional, pero recomendado)
        if (session != null) {
            session.close();
        }
        sessionFactory.close();
    }
}
