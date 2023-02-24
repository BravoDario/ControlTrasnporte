module org.utl.dsm.examendos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens org.utl.dsm.examendos to javafx.fxml;
    exports org.utl.dsm.examendos;
}