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

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TutoriaGrupal extends Tutoria{
    @FXML
    public ComboBox<String> leertutoriaComboBox;
    @FXML
    private TextField TemaTextField;

    @FXML
    private TextField TiempoTextField;

    @FXML
    private TextField matriculasTextField;

    @FXML
    private ComboBox<String> alumnoComboBox;

    @FXML
    private TextField nombreTextField;

    @FXML
    private ComboBox<String> crearTutoriaComboBox;

    @FXML
    private Button regsitrartutoriagrupalButton;

    @FXML
    private ComboBox<String > actualizartutoriasComboBox;

    private List<Tutoria> tutoriasGrupales = new ArrayList<>();

    public TutoriaGrupal(List<AlumnoAgregar> alumnos, String descripcion, LocalDateTime fechaHoraRegistro, int tiempo, String tema, String nombreGrupo) {
        super(alumnos, descripcion, fechaHoraRegistro, tiempo, tema, nombreGrupo);
    }

    @FXML
    void onMouseClickregistrartutoriagrupalButton(MouseEvent event) {
        registrarTutoriaGrupal();
    }

    private void registrarTutoriaGrupal() {

        String nombreGrupo = nombreTextField.getText().trim();
        String matriculas = matriculasTextField.getText().trim();
        String tema = TemaTextField.getText().trim();
        String tiempoStr = TiempoTextField.getText().trim();
        if (nombreGrupo.isEmpty() || matriculas.isEmpty() || tema.isEmpty() || tiempoStr.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        try {
            int tiempo = Integer.parseInt(tiempoStr);
            String[] matriculasArray = matriculas.split(",");
            List<AlumnoAgregar> alumnos = new ArrayList<>();
            for (String matricula : matriculasArray) {
                AlumnoAgregar alumno = Alumno.getInstance().buscarAlumnoPorMatricula(matricula.trim());
                if (alumno != null) {
                    alumnos.add(alumno);
                }
            }
            if (alumnos.isEmpty()) {
                mostrarAlerta("Error", "No se encontró ningún alumno con las matrículas ingresadas.");
                return;
            }
            LocalDateTime fechaHoraRegistro = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String fechaHoraFormateada = fechaHoraRegistro.format(formatter);
            Tutoria tutoriaGrupal = new Tutoria(alumnos, tema, fechaHoraRegistro, tiempo, tema, nombreGrupo);
            tutoriasGrupales.add(tutoriaGrupal);
            System.out.println("Tamaño de las tutorías grupales creadas: " + tutoriasGrupales.size());
            StringBuilder mensaje = new StringBuilder("Tutoría Grupal Registrada\n");
            mensaje.append("Grupo: ").append(nombreGrupo).append("\n");
            mensaje.append("Tema: ").append(tema).append("\n");
            mensaje.append("Duración de la tutoría: ").append(tiempo).append(" minutos\n");
            mensaje.append("Fecha y Hora de Registro: ").append(fechaHoraFormateada).append("\n");
            mensaje.append("Alumnos:\n");
            for (AlumnoAgregar alumno : alumnos) {
                mensaje.append("- Nombre: ").append(alumno.getNombre()).append("\n");
                mensaje.append("  Matrícula: ").append(alumno.getMatricula()).append("\n");
                mensaje.append("  Grupo: ").append(alumno.getGrupo()).append("\n");
                mensaje.append("  Género: ").append(alumno.getGenero()).append("\n");
            }

            mostrarAlerta("Tutoría Grupal Registrada", mensaje.toString());
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El tiempo debe ser un número entero válido.");
        }
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        matriculasTextField.clear();
        TemaTextField.clear();
        TiempoTextField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        regsitrartutoriagrupalButton.setStyle("-fx-background-color: darkblue;");
        regsitrartutoriagrupalButton.setEffect(new DropShadow());
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        regsitrartutoriagrupalButton.setStyle("-fx-background-color: blue;");
        regsitrartutoriagrupalButton.setEffect(null);
    }

    @FXML
    void initialize() {
        alumnoComboBox.getItems().addAll("Agregar Alumno", "Buscar Alumno", "Eliminar Alumno", "Actualizar Alumno");
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
        leertutoriaComboBox.getItems().addAll("Leer Tutoría Individual", "Leer Tutoría Grupal", "Tutorias Atendidas", "Tutorias No Atendidas");
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
                    case "Tutorias Atendidas":
                        abrirVentanaTutoriasAtendidas();

                        break;
                    case "Tutorias No Atendidas":

                        break;
                }
            }
        });
    }

    private void abrirVentanaTutoriasAtendidas() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/multidiciplinario/TutoriasAtendidas.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleComboBoxSelection(ActionEvent event) {
        String selectedOption = alumnoComboBox.getValue();
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

    private void abrirVentanaTutoriaIndividual(){
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

    private void abrirVentanaTutoriaGrupal(){
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
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
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
            LeerTutoriaGrupal leerTutoriaGrupalController = fxmlLoader.getController();
            leerTutoriaGrupalController.setTutoriasGrupales(tutoriasGrupales);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Tutoria> getTutoriasGrupales() {
        ObservableList<Tutoria> tutoriasObservableList = FXCollections.observableArrayList(tutoriasGrupales);
        return tutoriasObservableList;
    }

}
