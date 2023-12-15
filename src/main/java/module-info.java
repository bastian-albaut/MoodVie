module org.openjfx.javafxmavenarchetypes {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires Dotenv;




    opens com.moodvie to javafx.fxml;
    opens com.moodvie.controller to javafx.fxml;

    exports com.moodvie;
}