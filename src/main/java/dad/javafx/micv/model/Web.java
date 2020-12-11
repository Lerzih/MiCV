package dad.javafx.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Web {
	
	private StringProperty url = new SimpleStringProperty();
	
	public final StringProperty webProperty() {
		
		return this.url;	
	}

	public final String getWeb() {
		
		return this.webProperty().get();
	}

	public final void setWeb(final String web) {
		
		this.webProperty().set(web);
	}

}
