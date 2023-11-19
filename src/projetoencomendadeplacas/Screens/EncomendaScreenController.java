package projetoencomendadeplacas.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EncomendaScreenController implements Initializable {

    @FXML
    private Pane encomendaScreen;
    @FXML
    private Label encomendasIdLabel;
    @FXML
    private TextField encomendaAlturaTextfield;
    @FXML
    private TextField larguraAlturaTextfield;
    @FXML
    private TextField encomendaFraseTextfield;
    @FXML
    private ComboBox<?> encomendaCorPlacaCombobox;
    @FXML
    private ComboBox<?> encomendaCorFraseCombobox;
    @FXML
    private DatePicker dataEntregaDatepicker;
    @FXML
    private ComboBox<?> encomendaFormaPagamentoCombobox;
    @FXML
    private Button encomendaGravarButton;
    @FXML
    private Button encomendaExcluirButton;
    @FXML
    private TableView<?> encomendaTableview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
