package Controller;

import Model.UserDataClass;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarnGrowthController implements Initializable {
    @FXML private BarChart bcGrowth;
    XYChart.Series data = new XYChart.Series<>();
    UserDataClass user[];
    
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException{
        Parent sewaParent = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
        Scene backScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Pengisian Data Sewa");
        stage.setScene(backScene);
        stage.show();
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMethod();
        int x = 0, a = 0, b = 0, c = 0, d  = 0;
        
        while(user[x] != null){
            x++;
        }
        
        for(int i = 0;i < x;i++){
            switch (user[i].getNama()) {
                case "Ternak Budi":
                    a = a + user[i].getJumlahInvest();
                    break;
                case "Ternak Kesbor":
                    b = b + user[i].getJumlahInvest();
                    break;
                case "Ternak Kiwil":
                    c = c + user[i].getJumlahInvest();
                    break;
                case "Ternak Bambang":
                    d = d + user[i].getJumlahInvest();
                    break;
                default:
                    break;
            }
        }
        
        data.getData().add(new XYChart.Data("Ternak Budi", a));
        data.getData().add(new XYChart.Data("Ternak Kesbor", b));
        data.getData().add(new XYChart.Data("Ternak Kiwil", c));
        data.getData().add(new XYChart.Data("Ternak Bambang", d));

        bcGrowth.getData().addAll(data);
    }    
    
}
