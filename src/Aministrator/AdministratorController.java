package Aministrator;

import DbHandler.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import library.MainController;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class AdministratorController implements Initializable {

    @FXML
    private ImageView background;
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXButton deleteBtn;
    @FXML
    private JFXButton viewBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Images/adminBackground.jpg");
        background.setImage(img);
    }    

    @FXML
    private void DeleteData(ActionEvent event) {
         FileChooser filechoose = new FileChooser();
         File file = filechoose.showOpenDialog(null);
         String fileName = file.getAbsolutePath();
         Vector dataholder = read(fileName);
         deleteToDatabase(dataholder);
    }
    
    @FXML
    private void addData(ActionEvent event) throws FileNotFoundException, IOException {
         FileChooser filechoose = new FileChooser();
         File file = filechoose.showOpenDialog(null);
         String fileName = file.getAbsolutePath();
         Vector dataholder = read(fileName);
         saveToDatabase(dataholder);
    }

    
    
  public static Vector read(String fileName)    {
    Vector cellVectorHolder = new Vector();
       try{
        FileInputStream myInput = new FileInputStream(fileName);
            //POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
        HSSFWorkbook myWorkBook = new HSSFWorkbook(myInput);
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator rowIter = mySheet.rowIterator(); 
        
       while(rowIter.hasNext()){
              HSSFRow myRow = (HSSFRow) rowIter.next();
              Iterator cellIter = myRow.cellIterator();
              Vector cellStoreVector=new Vector();
              while(cellIter.hasNext()){
                      HSSFCell myCell = (HSSFCell) cellIter.next();
                      cellStoreVector.addElement(myCell);
              }
              cellVectorHolder.addElement(cellStoreVector);
      }
    }catch (Exception e){e.printStackTrace(); }
    return cellVectorHolder;
}
   
  private static void saveToDatabase(Vector dataHolder) {
              String bookId="";
              String bookname="";
              String authorname="";
              String pub = "";
              DatabaseHandler handler = DatabaseHandler.getInstance();
            for (int i=0;i<dataHolder.size(); i++){
               Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
                    for(int j=0; j < cellStoreVector.size();j++){
                            HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
                            String st = myCell.toString();
                            if(j==0){
                                bookId = st;
                            }else if(j==1){
                                bookname = st;
                            }else if(j==2){
                                authorname = st;
                            }else{
                                pub = st;
                            }
                    }
                    String query = "INSERT INTO BOOKS VALUES("
                            + "'"+bookId+"',"
                            + "'"+bookname+"',"
                            + "'"+authorname+"',"
                            + "'"+pub+"')";
                   
  if(handler.execAction(query)){                //execAction perform above's query 
            // If Query is succefully performed
            Notifications.create().title("Successful").text("Data Entered").position(Pos.TOP_RIGHT).graphic(new ImageView("/Images/yes.png")).show();
        }else{
            // If Query is not Sucessfully performed
            Notifications.create().title("ERROR").text(BId+" Already Exists").position(Pos.TOP_RIGHT).showError();
        }
            }
    }
    
    
    @FXML
    private void goBack(ActionEvent event) {
          try {
            Stage stage=(Stage) backBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/library/Main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
           } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ViewData(ActionEvent event) {
       try {
        Parent root = FXMLLoader.load(getClass().getResource("/ViewBooks/viewBooks.fxml"));
        Scene scene = new Scene(root);
        Stage stage  = new Stage();
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

     private void deleteToDatabase(Vector dataHolder) {
            String bookId="";
            DatabaseHandler handler = DatabaseHandler.getInstance();
            for(int i=0;i<dataHolder.size(); i++){
               Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
               HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(0);
               String st = myCell.toString();
               bookId = st;
               System.out.println(bookId);
               String query = "DELETE FROM BOOKS WHERE BooKId = '"+bookId+"'";
               handler.execAction(query);
            }
        }
    }
