/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacejava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class InterfaceJava extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        installDependencies();
        launch(args);        
    }
    
    private static void installDependencies(){
        
        System.out.println("Installing numpy and scipy...");
        Runtime rt = Runtime.getRuntime();
        try {
            Process p = rt.exec("cmd.exe /c python -m pip install numpy scipy");
            
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line;
            
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao instalar dependências.");
        }
    }
    
}
