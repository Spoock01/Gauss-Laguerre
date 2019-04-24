/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacejava;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author Joao
 */
public class FXMLDocumentController implements Initializable {
    
    private final int MAX_COLUMN_SIZE = 10;
    private int equacao = 0;
    private int numberOfColumns = 1;
    
    @FXML
    private TextField txtFieldArray1;

    @FXML
    private TextField txtFieldArray2;
    
    @FXML
    private TableView tableView;
    
    @FXML
    private TextField txtReal;

    @FXML
    private TextField txtLaguerre;
    
    @FXML
    private ComboBox<String> comboBox;
    
    @FXML
    private Label lblDescricao1;
    
    @FXML
    private Label lblDescricao2;
    
    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageView1;
    
    
    @FXML
    void changeComboBoxValue(ActionEvent event) {
        String selectedText = comboBox.getSelectionModel().getSelectedItem();
        
        if(selectedText.equals("IntegralOf e^-x * cos(x)")){
            equacao = 1;
        }else if(selectedText.equals("e^-1 IntegralOf e^-x * (x + 1)^2")){
            equacao = 2;
        }
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        int n = -1, forRange = 1;
        
        if (txtFieldArray1.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Digite um valor para N.");
            return;
        }
        
        if(comboBox.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Selecione uma equação.");
            return;
        }
        
        try{
            n = Integer.parseInt(txtFieldArray1.getText().trim());
            
            if(n <= 0 || n > 100){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, "Deve ser digitado um valor numérico entre 1 e 100.");
            return;
        }
        
        if(!data.isEmpty()){
            btnClearAction(event);
        }
        
        try {
            Process p = Runtime.getRuntime().exec("python src/PythonCode/GaussLaguerre.py " + n + " " + equacao);
            
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
        txtLaguerre.setText("");
        txtReal.setText("");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Apagando colunas que já vem no tableview
        String descricao1 = 
                "A quadratura de Gauss tem como motivação calcular integrais de forma numérica\n"
               +"Para utilizar a fórmula de Gauss-Laguerre, a integral a ser calculada deve ter a função de peso \n\n"
               +"                    W(x)= e^-x, a = 0 e b = infinito, onde 'a' e 'b' são os limites de integração.\n";

        
        String descricao2 = "Com os requisitos acima, a integral é calculada "
                           +"utilizando a seguinte aproximação e função de peso:";
        
        lblDescricao1.setText(descricao1);
        lblDescricao2.setText(descricao2);
        
        
        File file = new File("src/Imagens/Equacao.png");
        File file2 = new File("src/Imagens/Peso.png");
        Image image = new Image(file.toURI().toString());
        Image image2 = new Image(file2.toURI().toString());
        
        imageView.setImage(image);
        imageView1.setImage(image2);
        
        tableView.getColumns().clear();
        
        createColumns("N");
        createColumns("Xi");
        createColumns("Wi");
        
        
        comboBox.setItems(FXCollections.observableArrayList("IntegralOf e^-x * cos(x)",
            "e^-1 IntegralOf e^-x * (x + 1)^2"));
        
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
