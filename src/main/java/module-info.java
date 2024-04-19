module com.example.multidiciplinario {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.multidiciplinario to javafx.fxml;
    exports com.example.multidiciplinario;
    exports com.example.multidiciplinario.Controllers;
    opens com.example.multidiciplinario.Controllers to javafx.fxml;
    exports com.example.multidiciplinario.Models;
    opens com.example.multidiciplinario.Models to javafx.fxml;
}