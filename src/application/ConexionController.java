package application;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;





public class ConexionController  implements Initializable {
	
	
	
	    @FXML TableView<Glosa>  tablaglosa;
	    @FXML TableColumn<Glosa, Integer>  idg;
	    @FXML TableColumn<Glosa, LocalDate>  fecharec;
	    @FXML TableColumn<Glosa, String> mmes;
	    @FXML TableColumn<Glosa, String > minit;
	    @FXML TableColumn<Glosa, String > mientidad;
	    @FXML TableColumn<Glosa, Integer>  numfac;
	    @FXML TableColumn<Glosa, Integer>  valglosa;
	    @FXML TableColumn <Glosa, String> tipoobj;
	    @FXML TableColumn<Glosa, LocalDate>  fechaent;
	    @FXML TableColumn<Glosa, String> usu;
	    @FXML TableColumn<Glosa, String > obser;
	    
	 
	  
	    
	    
	    

	    @FXML private Button exporta;
	    @FXML private Button bqdfac;
	    @FXML private Button mts;
	    @FXML private Button lmp;
	    @FXML private Button mtn;
	    @FXML private Button btn;
	    @FXML private Button prueba;
        @FXML private MenuBar menucombo;
        @FXML private MenuBar menudos;
        @FXML private Button botonborrar;
		@FXML private TextField borrar;
		@FXML private DatePicker mifecha;
		@FXML private DatePicker mifent;
	    @FXML private ComboBox <String> mes;
	    @FXML private TextField nitb;
	    @FXML private ComboBox <String> entidad;
	    @FXML private TextField numerofactura;
	    @FXML private TextField buscarfactura;
	    @FXML private TextField valorglosa;
	    @FXML private ComboBox <String> tipoobjecion;
	    @FXML private TextField usuario;
	    @FXML private TextField observacion;
	    ResultSet rs=null;
	    Connection Conexion=null;

	    
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			idg.setCellValueFactory(new PropertyValueFactory <Glosa,Integer>("idglosa"));
	        mmes.setCellValueFactory(new PropertyValueFactory <Glosa,String>("mes"));
			minit.setCellValueFactory(new PropertyValueFactory <Glosa,String>("nit"));
			mientidad.setCellValueFactory(new PropertyValueFactory <Glosa,String>("entidad"));
			numfac.setCellValueFactory(new PropertyValueFactory <Glosa,Integer>("numerofactura"));
			valglosa.setCellValueFactory(new PropertyValueFactory <Glosa,Integer>("valorglosa"));
			tipoobj.setCellValueFactory(new PropertyValueFactory <Glosa,String>("tipoobjecion"));
			usu.setCellValueFactory(new PropertyValueFactory <Glosa,String>("usuario"));
			obser.setCellValueFactory(new PropertyValueFactory <Glosa,String>("observacion"));  
	
			
			
			
			fecharec.setCellValueFactory(cellData -> cellData.getValue().fechaRecibidoProperty());
			fechaent.setCellValueFactory(cellData -> cellData.getValue().fechaEntregaAuditoriaProperty());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fecharec.setCellFactory(column -> {
		         return new TableCell<Glosa, LocalDate>() {
		             @Override
		             protected void updateItem(LocalDate item, boolean empty) {
		                 super.updateItem(item, empty);

		                 if (item == null || empty) {
		                     setText(null);
		                 } else {
		                     setText(formatter.format(item));

		                 }
		             }
		         };
		     });
		     fechaent.setCellFactory(column -> {
		         return new TableCell<Glosa, LocalDate>() {
		             @Override
		             protected void updateItem(LocalDate item, boolean empty) {
		                 super.updateItem(item, empty);

		                 if (item == null || empty) {
		                     setText(null);
		                 } else {
		                     setText(formatter.format(item));

		                 }
		             }
		         };
		     });
		     
