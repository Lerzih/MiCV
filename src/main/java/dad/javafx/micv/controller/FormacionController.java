package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Formacion;
import dad.javafx.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class FormacionController implements Initializable {
	
	CV cv = new CV();
	
	private ObjectProperty<Formacion> formacion = new SimpleObjectProperty<>();
	
	public final ObjectProperty<Formacion> formacionProperty() {
		return this.formacion;
	}

	public final Formacion getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final Formacion formacion) {
		this.formacionProperty().set(formacion);
	}
	
	// ----- Elementos FXML -----
	
	// ----- Vista -----
    
	@FXML
	private HBox view;
	
    private HBox getView() {
    	return this.view;
    }
    
    // ----- Tabla -----

    @FXML
    private TableView<Formacion> tblViewFormacion;

    @FXML
    private TableColumn<Formacion, LocalDate> colDesde;

    @FXML
    private TableColumn<Formacion, LocalDate> colHasta;

    @FXML
    private TableColumn<Formacion, String> colDenominacion;

    @FXML
    private TableColumn<Formacion, String> colOrganizador;
    
    // ----- Constructor Controlador -----
    
    public FormacionController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	
    	formacion.addListener((o, ov, nv) -> onContactoChanged(o, ov, nv));
    	
    	cv.getFormacion().denominacionProperty().bindBidirectional();
		
	}
    
    private Object onContactoChanged(ObservableValue<? extends Formacion> o, Formacion ov, Formacion nv) {
		// TODO Auto-generated method stub
		return null;
	}

	// ----- Botones -----

    @FXML
    private Button btnAddFormacion;

    @FXML
    private Button btnDeleteFormacion;
    
    
    
    // ----- Funcion Boton Add -----

    @FXML
    void onAddFormacion(ActionEvent event) {

    }
    
    // ----- Funcion Boton Delete -----

    @FXML
    void onDeleteFormacion(ActionEvent event) {

    }

	

}
