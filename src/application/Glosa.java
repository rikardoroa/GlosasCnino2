package application;


import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Glosa {
	
	private IntegerProperty idglosa;
	private ObjectProperty <LocalDate> fecharecibido;
	private StringProperty mes;
	private StringProperty nit;
	private StringProperty entidad;
	private IntegerProperty numerofactura;
	private IntegerProperty valorglosa;
	private StringProperty tipoobjecion;
	private ObjectProperty <LocalDate> fechaentregaauditoria;
	private StringProperty usuario;
	private StringProperty observacion;
	
	

	public Glosa (Integer idglosa, LocalDate fecharecibido,  String mes, String nit, String entidad, Integer numerofactura, Integer valorglosa ,String tipoobjecion ,  LocalDate fechaentregaauditoria,  String usuario, String observacion) {
		this.idglosa=new SimpleIntegerProperty (idglosa);
		 this.fecharecibido= new SimpleObjectProperty<>(fecharecibido);
		 this.mes=  new SimpleStringProperty (mes);
	     this.nit= new SimpleStringProperty ( nit);
	     this.entidad= new SimpleStringProperty ( entidad);
	     this.numerofactura=new SimpleIntegerProperty (numerofactura);
	     this.valorglosa=new SimpleIntegerProperty (valorglosa);
	     this.tipoobjecion= new SimpleStringProperty ( tipoobjecion);
	     this.fechaentregaauditoria= new SimpleObjectProperty<>(fechaentregaauditoria);
	     this.usuario=  new SimpleStringProperty (usuario);
	     this.observacion= new SimpleStringProperty ( observacion);
	    
	}

public Integer getIdglosa() {
return idglosa.get();
}

public  void  setIdglosa(Integer idglosa) {
this.idglosa = new SimpleIntegerProperty (idglosa);
}
	
	
public LocalDate getFechaRecibido() {
return fecharecibido.get();
}

public void setFechaRecibido(LocalDate fecharecibido) {
this.fecharecibido = new SimpleObjectProperty<>(fecharecibido);
}

public ObjectProperty<LocalDate> fechaRecibidoProperty() {
return fecharecibido;
}
		

public String getMes() {
return mes.get();
}

public  void  setMes(String mes) {
this.mes=new SimpleStringProperty (mes);
}

public String getNit() {
return nit.get();
}

public  void  setNit(String nit) {
this.nit=new SimpleStringProperty ( nit);
}


public String getEntidad() {
return entidad.get();
}

public  void  setEntidad(String entidad) {
this.entidad=new SimpleStringProperty ( entidad);
}

public Integer getNumerofactura() {
	return numerofactura.get();
}

public  void  setNumerofactura (Integer numerofactura) {
	this.numerofactura = new SimpleIntegerProperty (numerofactura);
}


public Integer getValorglosa() {
	return valorglosa.get();
}

public  void setValorglosa(Integer valorglosa) {
	this.valorglosa = new SimpleIntegerProperty (valorglosa);
}


public String getTipoobjecion() {
return tipoobjecion.get();
}

public  void  setTipoobjecion (String tipoobjecion) {
this.tipoobjecion = new SimpleStringProperty ( tipoobjecion);
}



public LocalDate getFechaEntregaAuditoria() {
return fechaentregaauditoria.get();
}

public void setFechaEntregaAuditoria(LocalDate fechaentregaauditoria) {
this.fechaentregaauditoria = new SimpleObjectProperty<>(fechaentregaauditoria);
}

public ObjectProperty<LocalDate> fechaEntregaAuditoriaProperty() {
return fechaentregaauditoria;
}
	

public String getUsuario() {
return usuario.get();
}

public  void  setUsuario(String usuario) {
this.usuario=new SimpleStringProperty (usuario);
}

public String getObservacion() {
return observacion.get();
}

public  void  setObservacion(String observacion) {
this.observacion=new SimpleStringProperty (observacion);
}

}