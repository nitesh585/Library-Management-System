package DeleteData;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class DeleteDataController implements Initializable {

    DatabaseHandler handler;
    @FXML
    private JFXButton backBtn;
    
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXTextField bookId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
             handler = DatabaseHandler.getInstance();
    }    

    @FXML
    private void goBack(ActionEvent event) {
        try {
            Stage stage = (Stage) deleteBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Aministrator/administrator.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DeleteDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void deleteData(ActionEvent event) throws SQLException {
        String BId = bookId.getText();
        String query = "DELETE FROM BOOKS WHERE BookId = '"+BId+"'";
        handler.deleteRow(query);
        
        bookId.setText("");
    }
}
