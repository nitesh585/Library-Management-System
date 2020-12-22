package StudentView;

import DbHandler.DatabaseHandler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class StudentViewController implements Initializable {

    ObservableList<StudentBooks> list = FXCollections.observableArrayList();

    @FXML
    private ImageView searchicon;
    @FXML
    private JFXTextField search;
    @FXML
    private ComboBox<String> option;
    @FXML
    private JFXTreeTableView<StudentBooks> tableview;

    int turn =0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Images/search.png");
        searchicon.setImage(img);
        ObservableList<String> d = FXCollections.observableArrayList("Book Name", "Author Name");
        option.setItems(d);

        JFXTreeTableColumn<StudentBooks,String> IdCol = new JFXTreeTableColumn("Book Id");
        IdCol.setPrefWidth(325);
        IdCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StudentBooks,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StudentBooks, String> param) {
                return param.getValue().getValue().BookId;
            }

        });


        JFXTreeTableColumn<StudentBooks,String> BookNameCol = new JFXTreeTableColumn("Book Name");
        BookNameCol.setPrefWidth(325);
        BookNameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StudentBooks,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StudentBooks, String> param) {
                return param.getValue().getValue().Bookname;
            }

        });

        JFXTreeTableColumn<StudentBooks,String> AuthCol = new JFXTreeTableColumn("Author Name");
        AuthCol.setPrefWidth(325);
        AuthCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<StudentBooks,String>,ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<StudentBooks, String> param) {
                return param.getValue().getValue().Authname;
            }

        });

        JFXTreeTableColumn<StudentBooks,String> pubCol = new JFXTreeTableColumn("Publisher Name");
        pubCol.setPrefWidth(325);
        pubCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<StudentBooks, String> param) -> param.getValue().getValue().Pub);
        DatabaseHandler handler = DatabaseHandler.getInstance();
        String q = "SELECT * FROM BOOKS";
        ResultSet res = handler.execQuery(q);
        try {
            while(res.next()) {
                String i = res.getString("BookId");
                String n = res.getString("BookName");
                String a = res.getString("AuthorName");
                String p = res.getString("PublisherName");
                list.add(new StudentBooks(i,n,a,p));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<StudentBooks> item = new RecursiveTreeItem<>(list,RecursiveTreeObject::getChildren );
        tableview.getColumns().setAll(IdCol,BookNameCol,AuthCol,pubCol);
        tableview.setRoot(item);
        tableview.setShowRoot(false);

        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            tableview.setPredicate((TreeItem<StudentBooks> t) -> {
                Boolean f;
                if(turn==0) {
                    f = (t.getValue().Bookname.getValue().contains(newValue)  || t.getValue().Bookname.getValue().contains(newValue.toLowerCase())) ;
                } else {
                    f = (t.getValue().Authname.getValue().contains(newValue) || t.getValue().Authname.getValue().contains(newValue.toLowerCase())) ;
                }
                return f;
            });
        });

    }

    class StudentBooks extends RecursiveTreeObject<StudentBooks> {
        StringProperty BookId;
        StringProperty Bookname;
        StringProperty Authname;
        StringProperty Pub;

        StudentBooks(String i,String n,String a,String p) {
            this.BookId = new SimpleStringProperty(i);
            this.Bookname = new SimpleStringProperty(n);
            this.Authname =new SimpleStringProperty(a);
            this.Pub = new SimpleStringProperty(p);
        }
    }

    public void ComboxHandler() {
        String res = option.getValue().toString();
        if(res.equals("Book Name")) {
            turn =0;
        } else {
            turn =1;
        }
    }

}
