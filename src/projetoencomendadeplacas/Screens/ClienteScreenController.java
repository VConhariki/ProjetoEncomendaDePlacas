package projetoencomendadeplacas.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import projetoencomendadeplacas.DAOs.ClienteDAO;
import projetoencomendadeplacas.DTOs.ClienteTableViewDTO;
import projetoencomendadeplacas.Entities.Cliente;

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
    private TableView<Cliente> ClienteTableView;
    @FXML
    private Pane clienteScreen;

    ObservableList<Cliente> lista;
    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    ClienteDAO banco;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            banco = new ClienteDAO();
            lista = FXCollections.observableArrayList(banco.getAll());
            montaTabela();
        } catch(Exception e){
//            CaixaDialogo.displayMensagem("ERROR", e.getMessage());
        }
    }    
    
    private void montaTabela() {
        ClienteTableView.setItems(lista);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
//        ClienteTableView.getSelectionModel().selectedIndexProperty().addListener(
//                evt -> {
//                    mostraProdutos(ClienteTableView.getSelectionModel().getSelectedItem());
//                }
//        );
    }
    
//    private void mostraProdutos(Cliente selectedItem) {
//        regAtual = selectedItem;
//        this.txCodigo.setText(String.valueOf(regAtual.getCodigo()));
//        this.txNome.setText(regAtual.getNome());
//        this.txPreco.setText(String.valueOf(regAtual.getPreco()));
//        this.txUnidade.setText(regAtual.getUnidade());
//        this.txCodigo.requestFocus();
//    }
}
