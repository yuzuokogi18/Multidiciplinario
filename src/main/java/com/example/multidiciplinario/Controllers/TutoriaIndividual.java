package com.example.multidiciplinario.Controllers;
import com.example.multidiciplinario.Models.AlumnoAgregar;
import com.example.multidiciplinario.Models.Tutoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TutoriaIndividual extends Tutoria {
    @FXML
    public ComboBox<String> leertutoriaComboBox;
    public ComboBox eliminartutoriaComboBox;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TiempoTextField;
    @FXML
    private ComboBox<AlumnoAgregar> alumnoComboBox;
    @FXML
    private ComboBox<String> crearTutoriaComboBox;
    @FXML
    private TextField descripcionTextField;


    @FXML
    private Button registrarTutoriaButton;
    private List<Tutoria> tutoriasIndividuales;

    public void eliminarTutoria(Tutoria tutoria) {
        tutoriasIndividuales.remove(tutoria);
    }

    @FXML
    private TextField MatriculaTextField;
    @FXML
    private ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();
    @FXML
    private List<AlumnoAgregar> listaAlumnos = new ArrayList<>();
    @FXML
    private TableView<Tutoria> tutoriasTableView;

    public TutoriaIndividual() {
        super((AlumnoAgregar) null, null, null, 0);
    }


    private TutoriaIndividual tutoriaIndividualController;


    public void actualizarListaAlumnos(List<AlumnoAgregar> listaAlumnos) {
        this.listaAlumnos.clear();
        this.listaAlumnos.addAll(listaAlumnos);
    }

    public int obtenerTamanoListaTutorias() {
        return tutorias.size();
    }

    public ObservableList<Tutoria> getTutorias() {
        return tutorias;
    }


    @FXML
    void onMouseClickregistrartutoriaButton(ActionEvent event) {
        registrarTutoria();
    }

    private void registrarTutoria() {
        String descripcion = descripcionTextField.getText().trim();
        String tiempoStr = TiempoTextField.getText().trim();
        String matricula = MatriculaTextField.getText().trim();

        // Validar que todos los campos estén llenos
        if (descripcion.isEmpty() || tiempoStr.isEmpty() || matricula.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        try {
            int tiempo = Integer.parseInt(tiempoStr);
            LocalDateTime fechaHoraRegistro = LocalDateTime.now();
            AlumnoAgregar alumno = buscarAlumnoPorMatricula(matricula);
            if (alumno == null) {
                mostrarAlerta("Error", "No se encontró ningún alumno con esa matrícula.");
                return;
            }
            Tutoria tutoria = new Tutoria(alumno, descripcion, fechaHoraRegistro, tiempo);

            tutorias.add(tutoria);
            alumno.agregarTutoria(tutoria); // Agregar la tutoría al alumno correspondiente
            mostrarAlerta("Tutoría Registrada", "Tutoría creada correctamente.");
            System.out.println("Tamaño de la lista de tutorías creadas: " + obtenerTamanoListaTutorias());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El tiempo debe ser un número entero válido.");
        }


    }

    private AlumnoAgregar buscarAlumnoPorMatricula(String matricula) {
        for (AlumnoAgregar alumno : listaAlumnos) {
            if (alumno.getMatricula().equals(matricula)) {
                return alumno;
            }
        }
        return null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        registrarTutoriaButton.setStyle("-fx-background-color: darkblue;");
        registrarTutoriaButton.setEffect(new DropShadow());
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        registrarTutoriaButton.setStyle("-fx-background-color: blue;");
        registrarTutoriaButton.setEffect(null);
    }


    @FXML
    void initialize() {
        alumnoComboBox.getItems().addAll(Alumno.getInstance().getListaAlumnos());
        alumnoComboBox.setOnAction(this::handleComboBoxSelection);
        crearTutoriaComboBox.getItems().addAll("Tutoría Individual", "Tutoría Grupal");
        crearTutoriaComboBox.setOnAction(event -> {
            String selectedOption = (String) crearTutoriaComboBox.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Tutoría Individual":
                        abrirVentanaTutoriaIndividual();
                        break;
                    case "Tutoría Grupal":
                        abrirVentanaTutoriaGrupal();
                        break;
                }
            }
        });

        eliminartutoriaComboBox.getItems().addAll("Eliminar Tutoría Individual", "Eliminar Tutoría Grupal");
        eliminartutoriaComboBox.setOnAction(event -> {
            String selectedOption = (String) eliminartutoriaComboBox.getSelectionModel().getSelectedItem();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Eliminar Tutoría Individual":
                        abrirVentanaEliminarTutoriaIndividual();

                        break;
                    case "Eliminar Tutoría Grupal":

                        break;
                }
            }
        });
        leertutoriaComboBox.getItems().addAll("Leer Tutoría Individual", "Leer Tutoría Grupal", "Tutorias Atendidas y no Atendidas", "Agendar Cita", "Buscar Tutoria");
        leertutoriaComboBox.setOnAction(event -> {
            String selectedOption = leertutoriaComboBox.getValue();
            if (selectedOption != null) {
                switch (selectedOption) {
                    case "Leer Tutoría Individual":
                        abrirVentanaLeerTutorias();
                        break;
                    case "Leer Tutoría Grupal":
                        abrirVentanaLeerTutoriaGrupal();
                        break;
                    case "Tutorias Atendidas y no Atendidas":
                        abrirVentanaTutoriasAtendidasynoAtendidas();
                        break;
                    case "Agendar Cita":
                        abrirVentanaAgendarCita();
                        break;
                    case "Buscar Tutoria":
                        abrirVentanaBuscarTutoria();

                }
            }
        });

    }


    public void handleComboBoxSelection(ActionEvent event) {
        String selectedOption = String.valueOf(alumnoComboBox.getValue());
        switch (selectedOption) {
            case "Agregar Alumno":
                abrirVentanaAgregarAlumno();
                break;
            case "Buscar Alumno":
                abrirVentanaBuscarAlumno();
                break;
            case "Eliminar Alumno":
                abrirVentanaEliminarAlumno();
                break;
            case "Actualizar Alumno":
                abrirVentanaActualizarAlumno();
                break;
            default:
                break;
        }
    }

    private void abrirVentanaAgendarCita() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/AgendarCita.fxml"));
            fxmlLoader.setControllerFactory(t -> new AgendarCita());
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaBuscarTutoria() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/BuscarTutoria.fxml"));
            fxmlLoader.setControllerFactory(t -> new BuscarTutoria());
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaAgregarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/AgregarAlumno.fxml"));
            fxmlLoader.setControllerFactory(t -> new AgregarAlumno());
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaBuscarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/BuscarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaEliminarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/EliminarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaActualizarAlumno() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/ActualizarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaTutoriaIndividual() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/TutoriaIndividual.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaTutoriaGrupal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/TutoriaGrupal.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaLeerTutorias() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/LeerTutoriaIndividual.fxml"));
            Parent root = fxmlLoader.load();
            LeerTutoriaIndividual leerTutoriaIndividualController = fxmlLoader.getController();
            leerTutoriaIndividualController.setTutoriaIndividualController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            leerTutoriaIndividualController.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaLeerTutoriaGrupal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/LeerTutoriaGrupal.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVentanaEliminarTutoriaIndividual() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/EliminarTutoriaIndividual.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirVentanaTutoriasAtendidasynoAtendidas() {
        Stage existingStage = findExistingStage(TutoriasAtendidas.class);
        if (existingStage != null) {
            TutoriasAtendidas controller = (TutoriasAtendidas) existingStage.getScene().getUserData();
            if (controller != null) {
                controller.setAlumnosConTutorias(listaAlumnos);
                existingStage.show(); // Mostrar la ventana existente
                return;
            }
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/TutoriasAtendidas.fxml"));
            Parent root = fxmlLoader.load();
            TutoriasAtendidas controller = fxmlLoader.getController();
            // Pasar los datos al controlador
            controller.setAlumnosConTutorias(listaAlumnos);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage findExistingStage(Class<?> controllerClass) {
        for (Window stage : Stage.getWindows()) {
            if (stage.getUserData() instanceof ModuleLayer.Controller) {
                if (stage.getUserData().getClass().equals(controllerClass)) {
                    return (Stage) stage;
                }
            }
        }
        return null;
    }

    public List<Tutoria> buscarTutoriasPorMatricula(String matricula) {
        List<Tutoria> tutoriasEncontradas = new ArrayList<>();
        if (this.tutoriaIndividualController != null) {
            for (Tutoria tutoria : tutorias) {
                if (tutoria.getAlumno().getMatricula().equals(matricula)) {
                    tutoriasEncontradas.add(tutoria);
                }
            }
        } else {
            System.out.println("Error: tutoriaIndividualController es nulo");
        }
        return tutoriasEncontradas;
    }
}
