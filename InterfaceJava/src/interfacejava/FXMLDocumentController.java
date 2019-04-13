/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacejava;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author Joao
 */
public class FXMLDocumentController implements Initializable {
    
    private final int MAX_COLUMN_SIZE = 10;
    private int numberOfColumns = 1;
    
    @FXML
    private TextField txtFieldArray1;

    @FXML
    private TextField txtFieldArray2;
    
    @FXML
    private TableView tableView;
    
    @FXML
    private Button btnClear;    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {

        data.add(new Person("Valor1", "Valor2", "Valor3"));
       
        tableView.setItems(data);
        
        System.out.println("inserindo");
    }
    
    @FXML
    private void btnClearAction(ActionEvent event){

        tableView.getItems().clear();
        txtFieldArray1.setText("");
        txtFieldArray2.setText("");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Apagando colunas que já vem no tableview
        tableView.getColumns().clear();
        
        createColumns("N");
        createColumns("Xi");
        createColumns("Wi");
        
        
    }
    
    private void createColumns(String columnName){
        
        /*
            String s deve ter o mesmo nome dos métodos 'get' da classe que será
            usada para inserir os dados no objeto 'ObservableList data'
        */
        
        TableColumn t = new TableColumn(columnName);
        String s = "prop" + (numberOfColumns++);
        t.setCellValueFactory( new PropertyValueFactory<>(s));
        t.setSortable(false);
        t.setMinWidth(300);
        t.setStyle( "-fx-alignment: CENTER;");

        tableView.getColumns().add(t);
            
    }


    ObservableList<Person> data = FXCollections.observableArrayList();
    
}
