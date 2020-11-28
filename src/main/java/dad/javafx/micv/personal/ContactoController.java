package dad.javafx.micv.personal;

import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Contacto;
import dad.javafx.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

public class ContactoController implements Initializable {
	
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>();
	
	@FXML
    private SplitPane view;

    @FXML
    private Button btnAddTelefono;

    @FXML
    private Button btnDeleteTelefono;

    @FXML
    private Button btnAddCorreo;

    @FXML
    private Button btnDeleteCorreo;

    @FXML
    private Button btnAddWeb;

    @FXML
    private Button btnDeleteWeb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

}
