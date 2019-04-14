package interfacejava;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

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

//        data.add(new Data("Valor1", "Valor2", "Valor3"));
//        tableView.setItems(data);
        ArrayList<String> array1 = new ArrayList<>();
        ArrayList<String> array2 = new ArrayList<>();
        
        String[] split1 = txtFieldArray1.getText().split(",");
        String[] split2 = txtFieldArray1.getText().split(",");
        
        System.out.println(split1.length + " " + split2.length);
        
        if(txtFieldArray1.getText().isEmpty() || txtFieldArray2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Insira valores nos campos.");
            return;
        }        
        
        if((split1.length == split2.length) && (split1.length <= 5)){
            for (int i = 0; i < split1.length; i++){
                try{
                    Float.parseFloat(split1[i]);
                    Float.parseFloat(split2[i]);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Digite apenas números.");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Quantidade de valores incorreta.");
        }
        
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
        tableView.setPlaceholder(new Label("Insira os valores dos dois arrays, com cada valor separado por vírgula."));
        
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
    ObservableList<Data> data = FXCollections.observableArrayList();
}
