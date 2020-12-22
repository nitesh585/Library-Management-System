package library;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private ImageView admin;
    @FXML
    private ImageView student;
    @FXML
    private JFXButton adminBtn;
    @FXML
    private JFXButton studentBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       Image s = new Image("/Images/student.png");  //setting student image
       Image a = new Image("/Images/administrator.png");  //setting admininstrator image
       admin.setImage(a);
       student.setImage(s);
    }    

    @FXML
    private void loadAdministrator(ActionEvent event) {
        try {
            //loading administrator scene
            Stage stage=(Stage) adminBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Aministrator/administrator.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
           } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadStudent(ActionEvent event) {
       try {
            //loading student scene
            Stage stage=(Stage) studentBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/StudentView/studentView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
           } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
