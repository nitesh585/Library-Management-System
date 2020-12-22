package ViewBooks;

import DbHandler.DatabaseHandler;
import UpdateData.UpdateDataController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

public class ViewBooksController implements Initializable {

    ObservableList<Books> list = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Books> tableview;
    @FXML
    private TableColumn<Books,String> IdCol;
    @FXML
    private TableColumn<Books,String> NameCol;
    @FXML
    private TableColumn<Books,String> AuthorCol;
    @FXML
    private TableColumn<Books,String> PublisherCol;
    @FXML
    private MenuItem updat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          initcol();
          load_data();
    }    

    private void initcol() {
         IdCol.setCellValueFactory(new PropertyValueFactory("Id"));
         NameCol.setCellValueFactory(new PropertyValueFactory("Name"));
         AuthorCol.setCellValueFactory(new PropertyValueFactory("AuthName"));
         PublisherCol.setCellValueFactory(new PropertyValueFactory("PubName"));
    }

    private void load_data() {
        list.clear();
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String q = "SELECT * FROM BOOKS";
        ResultSet res = handler.execQuery(q);
        try {
            while(res.next()){
                String i = res.getString("BookId");
                String n = res.getString("BookName");
                String a = res.getString("AuthorName");
                String p = res.getString("PublisherName");
                list.add(new Books(i,n,a,p));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(list);
    }

    @FXML
    private void handleDeleteOption(ActionEvent event) {
        Books selectedbook = tableview.getSelectionModel().getSelectedItem();
        if(selectedbook==null){
           return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Deleting Book");
        a.setContentText("Are you want to delete "+selectedbook.getName()+" ?");
        Optional<ButtonType> ans = a.showAndWait();
        if(ans.get()==ButtonType.OK){
            deletebook(selectedbook.getId());
            list.remove(selectedbook);
        }else{
            Notifications.create().text("Deletion process is Canceled !").showConfirm();
        }
    }

    private void deletebook(String Id) {
        String BId = Id;
        DatabaseHandler handler = DatabaseHandler.getInstance(); 
        String query = "DELETE FROM BOOKS WHERE BookId = '"+BId+"'";
        if(handler.deleteRow(query)){
            Notifications.create().text(BId+" is Deleted").graphic(new ImageView("/Images/yes.png")).position(Pos.TOP_RIGHT).show();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("");
            a.setContentText("NOT Deleted");
            a.show();
        }
    }

    @FXML
    private void handleUpdateOption(ActionEvent event) {
        try {
           Books selectedbook = tableview.getSelectionModel().getSelectedItem();
           if(selectedbook==null){
               return;
            }
          System.out.println(selectedbook);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateData/updateData.fxml")); 
           Parent parent = loader.load();
           
           //using UpdateDataController to set the value of its textFields
           UpdateDataController control = (UpdateDataController) loader.getController();
           control.inflateUI(selectedbook);
           
           Stage stage = new Stage();
           Scene scene = new Scene(parent);
           stage.setScene(scene);
           stage.show();
           stage.setOnCloseRequest(new EventHandler(){
               @Override
               public void handle(Event event) {
                 handleRefreshOption(new ActionEvent());
               } 
           });
        } catch (IOException ex) {
            Logger.getLogger(ViewBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleRefreshOption(ActionEvent event) {
        load_data();
    }
    
    public static class Books{
     private final SimpleStringProperty Id;
     private final SimpleStringProperty Name;
     private final SimpleStringProperty AuthName;
     private final SimpleStringProperty PubName;
     
     Books(String i , String n , String a,String p){
         this.Id = new SimpleStringProperty(i);
         this.Name = new SimpleStringProperty(n);
         this.AuthName = new SimpleStringProperty(a);
         this.PubName = new SimpleStringProperty(p);
         
     }     
        public String getId() {
            return Id.get();
        }
        public String getName() {
            return Name.get();
        }
        public String getAuthName() {
            return AuthName.get();
        }
        public String getPubName() {
            return PubName.get();
        }
    }
    
}
