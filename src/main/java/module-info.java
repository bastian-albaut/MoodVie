module org.openjfx.javafxmavenarchetypes {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.google.gson;
    requires java.sql;
    requires Dotenv;




    opens com.moodvie to javafx.fxml;
    opens com.moodvie.persistance.model to javafx.base, com.google.gson;
    opens com.moodvie.controller to javafx.fxml;
    opens com.moodvie.controller.component.navBar to javafx.fxml;
    opens com.moodvie.controller.userController to javafx.fxml;
    opens com.moodvie.controller.watchLaterController to javafx.fxml;
    opens com.moodvie.controller.logController to javafx.fxml;
    opens com.moodvie.controller.filmController to javafx.fxml;

    exports com.moodvie;
}