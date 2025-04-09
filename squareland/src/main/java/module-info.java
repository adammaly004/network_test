module cz.cvut.fel.malyada1.squareland {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.cvut.fel.malyada1.squareland to javafx.fxml;
    exports cz.cvut.fel.malyada1.squareland;
}