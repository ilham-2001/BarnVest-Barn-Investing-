
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


public class HomePageController implements Initializable {
    
    // SHOW INVEST PAGE
    @FXML
    public void InvestHandleButton(ActionEvent event) throws IOException{
        Parent investParent = FXMLLoader.load(getClass().getResource("/View/InvestPage.fxml"));
        Scene investScene = new Scene(investParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Invest Page");
        stage.setScene(investScene);
        stage.show();
    }
    
    // SHOW REPORT PAGE
    @FXML
    private void growthHandleButton(ActionEvent event) throws IOException{
        Parent investParent = FXMLLoader.load(getClass().getResource("/View/BarnGrowth.fxml"));
        Scene investScene = new Scene(investParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Growth Page");
        stage.setScene(investScene);
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
    
    @FXML
    private void profileHandleButton(ActionEvent event) throws IOException{
        Parent reportParent = FXMLLoader.load(getClass().getResource("/View/ProfileAccountClass.fxml"));
        Scene reportScene = new Scene(reportParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("BarnVest Home");
        stage.setScene(reportScene);
        stage.show(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
