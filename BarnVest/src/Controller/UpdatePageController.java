
package Controller;

import Model.UserDataClass;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdatePageController implements Initializable {
    @FXML private TextField tfJumlahHwn;
    @FXML private ChoiceBox<String> cbKota;
    @FXML private ChoiceBox<String> cbJenisHewan;
    
    private UserDataClass user;
    private Stage controlUpdate;
   
    public void setUpdate(UserDataClass user){
        this.user = user;
        tfJumlahHwn.setText("" + user.getJumlahHwn());
        cbKota.setValue(user.getKota());
        cbJenisHewan.setValue(user.getJenisHwn());
    }
    
    @FXML
    private void UpdateHandleButton(){
        user.setJumlahHwn(Integer.parseInt(tfJumlahHwn.getText()));
        user.setKota(cbKota.getValue());
        user.setJenisHwn(cbJenisHewan.getValue());
        
        controlUpdate.close();
    }
    
    public void setControllerUpdate(Stage controlUpdate){
        this.controlUpdate = controlUpdate;
    }
    
    @FXML
    private void CancelButtonAction(){
      controlUpdate.close();
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbKota.getItems().addAll("-Pilih Kota-","Yogyakarta","Solo");
        cbKota.setValue("-Pilih Kota-");
        cbJenisHewan.getItems().addAll("-Jenis Hewan-","Sapi","Kambing");
        cbJenisHewan.setValue("-Jenis Hewan-");
    }    
    
}
