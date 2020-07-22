package Controller;

import Model.UserDataClass;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateReportClassController implements Initializable {
    @FXML private TextField tfNama;
    @FXML private TextField tfTarget;
    @FXML private TextField tfAlamat;
    @FXML private ChoiceBox cbxStatus;
    
    int index;
    UserDataClass[] user;
    
    public void setDefault(UserDataClass user){
        tfNama.setText(user.getNama());
        tfTarget.setText(String.valueOf(user.getTarget()));
        tfAlamat.setText(user.getAlamat());
        cbxStatus.setValue(user.getStatus());
    }
    
    @FXML
    private void handleButtonUpdate(ActionEvent event){
        loadMethod();
    try{
        user[index].setNama(tfNama.getText());
        user[index].setAlamat(tfAlamat.getText());
        user[index].setTarget(Integer.parseInt(tfTarget.getText()));
        user[index].setStatus((String) cbxStatus.getValue());
        saveMethod();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Data successfuly changed");
        alert.showAndWait();
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    catch(Exception e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing Requirement Found!!!");
        alert.setHeaderText("Fill all the requirement");
        alert.setContentText(null);
        alert.showAndWait();
    }
    
    }
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    private void loadMethod(){
        XStream xstream = new XStream(new StaxDriver());
        try {
            FileInputStream fis = new FileInputStream("SavedFileTableData.xml");
            int isi, i=0;char c; String s="";
            
            while((isi = fis.read()) != -1){
                c = (char)isi;
                s = s+c;
                i++;
            }
            user = (UserDataClass[])xstream.fromXML(s);
            fis.close();
        } catch (IOException e){
            System.err.println("Terjadi Kesalahan"+e.getMessage());
        }
    }
    
    private void saveMethod(){ 
        XStream save = new XStream(new StaxDriver());
        String xml = save.toXML(user);
        
        try{
            FileOutputStream sd = new FileOutputStream("savedFileTableData.xml");
            byte[] bit = xml.getBytes("UTF-8");
            sd.write(bit);
            sd.close();
        }catch(IOException e){
            System.err.println("Terjadi kesalahan"+e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       user = new UserDataClass[999];
       cbxStatus.getItems().addAll("Open for invest", "Closed for invest");
    }    
    
}
