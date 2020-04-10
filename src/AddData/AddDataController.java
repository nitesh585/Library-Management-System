package AddData;

import DbHandler.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

public class AddDataController implements Initializable {
    
    DatabaseHandler handler;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXTextField bookId;
    @FXML
    private JFXTextField bookName;
    @FXML
    private JFXTextField authorName;
    @FXML
    private JFXTextField publisher;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       handler = DatabaseHandler.getInstance();
    }    

    //Go back to the previous window
    @FXML
    private void goBack(ActionEvent event) {
        try {
            Stage stage =(Stage) backBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Aministrator/administrator.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //adding data
    @FXML
    private void addData(ActionEvent event) throws SQLException {
         String BId = bookId.getText();
         String Bname = bookName.getText();
         String Aname = authorName.getText();
         String pub = publisher.getText();
         
         String query = "INSERT INTO BOOKS VALUES ('"   //Query for inserting records in database
                 + BId+"','"
                 + Bname+"','"
                 + Aname+"','"
                 + pub+"')";
         
         //If a any Field is empty
        if(BId.equals("") || Bname.equals("") || Aname.equals("") || pub.equals("")){
            Notifications.create().title("ERROR").text("A Field is Empty").position(Pos.TOP_RIGHT).showError();
            return;
        }
        
        if(handler.execAction(query)){                //execAction perform above's query 
            // If Query is succefully performed
            Notifications.create().title("Successful").text("Data Entered").position(Pos.TOP_RIGHT).graphic(new ImageView("/Images/yes.png")).show();
        }else{
            // If Query is not Sucessfully performed
            Notifications.create().title("ERROR").text(BId+" Already Exists").position(Pos.TOP_RIGHT).showError();
        }
        
        bookId.setText("");
        bookName.setText("");
        authorName.setText("");
        publisher.setText("");
    } 
}