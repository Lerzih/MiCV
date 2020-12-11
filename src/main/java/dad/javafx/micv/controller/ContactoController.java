package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.model.Contacto;
import dad.javafx.micv.model.Correo;
import dad.javafx.micv.model.Telefono;
import dad.javafx.micv.model.TipoTelefono;
import dad.javafx.micv.model.Web;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ContactoController implements Initializable {
	
	// ----- Carga de elementos necesarios -----
	
	CV cv = new CV();
    
    public CV getCV() {
    	
    	return this.cv;
    }
	
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>();
	
	// ----- Constructor -----
	
	public ContactoController () throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	// ----- Elementos del FXML -----
	
	@FXML
    private SplitPane view;

    @FXML
    private TableView<Telefono> tblViewTelefono;

    @FXML
    private TableColumn<Telefono, String> colNumero;

    @FXML
    private TableColumn<Telefono, TipoTelefono> colTipo;

    @FXML
    private Button btnAddTelefono;

    @FXML
    private Button btnDeleteTelefono;

    @FXML
    private TableView<Correo> tblViewCorreo;

    @FXML
    private TableColumn<Correo, String> colEmail;

    @FXML
    private Button btnAddCorreo;

    @FXML
    private Button btnDeleteCorreo;

    @FXML
    private TableView<Web> tblViewWeb;

    @FXML
    private TableColumn<Web, String> colUrl;

    @FXML
    private Button btnAddWeb;

    @FXML
    private Button btnDeleteWeb;
    
    // ----- Initialize -----

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		contacto.addListener((o, ov, nv) -> onContactoChanged(o, ov, nv));
		
		// ----- Bindeo de las TableView -----
		
		tblViewTelefono.itemsProperty().bindBidirectional(cv.contactoProperty().get().telefonoProperty());
		tblViewCorreo.itemsProperty().bindBidirectional(cv.contactoProperty().get().correoProperty());
		tblViewWeb.itemsProperty().bindBidirectional(cv.contactoProperty().get().webProperty());
		
		// ----- Bindeo de los Botones con los seleccionados -----
		
		btnDeleteTelefono.disableProperty().bind(tblViewTelefono.getSelectionModel().selectedItemProperty().isNull());
		btnDeleteCorreo.disableProperty().bind(tblViewCorreo.getSelectionModel().selectedItemProperty().isNull());
		btnDeleteWeb.disableProperty().bind(tblViewWeb.getSelectionModel().selectedItemProperty().isNull());
		
		// ----- Bindeo de las Columns -----
		
		// -- CellValueFactory --
		
		colNumero.setCellValueFactory(v -> v.getValue().numeroProperty());
		colTipo.setCellValueFactory(v -> v.getValue().tipoTelefonoProperty());
		
		colEmail.setCellValueFactory(v -> v.getValue().direccionProperty());
		
		colUrl.setCellValueFactory(v -> v.getValue().webProperty());
		
		// -- CellFactory --
		
		colNumero.setCellFactory(TextFieldTableCell.forTableColumn());
		colTipo.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));
		
		colNumero.setCellFactory(TextFieldTableCell.forTableColumn());
		
		colNumero.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	
	private void onContactoChanged(ObservableValue<? extends Contacto> o, Contacto ov, Contacto nv) {
		
		if (ov != null) {
			
			// ----- Desbindeos -----
			
			tblViewTelefono.itemsProperty().unbindBidirectional(ov.telefonoProperty());
			tblViewCorreo.itemsProperty().unbindBidirectional(ov.correoProperty());
			tblViewWeb.itemsProperty().unbindBidirectional(ov.webProperty());
		}
		
		if (nv != null) {
			
			// ----- Nuevos Bindeos -----
			
			tblViewTelefono.itemsProperty().bindBidirectional(nv.telefonoProperty());
			tblViewCorreo.itemsProperty().bindBidirectional(nv.correoProperty());
			tblViewWeb.itemsProperty().bindBidirectional(nv.webProperty());
		}
	}
	
	// ----- Contacto -----
	
	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	
	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	
	public SplitPane getView() {
		return this.view;
	}
	
	// ----- Funciones Botones Add -----
	
	@FXML
	void onAddTelefono(ActionEvent event) {
		
		Dialog<Pair<String, TipoTelefono>> dialog = new Dialog<>();
		
		((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/images/cv64x64.png"));
		
		dialog.setTitle("Nuevo Telefono");
		dialog.setHeaderText("Introduzca un nuevo número de teléfono.");
		
		ButtonType insertar = new ButtonType("Añadir", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(insertar, ButtonType.CANCEL);

		TextField numero = new TextField();
		numero.setPromptText("Numero");
		
		ComboBox<TipoTelefono> tipotelefono = new ComboBox<>();
		tipotelefono.getItems().addAll(TipoTelefono.values());
		tipotelefono.setPromptText("Seleccione un  Tipo");
		
		GridPane gridpane = new GridPane();
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.setPadding(new Insets(20, 150, 10, 10));
		gridpane.add(new Label("Número:"), 0, 0);
		gridpane.add(numero, 1, 0);
		gridpane.add(new Label("Tipo:"), 0, 1);
		gridpane.add(tipotelefono, 1, 1);
		
		dialog.getDialogPane().setContent(gridpane);
		
		dialog.setResultConverter(dialogButton -> {
			try {
				Integer.parseInt(numero.getText());
			} catch (NumberFormatException e) {
				return null;
			}
			if (tipotelefono.getValue() == null)
				return null;
			return new Pair<>(numero.getText(), tipotelefono.getValue());
		});
		
		
		Optional<Pair<String, TipoTelefono>> result = dialog.showAndWait();
		
		if(!result.isEmpty()) {
			Telefono aux = new Telefono();
			aux.setNumero(result.get().getKey());
			aux.setTipoTelefono(result.get().getValue());
			cv.getContacto().getTelefono().add(aux);
		}
	}
	
	@FXML
	void onAddCorreo(ActionEvent event) {
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo Email");
		dialog.setHeaderText("Crear una nueva direccion de correo.");
		dialog.setContentText("Email:");
		
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(
		    new Image(this.getClass().getResource("/images/cv64x64.png").toString()));

		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
		   Correo aux = new Correo();
		   aux.setDireccion(result.get());
		   cv.getContacto().getEmail().add(aux);
		}
	}
	
	@FXML
	void onAddWeb(ActionEvent event) {
		
		TextInputDialog dialog = new TextInputDialog("http://");
		dialog.setTitle("Nueva Web");
		dialog.setHeaderText("Crear una nueva direccion web.");
		dialog.setContentText("URL:");

		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
		   Web aux = new Web();
		   aux.setWeb(result.get());
		   cv.getContacto().getWeb().add(aux);
		}
	}
	
	// ----- Funciones Botones Delete -----
	
	@FXML
	void onDeleteTelefono(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar Teléfono");
		alert.setContentText("¿Desea borrar este teléfono?");
		
		Optional<ButtonType> accept = alert.showAndWait();
		
		if (accept.get().getText().equals("Aceptar")) {
			cv.getContacto().getTelefono().remove(tblViewTelefono.getSelectionModel().getSelectedIndex());
		}
	}
	
	@FXML
	void onDeleteCorreo(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar Email");
		alert.setContentText("¿Desea borrar este email?");
		
		Optional<ButtonType> accept = alert.showAndWait();
		
		if (accept.get().getText().equals("Aceptar")) {
			cv.getContacto().getEmail().remove(tblViewCorreo.getSelectionModel().getSelectedIndex());
		}
	}
	
	@FXML
	void onDeleteWeb(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar URL");
		alert.setContentText("¿Desea borrar esta URL?");
		
		Optional<ButtonType> accept = alert.showAndWait();
		
		if (accept.get().getText().equals("Aceptar")) {
			cv.getContacto().getWeb().remove(tblViewWeb.getSelectionModel().getSelectedIndex());
		}
	}
}
