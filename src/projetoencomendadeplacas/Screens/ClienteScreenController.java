package projetoencomendadeplacas.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import projetoencomendadeplacas.DTOs.ClienteTableViewDTO;

public class ClienteScreenController implements Initializable {

    @FXML
    private Label clienteIdLabel;
    @FXML
    private TextField ClientNomeTextfield;
    @FXML
    private TextField ClientDocumentoTextfield;
    @FXML
    private TextField ClientTelefoneTextfield;
    @FXML
    private Button ClienteGravarButton;
    @FXML
    private Button ClienteExcluirButton;
    @FXML
    private TableView<ClienteTableViewDTO> ClienteTableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
