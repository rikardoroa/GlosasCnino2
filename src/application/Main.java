package application;
	
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class Main extends Application implements Initializable {
	    @FXML private TextField DatosEntidad;
	    @FXML private TextField NitEntidad;
	    @FXML private Button AgregarCombo;
	    @FXML private Button limpiartodo;
	    @FXML private Button limpiarcasillaentidad;
	    @FXML private TextField Nombreentidad;
	    @FXML private TextField Nitt;
	    @FXML private TextField tipoobjecion;
	    @FXML private TextField mes;
	    @FXML private TextField numfac;
	    @FXML private TextField valorfact;
	    @FXML private TextField observacion;
	    @FXML private TextField usuario;
	    @FXML private TextField numfacbusqueda;
		@FXML private DatePicker fecentrega;
		@FXML private DatePicker fecrecibido;
	    @FXML private Button buscanumfac;
	    @FXML private Button actualiza;
	    
	    @FXML TableView<Entidad>  tablaentidad;
	    @FXML TableColumn<Entidad, Integer > Miid;
	    @FXML TableColumn<Entidad, String > Mientidad;
	    @FXML TableColumn<Entidad, String > Minit;
	    
	    @FXML private Button mostrarentidad;
	    int dato;
	    
	    
	
	    

	@Override
	public void start(Stage stage) throws IOException {
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("Vista.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Oswald");
	        stage.setTitle("QUORA V1.0");
	        stage.setScene(scene);
	        stage.show();
    }
	
	public void seleccionaregistrostablaentidad() {
		ObservableList <Entidad> datostablaentidad =FXCollections.observableArrayList();
	      Connection conn=null;{
	          try {
	        	  conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	              Statement mostrar=conn.createStatement();
	              ResultSet rs;
	              rs= mostrar.executeQuery("select * from entidad");
	              while ( rs.next() ) 
	              {
	            	  datostablaentidad.add(new Entidad(
	                		 rs.getInt(1),
	                		 rs.getString("nombre"), 
	                		 rs.getString("nit")
	                		)
	                         );
	                 tablaentidad.setItems(datostablaentidad);
	              }
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }   
	       } 
	    }
	
	public void insertardatosentidad() {
		  Connection conn=null;
	         try {
	         if (DatosEntidad.getText().isEmpty() && NitEntidad.getText().isEmpty()||DatosEntidad.getText().isEmpty() ||NitEntidad.getText().isEmpty())  {
	            	 Alert alert = new Alert(AlertType.INFORMATION);
	                 alert.setTitle("Informacion");
	                 alert.setHeaderText(null);
	                 alert.setContentText("Los Datos no pueden estar Vacios!");
	                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	 	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	 	                 		"    -fx-border-radius: 5;\r\n" + 
	 	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	 	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	 	                 		"    -fx-text-fill:black;\r\n" + 
	 	                 		"    -fx-font-family: Oswald;\r\n" + 
	 	                 		"    -fx-font-size:15px;	");
	                 alert.showAndWait();
	             }
	             else {
	            	 conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
		             Statement insertar=conn.createStatement();
		             insertar.executeUpdate("insert into entidad (nombre,nit) values ('"+DatosEntidad.getText()+"','"+NitEntidad.getText()+"')");
	            	 Alert alert = new Alert(AlertType.INFORMATION);
	                 alert.setTitle("Informacion");
	                 alert.setHeaderText(null);
	                 alert.setContentText("Registro Insertado correctamente!");
	                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	 	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	 	                 		"    -fx-border-radius: 5;\r\n" + 
	 	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	 	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	 	                 		"    -fx-text-fill:black;\r\n" + 
	 	                 		"    -fx-font-family: Oswald;\r\n" + 
	 	                 		"    -fx-font-size:15px;	");
	                 alert.showAndWait();
	           }
	         } catch (SQLException e) {
	             e.printStackTrace();
	       }     
	    }
	

	public void seleccionaregistrosparaactualizar() {
		String cadena=numfacbusqueda.getText();
		Integer dato = null;
        String Consulta = "select * from auditoriac where numero_factura= ?" ;
	    Connection conn=null;{
	    	  try {
	    		  try {
		              dato = (Integer.parseInt(cadena));
		          } catch (NumberFormatException ex) {
		        	  Alert alert = new Alert(AlertType.INFORMATION);
	 	                 alert.setTitle("Informacion");
	 	                 alert.setHeaderText(null);
	 	                 alert.setContentText( "El campo no puede estar vacio, debe digitar el numero de factura" +ex);
	 	                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	 	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	 	                 		"    -fx-border-radius: 5;\r\n" + 
	 	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	 	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	 	                 		"    -fx-text-fill:black;\r\n" + 
	 	                 		"    -fx-font-family: Oswald;\r\n" + 
	 	                 		"    -fx-font-size:15px;	");
	 	                 alert.showAndWait();
		          }
	        	  conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	              PreparedStatement ps = conn.prepareStatement(Consulta);
	              ps.setInt(1, dato);
	              ResultSet rs =ps.executeQuery();
	              if (rs.next()) {
	            	  do {
	            		  java.sql.Date mifechauno = rs.getDate("fecha_recibido");
			              fecrecibido.setValue(mifechauno.toLocalDate());
			              String b= rs.getString("mes");
			              mes.setText(b);
			              String c= rs.getString("nit");
			              Nitt.setText(c);
			              String e= rs.getString("entidad");
			              Nombreentidad.setText(e);
		            	  String f= Integer.toString((rs.getInt("numero_factura")));
		            	  numfac.setText(f);
		            	  String g= Integer.toString((rs.getInt("valor_factura")));
		            	  valorfact.setText(g);
		            	  String h= rs.getString("tipo_objecion");
		            	  tipoobjecion.setText(h);
			              java.sql.Date mifechados = rs.getDate("fecha_entrega");
			              fecentrega.setValue(mifechados.toLocalDate());
			              String v= rs.getString("usuario");
			              usuario.setText(v);
			              String x= rs.getString("observaciones");
			              observacion.setText(x);   
	            	  }while ( rs.next() ) ;
	         	     Alert alert = new Alert(AlertType.INFORMATION);
	                 alert.setTitle("Informacion");
	                 alert.setHeaderText(null);
	                 alert.setContentText("Numero De Factura Encontrado!");
	                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	                 		"    -fx-border-radius: 5;\r\n" + 
	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	                 		"    -fx-text-fill:black;\r\n" + 
	                 		"    -fx-font-family: Oswald;\r\n" + 
	                 		"    -fx-font-size:15px;	");
	                 alert.showAndWait(); 	
		 	         }else {
	           	  Alert  alert = new Alert(AlertType.INFORMATION);
	 	                 alert.setTitle("Informacion");
	 	                 alert.setHeaderText(null);
	 	                 alert.setContentText("No se encontro un Numero de Factura Asociado en la Base de Datos");
	 	                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	 	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	 	                 		"    -fx-border-radius: 5;\r\n" + 
	 	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	 	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	 	                 		"    -fx-text-fill:black;\r\n" + 
	 	                 		"    -fx-font-family: Oswald;\r\n" + 
	 	                 		"    -fx-font-size:15px;	");
	 	                 alert.showAndWait();  
	                  }
	          } catch (SQLException e) {
	              e.printStackTrace();
	        }  
	     } 
	  }
	

	public void actualizardatos() throws ParseException {
		 Integer dato=null ;
		 String aa =((TextField)fecrecibido.getEditor()).getText();
		 String bb = mes.getText();
		 String cc = Nitt.getText();
		 String dd = Nombreentidad.getText();
		 Integer ee = null;
		 Integer ff = null;
		 String gg = tipoobjecion.getText();
		 String hh =((TextField)fecentrega.getEditor()).getText();
		 String jj = usuario.getText();
		 String kk = observacion.getText();
		 String fecha1=fecrecibido.getValue().toString();
		 String fecha2=fecentrega.getValue().toString();
		 
	
	    	 try {
	    		 dato = Integer.parseInt(numfacbusqueda.getText());
	    		 ee = Integer.parseInt(numfac.getText()); 
	    		 ff = Integer.parseInt(valorfact.getText());
	    	 }
	    	 catch(NumberFormatException ext){
	    		 Alert alert = new Alert(AlertType.INFORMATION);
	              alert.setTitle("Informacion");
	              alert.setHeaderText(null);
	              alert.setContentText("Campo Vacio, Por favor Digite el numero de Factura:" +ext);
	              alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	                 		"    -fx-border-radius: 5;\r\n" + 
	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	                 		"    -fx-text-fill:black;\r\n" + 
	                 		"    -fx-font-family: Oswald;\r\n" + 
	                 		"    -fx-font-size:15px;	");
	              alert.showAndWait();       
	    	     }
	    	 
	    	       if (fecha1.compareTo(fecha2)>0) {
	    	    	   Alert alert = new Alert(AlertType.INFORMATION);
		                 alert.setTitle("Informacion");
		                 alert.setHeaderText(null);
		                 alert.setContentText("La fecha de entrega no puede ser menor a la fecha de recibido!");
		                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
		 	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
		 	                 		"    -fx-border-radius: 5;\r\n" + 
		 	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
		 	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
		 	                 		"    -fx-text-fill:black;\r\n" + 
		 	                 		"    -fx-font-family: Oswald;\r\n" + 
		 	                 		"    -fx-font-size:15px;	");
		                 alert.showAndWait();    
	    	       }
	    	       else if(Nombreentidad.getText().isEmpty()||Nitt.getText().isEmpty()||fecentrega.getEditor().getText().isEmpty()||fecrecibido.getEditor().getText().isEmpty()||tipoobjecion.getText().isEmpty()||mes.getText().isEmpty()||usuario.getText().isEmpty()||observacion.getText().isEmpty()||numfac.getText().isEmpty()||valorfact.getText().isEmpty()) {
	        			 Alert alert = new Alert(AlertType.INFORMATION);
		                 alert.setTitle("Informacion");
		                 alert.setHeaderText(null);
		                 alert.setContentText("Por Favor Digite los Datos Del Formulario");
		                 alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
		 	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
		 	                 		"    -fx-border-radius: 5;\r\n" + 
		 	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
		 	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
		 	                 		"    -fx-text-fill:black;\r\n" + 
		 	                 		"    -fx-font-family: Oswald;\r\n" + 
		 	                 		"    -fx-font-size:15px;	");
		                 alert.showAndWait();  
	    	    	 }
	    	      else {
	    	    	  try {
	    	      Connection conn=null;	  
	        	  String Consulta = "update auditoriac set fecha_recibido='"+aa+"' ,mes='"+bb+"'  ,nit='"+cc+"' ,  entidad='"+dd+"',   numero_factura='"+ee+"',  valor_factura='"+ff+"', tipo_objecion='"+gg+"', fecha_entrega='"+hh+"', usuario='"+jj+"', observaciones='"+kk+"'  where numero_factura=?";
	        	  conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	              PreparedStatement ps = conn.prepareStatement(Consulta);
	              ps.setInt(1, dato);
	              ps.executeUpdate();
	              Alert alert = new Alert(AlertType.INFORMATION);
	              alert.setTitle("Informacion");
	              alert.setHeaderText(null);
	              alert.setContentText("Registro Actualizado correctamente");
	              alert.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
	                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
	                 		"    -fx-border-radius: 5;\r\n" + 
	                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
	                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
	                 		"    -fx-text-fill:black;\r\n" + 
	                 		"    -fx-font-family: Oswald;\r\n" + 
	                 		"    -fx-font-size:15px;	");
	              alert.showAndWait();
	        	}
	    	 
	           catch (SQLException e) {
	              e.printStackTrace();
	       }
	    }   
	   }   
	  
	
	
	
	public void limpiarcasillas() {
		Nombreentidad.clear();
	    Nitt.clear();
	    fecentrega.setValue(null);
	    fecrecibido.setValue(null);
	    tipoobjecion.clear();
	    mes.clear();
	    numfac.clear();
	    valorfact.clear();
	    observacion.clear();
	    usuario.clear();
	    numfacbusqueda.clear();
	}
	
	public void limpiarcasillasentidad() {
		DatosEntidad.clear();
		NitEntidad.clear();
	}
	
	
	public static void main (String[] args) {
		launch(args);	
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Miid.setCellValueFactory(new PropertyValueFactory <Entidad,Integer>("idEntidad"));
		Mientidad.setCellValueFactory(new PropertyValueFactory <Entidad,String>("Entidad"));
		Minit.setCellValueFactory(new PropertyValueFactory <Entidad,String>("Nit"));
		
		
	}

}
