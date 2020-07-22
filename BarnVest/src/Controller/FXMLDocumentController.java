
package Controller;

import Model.AccountDataClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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


public class FXMLDocumentController implements Initializable {
    @FXML private TextField userName;
    @FXML private PasswordField passWord;
    
    AccountDataClass[] AccData;
    
    // SHOW HOME PAGE (LOGIN)
    @FXML
    private void handleButtonLogin(ActionEvent event) throws IOException{
        loadMethod();
        
        String uname = userName.getText();
        String pass = passWord.getText();
        int i = 0;
        while(AccData[i] != null && (!AccData[i].getUserName().equals(uname) || 
                !AccData[i].getEmail().equals(uname)) && !AccData[i].getPassword().equals(pass)){
            i++;
        }
            if(AccData[i].getStatusUser().equals("Investor") || AccData[i].getStatusUser().equals("Masyarakat Umum")){
            Parent loginParent = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
            Scene loginScene = new Scene(loginParent);
        
            System.out.println("Indeks: " + i);
            ProfileAccountClassController.setIndex(i);
            
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("HOME PAGE BARNVEST");                      
            stage.setScene(loginScene);
            stage.show();
            }
            
            else if(AccData[i].getStatusUser().equals("Peternak")){
            Parent loginParent = FXMLLoader.load(getClass().getResource("/View/HomePagePeternak.fxml"));
            Scene loginScene = new Scene(loginParent);
        
            
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("HOME PAGE BARNVEST");                      
            stage.setScene(loginScene);
            stage.show();
            }
            
        System.out.println("Button Clicked");
    }
    
    // SHOW SIGN UP PAGE
    @FXML
    private void handleButtonSignUp(ActionEvent event) throws IOException{
        Parent signUpParent = FXMLLoader.load(getClass().getResource("/View/SignUp.fxml"));
        Scene signUpScene = new Scene(signUpParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("SIGN UP PAGE");
        stage.setScene(signUpScene);
        stage.show();
    }
    
    // Take XML data file
    public void loadMethod(){
        XStream xstream = new XStream(new StaxDriver());
        try {
            FileInputStream ld = new FileInputStream("savedData.xml");
            int isi, i=0;char c; String s="";
            while((isi = ld.read()) != -1){
                c = (char)isi;
                s = s+c;
                i++;
            }
            AccData = (AccountDataClass[])xstream.fromXML(s);
            ld.close();
            
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AccData = new AccountDataClass[100];
    }    
}
