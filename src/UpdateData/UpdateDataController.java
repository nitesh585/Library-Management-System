
package UpdateData;

import DbHandler.DatabaseHandler;
import ViewBooks.ViewBooksController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class UpdateDataController implements Initializable {

    @FXML
    private JFXTextField bookId;
    @FXML
    private JFXTextField bookName;
    @FXML
    private JFXTextField authorName;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXButton updateBtn;

    ViewBooksController v;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void inflateUI(ViewBooksController.Books book){
        bookId.setText(book.getId());
        bookName.setText(book.getName());
        authorName.setText(book.getAuthName());
        publisher.setText(book.getPubName());
        bookId.setEditable(false);
    }
    
    @FXML
    private void update_Data(ActionEvent event){
      String i = bookId.getText();
      String n = bookName.getText();
      String a = authorName.getText();
      String p = publisher.getText();
      String q = "UPDATE BOOKS SET BookId = '" + i+"'"
              + ",BookName = '"+n+"'"
              + ",AuthorName = '"+a+"'"
              + ",PublisherName = '"+p+"'"
              + "WHERE BookId='"+i+"'";
      DatabaseHandler handler = DatabaseHandler.getInstance();
      handler.execAction(q);
      bookId.setText("");
      bookName.setText("");
      authorName.setText("");
      publisher.setText("");
    }
}
