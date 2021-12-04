module ch.teko.prg3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens ch.teko.prg3 to javafx.fxml;
    opens ch.teko.prg3.kontakt to javafx.fxml;
    opens ch.teko.prg3.login to javafx.fxml;
    exports ch.teko.prg3;
    exports ch.teko.prg3.login;
    exports ch.teko.prg3.kontakt;
}