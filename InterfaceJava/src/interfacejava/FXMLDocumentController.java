/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;

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
    private TextField txtEquacao;

    @FXML
    private TextField txtReal;

    @FXML
    private TextField txtLaguerre;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        int n = -1, forRange = 1;
        
        if (txtFieldArray1.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Digite um valor para N.");
            return;
        }
        
        try{
            n = Integer.parseInt(txtFieldArray1.getText().trim());
            
            if(n <= 0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Deve ser digitado um valor numérico maior que ZERO.");
            return;
        }
        
        if(!data.isEmpty()){
            btnClearAction(event);
            System.out.println("Cleaning");
        }
        
        try {
            System.out.println("Running python code");
            Process p = Runtime.getRuntime().exec("python src/PythonCode/GaussLaguerre.py " + n);
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line;
            
            for(int i = 2; i <= n; i++){
                forRange += i;
            }
            
            for(int i = 0; i < forRange; i++){
                line = br.readLine();
                String arr[] = line.split(",");
                
                /**
                 * Adicionando valor ao objeto que controla a tabela
                 */
                data.add(new Person(arr[0], arr[1], arr[2]));
            }
            String real = br.readLine();
            String laguerre = br.readLine();
            
            txtReal.setText(real.split(":")[1]);
            txtLaguerre.setText(laguerre.split(":")[1]);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
       
        /**
         * Inserindo e deixando visiveis os valores da tabela
         */
        tableView.setItems(data);

    }
    
    @FXML
    private void btnClearAction(ActionEvent event){

        tableView.getItems().clear();
        txtFieldArray1.setText("");
        txtFieldArray2.setText("");
        txtEquacao.setText("");
        txtLaguerre.setText("");
        txtReal.setText("");

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
