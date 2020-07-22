package Controller;

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
import javafx.stage.Stage;

public class HomePagePeternakController implements Initializable {

    // SHOW REGISTER PAGE
    @FXML
    private void RegisterHandleButton(ActionEvent event) throws IOException{
        Parent registerParent = FXMLLoader.load(getClass().getResource("/View/RegisterPage.fxml"));
        Scene registerScene = new Scene(registerParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Register Page");
        stage.setScene(registerScene);
        stage.show();
    }
    
    // SHOW REPORT PAGE
    @FXML
    private void reportHandleButton(ActionEvent event) throws IOException{
        Parent reportParent = FXMLLoader.load(getClass().getResource("/View/ReportClass.fxml"));
        Scene reportScene = new Scene(reportParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Report Page");
        stage.setScene(reportScene);
        stage.show();   
    }
    
     // LOG OUT FROM APP
    @FXML
    private void logOutHandleButton(ActionEvent event) throws IOException{
        Parent reportParent = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
        Scene reportScene = new Scene(reportParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("BarnVest Home");
        stage.setScene(reportScene);
        stage.show();   
    }
    
     // SHOW SELL PAGE
    @FXML
    private void SellHandleButton(ActionEvent event) throws IOException{
        Parent sellParent = FXMLLoader.load(getClass().getResource("/View/SellPage.fxml"));
        Scene sellScene = new Scene(sellParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Buy Page");
        stage.setScene(sellScene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
