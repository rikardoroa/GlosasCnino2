package application;





import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


   public class Entidad {

	
	private IntegerProperty idEntidad;
	private StringProperty  Nit;
	private StringProperty  Entidad;

	
	
	public Entidad (Integer idEntidad,String Entidad,  String Nit ) {
	this.idEntidad=new SimpleIntegerProperty (idEntidad);
	this.Nit=  new SimpleStringProperty (Nit);
	this.Entidad= new SimpleStringProperty (Entidad);   
	}
	
	
	public Integer getIdEntidad() {
		return idEntidad.get();
	}

	public  void  setIdEntidad (Integer idEntidad) {
		this.idEntidad = new SimpleIntegerProperty (idEntidad);
	}
	
	
	public String getNit() {
		return Nit.get();
	}

	public  void  setNit(String Nit) {
		this.Nit=new SimpleStringProperty (Nit);
	}
	
	
	public String getEntidad() {
		return Entidad.get();
	}

	public  void  setEntidad(String Entidad) {
		this.Entidad=new SimpleStringProperty (Entidad);
	}
		
	
}
