package dad.javafx.micv.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {
	
	// ----- ListProperties -----
	
	private ListProperty<Telefono> telefono = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());
	private ListProperty<Correo> correo = new SimpleListProperty<Correo>(FXCollections.observableArrayList());
	private ListProperty<Web> web = new SimpleListProperty<Web>(FXCollections.observableArrayList());
	
	// ----- Telefono -----
	
	public final ListProperty<Telefono> telefonoProperty() {
		
		return this.telefono;
	}

	public final ObservableList<Telefono> getTelefono() {
		
		return this.telefonoProperty().get();
	}

	public final void setTelefono(final ObservableList<Telefono> telefono) {
		
		this.telefonoProperty().set(telefono);
	}
	
	// ----- Correo -----

	public final ListProperty<Correo> correoProperty() {
		
		return this.correo;
	}

	public final ObservableList<Correo> getEmail() {
		
		return this.correoProperty().get();
	}

	public final void setEmail(final ObservableList<Correo> Email) {
		
		this.correoProperty().set(Email);
	}
	
	// ----- Web -----
	
	public final ListProperty<Web> webProperty() {
		
		return this.web;
	}

	public final ObservableList<Web> getWeb() {
		
		return this.webProperty().get();
	}

	public final void setWeb(final ObservableList<Web> web) {
		
		this.webProperty().set(web);
	}

}
