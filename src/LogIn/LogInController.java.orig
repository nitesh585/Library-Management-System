package LogIn;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import library.MainController;
import org.controlsfx.control.Notifications;

public class LogInController implements Initializable {

    @FXML
    private JFXTextField admin;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private JFXButton login;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String a = admin.getText();
        String p = pass.getText();
        if(a.equals("Admin Master") && p.equals("rcduwelcome")){
            try {
            //loading administrator scene
            Stage stage=(Stage) login.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Aministrator/administrator.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
           } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            Notifications.create().text("Invalid Admin !!").position(Pos.TOP_RIGHT).showError();
        }
        admin.setText("");
        pass.setText("");
    }
    
}
