package projetoencomendadeplacas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projetoencomendadeplacas.Utils.Databases.Database;
import java.io.IOException;

public class ProjetoEncomendaDePlacas extends Application {

    public ProjetoEncomendaDePlacas() {
        Database db = new Database();
        db.createDatabase();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ProjetoEncomendaDePlacas.class.getResource("Screens/mainScreen.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Encomenda De Placas");
        stage.setScene(scene);
        stage. setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    } 
}