package projetoencomendadeplacas.Screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {
    
    final Logger logger;

    public MainScreenController() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openClienteScreen() {
        try {
            setStage("clienteScreen.fxml", "Cliente Manager");
            logger.info("Nova janela[Cliente] criada com sucesso");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao criar nova janela cliente.", e);
        }
    }

    @FXML
    private void openEncomendaScreen() {
        try {
            setStage("encomendaScreen.fxml", "Cliente Manager");
            logger.info("Nova janela[Encomenda] criada com sucesso");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao criar nova janela Encomenda.", e);
        }
    }
    
    private void setStage(String path, String title) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage. setResizable(false);
        stage.show();
    }
}
