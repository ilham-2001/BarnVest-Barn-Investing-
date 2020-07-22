package Controller;

import Model.UserDataClass;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SellPageController implements Initializable {
    
    @FXML private TextField tfJumlahHwn;
    @FXML private ChoiceBox<String> cbKota;
    @FXML private ChoiceBox<String> cbJenisHewan;
    @FXML private TableView<UserDataClass> tvSell;
    @FXML private TableColumn<UserDataClass, Integer> tcJumlah;
    @FXML private TableColumn<UserDataClass, String> tcKota;
    @FXML private TableColumn<UserDataClass, String> tcJenishwn;
    
    ObservableList<UserDataClass> listUser = FXCollections.observableArrayList();
    UserDataClass user;
    
    @FXML
    private void SellHandleButton(){
        user = new UserDataClass();
        try{
        user.setJumlahHwn(Integer.parseInt(tfJumlahHwn.getText()));
        user.setKota(cbKota.getValue());
        user.setJenisHwn(cbJenisHewan.getValue());
        listUser.add(user);
        tvSell.setItems(listUser);
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println("Tipe data Masukkan salah");
            alert.setTitle("ERROR TYPE !!");
            alert.setHeaderText("Inputan Salah !!");
            alert.setContentText("Inputan harus berupa Integer pada Jumlah hewan");
            alert.showAndWait();
        }
        

    }
    
    @FXML
    private void UpdateHandleButton()throws IOException{
        UserDataClass TableSelect = tvSell.getSelectionModel().getSelectedItem();
        if(TableSelect != null){
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UpdatePage.fxml"));
          AnchorPane page = (AnchorPane) loader.load();

          Stage stage = new Stage();
          stage.setTitle("UPDATE DATA");
          stage.initModality(Modality.APPLICATION_MODAL);

          Scene scene = new Scene(page);
          stage.setScene(scene);
          
          // GET CONTROLLER UPDATE
          // UPDATE ACCESS
          UpdatePageController controlUpdate = loader.getController();
          controlUpdate.setControllerUpdate(stage);
          controlUpdate.setUpdate(TableSelect);
          
          stage.show();
        }
    }
   
    @FXML
    private void DeleteHandleButton(){
       int tableSelected = tvSell.getSelectionModel().getSelectedIndex();
       if(tableSelected >=0 ){
           tvSell.getItems().remove(tableSelected);
       }else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Tidak ada pilihan");
           alert.setHeaderText("Belum ada Data yang dipilih");
           alert.setContentText("Pilih  Data pada tabel");

           alert.showAndWait();
       }
    }
    
    @FXML
    private void BackHandleButton(ActionEvent event) throws IOException{
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/HomePagePeternak.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("HOME PAGE");
        stage.setScene(sewaScene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbKota.getItems().addAll("-Pilih Kota-","Yogyakarta","Solo");
        cbKota.setValue("-Pilih Kota-");
        cbJenisHewan.getItems().addAll("-Jenis Hewan-","Sapi","Kambing");
        cbJenisHewan.setValue("-Jenis Hewan-");
        
        // Show to table
        tcJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlahHwn"));
        tcKota.setCellValueFactory(new PropertyValueFactory<>("kota"));
        tcJenishwn.setCellValueFactory(new PropertyValueFactory<>("jenisHwn"));
        
        tcJumlah.setCellValueFactory(cellData-> cellData.getValue().tcJumlahHwnProperty().asObject());
        tcKota.setCellValueFactory(cellData-> cellData.getValue().tcKotaProperty());
        tcJenishwn.setCellValueFactory(cellData-> cellData.getValue().tcJenisHwnProperty());    
    }    
    
}
