/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacejava;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Joao
 */
public class Data {
    private final SimpleStringProperty prop1;
    private final SimpleStringProperty prop2;
    private final SimpleStringProperty prop3;

    public Data(String fName, String lName, String email) {
        this.prop1 = new SimpleStringProperty(fName);
        this.prop2 = new SimpleStringProperty(lName);
        this.prop3 = new SimpleStringProperty(email);
    }

    public String getProp1() {
        return prop1.get();
    }
    public void setProp1(String fName) {
        prop1.set(fName);
    }

    public String getProp2() {
        return prop2.get();
    }
    public void setProp2(String fName) {
        prop2.set(fName);
    }

    public String getProp3() {
        return prop3.get();
    }
    public void setProp3(String fName) {
        prop3.set(fName);
    }
}
