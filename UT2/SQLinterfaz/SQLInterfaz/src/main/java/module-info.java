module org.example.sqlinterfaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.sqlinterfaz to javafx.fxml;
    opens org.example.sqlinterfaz.dto to javafx.base; // Abre el paquete dto para que javafx acceda a las propiedades
    exports org.example.sqlinterfaz;
}
