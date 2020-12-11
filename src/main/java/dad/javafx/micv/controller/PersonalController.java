package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import dad.javafx.micv.utils.CargaPaisesNacionalidades;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {
	
	// ----- Cargando Elementos Necesarios -----
	
	CV cv = new CV();

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	private ObjectProperty<Nacionalidad> nacionalidad = new SimpleObjectProperty<Nacionalidad>();

	// ----- Elementos FXML -----

	@FXML
	private GridPane view;

	@FXML
	private TextField identificacionText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private DatePicker fechaNacimientoDate;

	@FXML
	private TextArea direccionText;

	@FXML
	private TextField codigoPostalText;

	@FXML
	private TextField localidadText;

	@FXML
	private ListView<Nacionalidad> nacionalidadesList;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private Button nuevaNacionalidadButton;

	@FXML
	private Button quitarNacionalidadButton;

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		paisCombo.getItems().setAll(CargaPaisesNacionalidades.cargarPaises());
		
		quitarNacionalidadButton.disableProperty().
			bind(nacionalidadesList.getSelectionModel().selectedItemProperty().isNull());
		
		nacionalidad.bind(nacionalidadesList.getSelectionModel().selectedItemProperty());

		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));
		
		cv.getPersonal().identificacionProperty().bindBidirectional(identificacionText.textProperty());
		cv.getPersonal().nombreProperty().bindBidirectional(nombreText.textProperty());
		cv.getPersonal().apellidosProperty().bindBidirectional(apellidosText.textProperty());
		cv.getPersonal().fechaNacimientoProperty().bindBidirectional(fechaNacimientoDate.valueProperty());
		cv.getPersonal().direccionProperty().bindBidirectional(direccionText.textProperty());
		cv.getPersonal().codigoPostalProperty().bindBidirectional(codigoPostalText.textProperty());
		cv.getPersonal().localidadProperty().bindBidirectional(localidadText.textProperty());
		cv.getPersonal().paisProperty().bindBidirectional(paisCombo.valueProperty());
		cv.getPersonal().nacionalidadesProperty().bindBidirectional(nacionalidadesList.itemsProperty());
		
	}
	
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		System.out.println("ov=" + ov + "/nv=" + nv);
		
		if (ov != null) {
			
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			
			// Desbindeos propiedades faltantes
			
			direccionText.textProperty().unbindBidirectional(ov.direccionProperty());
			codigoPostalText.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			localidadText.textProperty().unbindBidirectional(ov.localidadProperty());
			nacionalidadesList.itemsProperty().unbindBidirectional(ov.nacionalidadesProperty());
			paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
		}
		
		if (nv != null) {
			
			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			
			// Bindeos propiedades faltantes
			
			direccionText.textProperty().bindBidirectional(nv.direccionProperty());
			codigoPostalText.textProperty().bindBidirectional(nv.codigoPostalProperty());
			localidadText.textProperty().bindBidirectional(nv.localidadProperty());
			nacionalidadesList.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
		}
		
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) {
		
		List<String> choices = new ArrayList<>();
		choices.addAll(CargaPaisesNacionalidades.cargarNacionalidades());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Nueva nacionalidad");
		dialog.setHeaderText("Añadir nueva nacionalidad");
		dialog.setContentText("Seleccione una nacionalidad");

		Optional<String> result = dialog.showAndWait();
		Nacionalidad aux = new Nacionalidad();
		aux.setDenominacion(result.get());
		cv.getPersonal().getNacionalidades().add(aux);
	}

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) {
		cv.getPersonal().getNacionalidades().remove(nacionalidad.get());
	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

}
