package projetoencomendadeplacas.Screens;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import projetoencomendadeplacas.DAOs.ClienteDAO;
import projetoencomendadeplacas.Entities.Cliente;
import projetoencomendadeplacas.Utils.Modal;

public class ClienteScreenController implements Initializable {
    
    ObservableList<Cliente> lista;
    ClienteDAO banco;
    Cliente clienteSelecionado;
    
    @FXML
    private Label clienteIdLabel;
    @FXML
    private TableView<Cliente> clienteTableView;
    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colNome;
    @FXML
    private TableColumn<Cliente, String> colDocumento;
    @FXML
    private TableColumn<Cliente, String> colTelefone;
    @FXML
    private TextField clienteNomeTextfield;
    @FXML
    private TextField clienteDocumentoTextfield;
    @FXML
    private TextField clienteTelefoneTextfield;
    @FXML
    private ToggleButton novoClienteToggleButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            banco = new ClienteDAO();
            clienteSelecionado = new Cliente();
            lista = FXCollections.observableArrayList(banco.getAll());
            montaTabela();
        } catch(Exception e){
            Modal.displayMensagem("ERROR", e.getMessage());
        }
    }
    
    @FXML
    private void gravar() {
        try{
            String mensagemTipo = "Gravação";
            Cliente novoCliente = montarCliente();
            if (novoClienteToggleButton.isSelected()) {
                banco.inserir(novoCliente);
                lista.add(novoCliente);
            }else{
                if(novoCliente.getId() == 0){
                    Modal.displayMensagem("WARNING", "Selecione um cliente para alterar", "Atenção!");
                    return;
                }
                banco.editar(novoCliente);
                lista.remove(novoCliente);
                lista.add(novoCliente);
                mensagemTipo = "Edição";
            }
            limparTela();
            Modal.displayMensagem("INFORMATION", mensagemTipo + " realizada com sucesso", "Sucesso!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void excluir() throws Exception {
        if(Modal.displayConfirmacao("CONFIRMATION", "Confirma a exclusão do cliente de CPF: " + clienteSelecionado.getCpfcnpj(), "Exclusão") == ButtonType.OK){
            banco.deletar(clienteSelecionado);
            lista.remove(clienteSelecionado);
            Modal.displayMensagem("INFORMATION", "Exclusão realizada com sucesso", "Operação com sucesso");
            limparTela();
        }
    }
    
    private void mostrarClientes(Cliente selectedItem) {
        clienteSelecionado = selectedItem;
        if(novoClienteToggleButton.isSelected())
            novoClienteToggleButton.fire();
        this.clienteIdLabel.setText(String.valueOf(clienteSelecionado.getId()));
        this.clienteNomeTextfield.setText(clienteSelecionado.getNome());
        this.clienteDocumentoTextfield.setText(String.valueOf(clienteSelecionado.getCpfcnpj()));
        this.clienteTelefoneTextfield.setText(String.valueOf(clienteSelecionado.getTelefone()));
        this.clienteNomeTextfield.requestFocus();
    }
    
    private Cliente montarCliente(){
        Cliente novoCliente = new Cliente();
        novoCliente.setId(Integer.valueOf(clienteIdLabel.getText()));
        novoCliente.setNome(clienteNomeTextfield.getText());
        novoCliente.setCpfcnpj(clienteDocumentoTextfield.getText());
        novoCliente.setTelefone(clienteTelefoneTextfield.getText());
        return novoCliente;
    }
    
    private void montaTabela() {
        clienteTableView.setItems(lista);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("cpfcnpj"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        clienteTableView.getSelectionModel().selectedIndexProperty().addListener(
                evt -> {
                    mostrarClientes(clienteTableView.getSelectionModel().getSelectedItem());
                }
        );
    }
    
    private void limparTela() {
        this.clienteIdLabel.setText("0");
        this.clienteNomeTextfield.clear();
        this.clienteDocumentoTextfield.clear();
        this.clienteTelefoneTextfield.clear();
        this.clienteNomeTextfield.requestFocus();
    }
}
