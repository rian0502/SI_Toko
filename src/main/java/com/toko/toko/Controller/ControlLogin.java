package com.toko.toko.Controller;

import com.toko.toko.Main;
import com.toko.toko.Model.Akun;
import com.toko.toko.Model.DatabaseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.swing.*;

public class ControlLogin implements Initializable {

    @FXML
    public TextField tfUsername;
    @FXML
    public PasswordField tfPassword;
    DatabaseModel dbm;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbm = new DatabaseModel();
    }

    public void handleLoginAction(ActionEvent event) {
        try {
            if(dbm.loginAkun(new Akun(tfUsername.getText().trim(), tfPassword.getText().trim()))){
                tfUsername.clear();
                tfPassword.clear();
                Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("HomePage.fxml")));
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                JOptionPane.showMessageDialog(null, "Username Atau Password Salah","Login",JOptionPane.ERROR_MESSAGE);
                tfUsername.clear();
                tfPassword.clear();
            }
        }catch (Exception e){
            System.out.println("EROR login");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }




    }
}