			 seleccionaregistros();
			 llenadocombobox();
			 llenadocombobox4();
		}
		


	
	    
		public void cargavistauno() throws IOException{
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("CargaEntidad.fxml"));
		        Scene scene = new Scene(fxmlLoader.load());
		        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Oswald");
		        Stage stage = new Stage();
		        stage.setTitle("Datos Entidad");
		        stage.setScene(scene);
		        stage.show();
		 }

		
		public void vistados() throws IOException{
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("Actualizacion.fxml"));
		        Scene scene = new Scene(fxmlLoader.load());
		        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Oswald");
		        Stage stage = new Stage();
		        stage.setTitle("Actualizacion De Datos");
		        stage.setScene(scene);
		        stage.show();
		}

		
		
		
		
		
	
		
		public void borraregistro() {
			 Integer numfac=null;
			 String consulta=" delete  from auditoriac where numero_factura=?";
			 Connection conn=null;{
				 
		          try {
		        	  try {
							 numfac = Integer.parseInt(borrar.getText());
						 }catch (NumberFormatException ex) {
							 Alert alert = new Alert(AlertType.INFORMATION);
				              alert.setTitle("Informacion");
				              alert.setHeaderText(null);
				              alert.setContentText("Campo Vacio, Por favor Digite el numero de Factura:" +ex);
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
		         	     PreparedStatement ps =conn.prepareStatement(consulta);
		         	     ps.setInt(1, numfac);
		         	     int comparar =ps.executeUpdate(); 
		         	     if (comparar==0){
		                 Alert alerta = new Alert(AlertType.INFORMATION);
				         alerta.setTitle("Informacion");
				         alerta.setHeaderText(null);
				         alerta.setContentText("Registro no encontrado");
				         alerta.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
				                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
				                 		"    -fx-border-radius: 5;\r\n" + 
				                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
				                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
				                 		"    -fx-text-fill:black;\r\n" + 
				                 		"    -fx-font-family: Oswald;\r\n" + 
				                 		"    -fx-font-size:15px;	");
				              alerta.showAndWait();    
			             }
		         	 else {  
			               Alert alerta = new Alert(AlertType.INFORMATION);
				           alerta.setTitle("Informacion");
				           alerta.setHeaderText(null);
				           alerta.setContentText("Registro borrado correctamente");
				           alerta.getDialogPane().setStyle("-fx-text-fill: white;\r\n" + 
				                 		"    -fx-border-color:  rgb(238, 201, 91);\r\n" + 
				                 		"    -fx-border-radius: 5;\r\n" + 
				                 		"    -fx-padding: 10 2 10 -2;\r\n" + 
				                 		"    -fx-background-color:linear-gradient(to bottom, #ffffcc 15%, #ffcc99 91%);\r\n" + 
				                 		"    -fx-text-fill:black;\r\n" + 
				                 		"    -fx-font-family: Oswald;\r\n" + 
				                 		"    -fx-font-size:15px;	");
				            alerta.showAndWait();  
				          }     
		          }catch (SQLException e) {
		        }
		      }
			seleccionaregistros();
		  } 
		      

	public void conexion(){
		try {
			 Conexion=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	        } 
		catch (SQLException e) {
		     e.printStackTrace();
		 }
		 if(Conexion!=null) {
			 Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Informacion");
             alert.setHeaderText(null);
             alert.setContentText("Conexion Exitosa");
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
			 Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Informacion");
             alert.setHeaderText(null);
             alert.setContentText("No se Pudo Conectar Con la Base de Datos");
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
	  }
	
	
	public void insertaregistro() throws ParseException {
		  String combo1 = null;
		  String dato2 = nitb.getText();
		  String combo3 = null;
		  String combo4 = null;
		  Integer c=null;
		  Integer h=null;
		  String fecha1=mifecha.getValue().toString();
		  String fecha2 = mifent.getValue().toString();
		  try {
				c= Integer.parseInt(valorglosa.getText());
				h= Integer.parseInt(numerofactura.getText());
			 }catch (NumberFormatException ex) {
				 Alert alert = new Alert(AlertType.INFORMATION);
	              alert.setTitle("Informacion");
	              alert.setHeaderText(null);
	              alert.setContentText("Campo Vacios, Por favor Digite la Información:" +ex);
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
		  try {
		         combo1= mes.getSelectionModel().getSelectedItem().toString();
   		         combo3= entidad.getSelectionModel().getSelectedItem().toString();
   		         combo4= tipoobjecion.getSelectionModel().getSelectedItem().toString();
   	      }catch(NullPointerException ee) {
   		         ee.printStackTrace();
   		         Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Informacion");
                 alert.setHeaderText(null);
                 alert.setContentText("Datos del Combobox Vacios" +ee);
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
		      
		       if(fecha1.compareTo(fecha2)> 0) {
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
		       else if (mifecha.getEditor().getText().isEmpty()||mifent.getEditor().getText().isEmpty()||nitb.getText().isEmpty()||numerofactura.getText().isEmpty()||valorglosa.getText().isEmpty()||usuario.getText().isEmpty()||observacion.getText().isEmpty()) {
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
	        	 conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	             Statement insertar=conn.createStatement();
	             insertar.executeUpdate("insert into auditoriac (fecha_recibido, mes, nit, entidad, numero_factura, valor_factura, tipo_objecion, fecha_entrega, usuario, observaciones) values ('"+((TextField)mifecha.getEditor()).getText()+"','"+combo1+"','"+dato2+"','"+combo3+"','"+h+"', '"+c+"','"+combo4+"','"+((TextField)mifent.getEditor()).getText()+"','"+usuario.getText()+"', '"+observacion.getText()+"')");          
	                 if(conn!=null) {
	            	 Alert alert = new Alert(AlertType.INFORMATION);
	                 alert.setTitle("Informacion");
	                 alert.setHeaderText(null);
	                 alert.setContentText("Registro Insertado correctamente");
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
		 		}
	          catch (SQLException e) {
	             e.printStackTrace();
	         }
	         seleccionaregistros(); 
	 	   }
	      }
	
	public void seleccionaregistros() {
		ObservableList <Glosa> data =FXCollections.observableArrayList();
	      Connection conn=null;{
	          try {
	        	  conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	              Statement mostrar=conn.createStatement();
	              ResultSet rs;
	              rs= mostrar.executeQuery("select * from auditoriac");
	              while ( rs.next() ) 
	              {
	                 data.add(new Glosa(
	                		 rs.getInt(1),
	                		 rs.getDate(2).toLocalDate(),
	                		 rs.getString("mes"), 
	                		 rs.getString("nit"),
	                		 rs.getString("entidad"),
	                		 rs.getInt(6),
	                		 rs.getInt(7),
	                		 rs.getString("tipo_objecion"),
	                		 rs.getDate(9).toLocalDate(),
	                		 rs.getString("usuario"),
	                		 rs.getString("observaciones")
	                		 )
	                         );
	                 tablaglosa.setItems(data);
	              }
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }   
	       } 
	    }
	
	public void busquedanfactura() {
		 String nfac = buscarfactura.getText();
		 ObservableList <Glosa> busqueda =FXCollections.observableArrayList();
		 String consulta=" select * from auditoriac where numero_factura like ? " ;
		 Connection conn=null;{
	          try {
	 	         if (buscarfactura.getText().isEmpty())  {
	 	            	 Alert alert = new Alert(AlertType.INFORMATION);
	 	                 alert.setTitle("Informacion");
	 	                 alert.setHeaderText(null);
	 	                 alert.setContentText("El campo no puede estar vacio, debe digitar el numero de factura");
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
	         	  PreparedStatement ps =conn.prepareStatement(consulta);
	         	  ps.setString(1, nfac);
	         	  ResultSet rs =ps.executeQuery();
	         	  if(rs.next()) {
	                do {
	                 busqueda.add(new Glosa(
	                		 rs.getInt(1),
	                		 rs.getDate(2).toLocalDate(),
	                		 rs.getString("mes"), 
	                		 rs.getString("nit"),
	                		 rs.getString("entidad"),
	                		 rs.getInt(6),
	                		 rs.getInt(7),
	                		 rs.getString("tipo_objecion"),
	                		 rs.getDate(9).toLocalDate(),
	                		 rs.getString("usuario"),
	                		 rs.getString("observaciones")
	                         )
	                         );
	                }while ( rs.next() ) ;
	         		 Alert alert = new Alert(AlertType.INFORMATION);
 	                 alert.setTitle("Informacion");
 	                 alert.setHeaderText(null);
 	                 alert.setContentText("Factura Encontrada!");
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
	         		 Alert alert = new Alert(AlertType.INFORMATION);
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
	 	        }
	          } catch (SQLException e) {
	              e.printStackTrace();
	        }
	       tablaglosa.setItems(busqueda); 
		 }  
	  }
	
	
	
	public void validanit() {
	 String miDatonit3 = nitb.getText();
	 String consulta="select nit from entidad where nit='"+miDatonit3+"' " ;
	 Connection conn=null;{
		 try {
		  conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
    	  PreparedStatement ps =conn.prepareStatement(consulta);
    	  ResultSet rs =ps.executeQuery(); 
    	  while(rs.next()) {
    	  rs.getString("nit");
    	  if(rs.getString("nit").equals(miDatonit3)) {
    			 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Informacion");
                 alert.setHeaderText(null);
                 alert.setContentText("Dato del Nit Encontrado!, Valide el nit");
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
    		else if(miDatonit3!=rs.getString("nit")) {
   			 Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Informacion");
             alert.setHeaderText(null);
             alert.setContentText("Dato del Nit No Encontrado!");
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
         }
	   }catch (SQLException e){
	 }
    }
	}
	
	
	public void llenadocombobox() {
		Connection conn=null;
        try {
        	ObservableList<String> listacombo= FXCollections.observableArrayList();
        	String consulta = "select mes from meses";
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
            PreparedStatement ps =conn.prepareStatement(consulta);
       	     ResultSet rs = ps.executeQuery();
       	     while ( rs.next() ) 
             {  
               listacombo.add(rs.getString("mes"));
             }
       	  mes.setItems(listacombo);
        } catch (SQLException e) {
            e.printStackTrace();
      }
   }
	
	
	
	public void llenadocombobox3() {
		String miDatonit2= nitb.getText();
		Connection conn=null;
        try {
        	if(nitb.getText().isEmpty()) {
   			 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Informacion");
                 alert.setHeaderText(null);
                 alert.setContentText("No se Valido el Nit por que el Campo esta Vacio");
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
   			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText(null);
            alert.setContentText("Nit Validado Exitosamente");
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
        	ObservableList<String> listacombonombre= FXCollections.observableArrayList();
        	String consulta = "select nombre from entidad where nit='"+miDatonit2+"' ";
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
            PreparedStatement ps =conn.prepareStatement(consulta);
            /*ps.setString(1, miDatonit2);*/
       	     ResultSet rs = ps.executeQuery();
       	     while ( rs.next() ) 
             {  
       	    listacombonombre.add(rs.getString("nombre"));
       	     }  
       	  entidad.setItems(listacombonombre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize(null, null);
	  }

	
	public void llenadocombobox4() {
		Connection conn=null;
        try {
        	ObservableList<String> listacombotipoob= FXCollections.observableArrayList();
        	String consulta = "select tipo_objecion from tipob";
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
            PreparedStatement ps =conn.prepareStatement(consulta);
       	     ResultSet rs = ps.executeQuery();
       	     while ( rs.next() ) 
             {  
       	    	listacombotipoob.add(rs.getString("tipo_objecion"));
             }
       	  tipoobjecion.setItems(listacombotipoob);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	
	public void exportaexcel() throws IOException {
		  Connection conn=null;{
	          try {
	        	  conn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-4JA6SFR:1433;databaseName=GLOSASNINO", "sa", "123");
	              Statement mostrar=conn.createStatement();
	              ResultSet rs;
	              rs= mostrar.executeQuery("select * from auditoriac");
                  @SuppressWarnings("resource")
				  XSSFWorkbook milibroexcel = new XSSFWorkbook();
	              XSSFSheet  mihoja = milibroexcel.createSheet("Detalle de Glosas");
	              XSSFRow cabecera =  mihoja.createRow(0);
	              cabecera.createCell(0).setCellValue("FECHA RECIBIDO");
	              
	              cabecera.createCell(1).setCellValue("MES");
	              cabecera.createCell(2).setCellValue("NIT");
	              cabecera.createCell(3).setCellValue("ENTIDAD");
	              cabecera.createCell(4).setCellValue("N° DE FACTURA");
	              cabecera.createCell(5).setCellValue("VALOR GLOSA");
	              cabecera.createCell(6).setCellValue("TIPO OBJECIÓN");
	              cabecera.createCell(7).setCellValue("FECHA DE ENTREGA AUDITORÍA");
	              cabecera.createCell(8).setCellValue("FIRMA DE QUIEN RECIBE");
	              cabecera.createCell(9).setCellValue("OBSERVACIÓN");
	              
	              mihoja.setColumnWidth(0, 4000);
	              mihoja.setColumnWidth(1, 4000);
	              mihoja.setColumnWidth(2, 4000);
	              mihoja.setColumnWidth(3, 4000);
	              mihoja.setColumnWidth(4, 4000);
	              mihoja.setColumnWidth(5, 4000);
	              mihoja.setColumnWidth(6, 4000);
	              mihoja.autoSizeColumn(7);
	              mihoja.autoSizeColumn(8);
	              mihoja.setColumnWidth(9, 5500);
	           
	         
	              
	              XSSFCellStyle style = milibroexcel.createCellStyle();
	              style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.index);
	              style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	              style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
	              
	              Font celdafondo= milibroexcel.createFont();
	              celdafondo.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
	              style.setFont(celdafondo);

	              cabecera.getCell(0).setCellStyle(style);
	              cabecera.getCell(1).setCellStyle(style);
	              cabecera.getCell(2).setCellStyle(style);
	              cabecera.getCell(3).setCellStyle(style);
	              cabecera.getCell(4).setCellStyle(style);
	              cabecera.getCell(5).setCellStyle(style);
	              cabecera.getCell(6).setCellStyle(style);
	              cabecera.getCell(7).setCellStyle(style);
	              cabecera.getCell(8).setCellStyle(style);
	              cabecera.getCell(9).setCellStyle(style);
	        
	             
	              
                int siguientefilaenellibro=1;
	              while ( rs.next() ) 
	              {
	            	  XSSFRow cabecerasiguientefila =  mihoja.createRow(siguientefilaenellibro);
	            	  cabecerasiguientefila.createCell(0).setCellValue(rs.getDate("FECHA_RECIBIDO").toString());
	            	  cabecerasiguientefila.createCell(1).setCellValue(rs.getString("MES"));
	            	  cabecerasiguientefila.createCell(2).setCellValue(rs.getString("NIT"));
	            	  cabecerasiguientefila.createCell(3).setCellValue(rs.getString("ENTIDAD"));
	            	  cabecerasiguientefila.createCell(4).setCellValue(rs.getInt(6));
	            	  cabecerasiguientefila.createCell(5).setCellValue(rs.getInt(7));
	            	  cabecerasiguientefila.createCell(6).setCellValue(rs.getString("TIPO_OBJECION"));
	            	  cabecerasiguientefila.createCell(7).setCellValue(rs.getDate("FECHA_ENTREGA").toString());
	            	  cabecerasiguientefila.createCell(8).setCellValue(rs.getString("USUARIO"));
	            	  cabecerasiguientefila.createCell(9).setCellValue(rs.getString("OBSERVACIONES"));
	            	  siguientefilaenellibro++;
	            	
	              }
	                FileChooser guardarexcel = new FileChooser();
	                FileChooser.ExtensionFilter excelfiltro = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
	                guardarexcel.getExtensionFilters().add(excelfiltro);
	                File miarchivoexcel = guardarexcel.showSaveDialog(null);
	                if (miarchivoexcel != null) {
	                    try (FileOutputStream outputStream = new FileOutputStream(miarchivoexcel.getAbsolutePath())) {
	                    	 milibroexcel.write(outputStream);
	                    }
	                    catch (IOException ex) {
	                        
	                    }
	                }
                
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
	           
	     }
		  
		  
	}

	public void limpiatexto() {
		numerofactura.clear();
		valorglosa.clear();
		usuario.clear();
		observacion.clear();
		borrar.clear();
		mifecha.setValue(null);
	    mifent.setValue(null);
	    mes.setValue(null);
	    nitb.clear();
	    entidad.setValue(null);
	    tipoobjecion.setValue(null);
	}
	
	

	public void cargarconexion() {
	 btn.setOnAction(e->{
	        conexion();
	    });
	}
	
	public void cargarregistro() {
		 mtn.setOnAction(e->{
		        try {
					insertaregistro();
				} catch (ParseException e1) {
					e1.printStackTrace();
	   		         Alert alert = new Alert(AlertType.INFORMATION);
	                 alert.setTitle("Informacion");
	                 alert.setHeaderText(null);
	                 alert.setContentText("Fechas Vacias!" +e1);
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
		    });
		}
	public void borrarcasillatexto() {
		 lmp.setOnAction(e->{
		        limpiatexto();
		    });
		}
	
	public void mostrartodo() {
		mts.setOnAction(e->{
	        seleccionaregistros();
	    });
	}
  
	public void buscanfactura() {
		 bqdfac.setOnAction(e->{
			 busquedanfactura();
		       
		    });
	}
		 

	public void borraregistrofactura() {
		botonborrar.setOnAction(e->{
			borraregistro();
				       
	  });
	}

}

				