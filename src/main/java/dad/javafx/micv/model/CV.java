package dad.javafx.micv.model;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CV {
	
	// ----- Personal -----

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}
	
	// ----- Contacto -----
	
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>(new Contacto());

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	
	// ----- Formacion -----
	
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
	
}



