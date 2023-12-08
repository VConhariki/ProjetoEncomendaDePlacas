package projetoencomendadeplacas.Screens;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import projetoencomendadeplacas.DAOs.EncomendaDAO;
import projetoencomendadeplacas.Entities.Encomenda;
import projetoencomendadeplacas.Utils.Enums.CorFraseEnum;
import projetoencomendadeplacas.Utils.Enums.CorPlacaEnum;
import projetoencomendadeplacas.Utils.Enums.FormaPagamentoEnum;
import projetoencomendadeplacas.Utils.Modal;

public class EncomendaScreenController implements Initializable {

    ObservableList<Encomenda> lista;
    EncomendaDAO banco;
    Encomenda encomendaSelecionada;
    
    @FXML
    private Label encomendasIdLabel;
    @FXML
    private TextField encomendaAlturaTextfield;
    @FXML
    private TextField encomendaFraseTextfield;
    @FXML
    private ComboBox<CorPlacaEnum> encomendaCorPlacaCombobox;
    @FXML
    private ComboBox<CorFraseEnum> encomendaCorFraseCombobox;
    @FXML
    private DatePicker dataEntregaDatepicker;
    @FXML
    private ComboBox<FormaPagamentoEnum> encomendaFormaPagamentoCombobox;
    @FXML
    private TableView<Encomenda> encomendaTableview;
    @FXML
    private TextField larguraEcomendaTextfield;
    @FXML
    private TableColumn<Encomenda, Integer> colId;
    @FXML
    private TableColumn<Encomenda, Double> colAltura;
    @FXML
    private TableColumn<Encomenda, Double> colLargura;
    @FXML
    private TableColumn<Encomenda, String> colFrase;
    @FXML
    private TableColumn<Encomenda, String> colCorPlaca;
    @FXML
    private TableColumn<Encomenda, String> colCorFrase;   
    @FXML
    private TableColumn<Encomenda, Date> colDataEntrega;
    @FXML
    private TableColumn<Encomenda, Double> colValorServico;
    @FXML
    private TableColumn<Encomenda, Double> colValorSinal;
    @FXML
    private TableColumn<Encomenda, String> colFormaPagamento;
    @FXML
    private TableColumn<Encomenda, Boolean> colPendente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        encomendaCorPlacaCombobox.getItems().addAll(CorPlacaEnum.values());
        encomendaCorPlacaCombobox.setValue(CorPlacaEnum.values()[0]);
        encomendaCorFraseCombobox.getItems().addAll(CorFraseEnum.values());
        encomendaCorFraseCombobox.setValue(CorFraseEnum.values()[0]);
        encomendaFormaPagamentoCombobox.getItems().addAll(FormaPagamentoEnum.values());
        encomendaFormaPagamentoCombobox.setValue(FormaPagamentoEnum.values()[0]);
        dataEntregaDatepicker.setValue(LocalDate.now());
        
        try{
            banco = new EncomendaDAO();
            encomendaSelecionada = new Encomenda();
            lista = FXCollections.observableArrayList(banco.getAll());
            montaTabela();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void gravar() {
        try{
            String mensagemTipo = "Gravação";
            Encomenda novaEncomenda = montarEncomenda();
            System.out.println(novaEncomenda.toString());
            banco.inserir(novaEncomenda);
            lista.add(novaEncomenda);
            Modal.displayMensagem("INFORMATION", mensagemTipo + " realizada com sucesso", "Sucesso!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private Encomenda montarEncomenda(){
        Encomenda novaEncomenda = new Encomenda();
        novaEncomenda.setId(Integer.valueOf(encomendasIdLabel.getText()));
        novaEncomenda.setAlturaplaca(Double.valueOf(encomendaAlturaTextfield.getText()));
        novaEncomenda.setLarguraplaca(Double.valueOf(larguraEcomendaTextfield.getText()));
        novaEncomenda.setFrase(encomendaFraseTextfield.getText());
        novaEncomenda.setCorfrase(encomendaCorFraseCombobox.getValue().ordinal());
        novaEncomenda.setCorplaca(encomendaCorPlacaCombobox.getValue().ordinal());
        novaEncomenda.setDataentrega(Date.from(dataEntregaDatepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        novaEncomenda.setFormapagamento(encomendaFormaPagamentoCombobox.getValue().ordinal());
        novaEncomenda.setPagamentopendente(Boolean.TRUE);
        novaEncomenda.setValorservico(Double.valueOf("2"));
        novaEncomenda.setValorsinal(Double.valueOf("1"));
        return novaEncomenda;
    }
    
    private void montaTabela() {
        lista.forEach((e) -> {
            e.setEnumsDescriptions();
        });
        
        encomendaTableview.setItems(lista);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("alturaplaca"));
        colLargura.setCellValueFactory(new PropertyValueFactory<>("larguraplaca"));
        colFrase.setCellValueFactory(new PropertyValueFactory<>("frase"));
        colCorPlaca.setCellValueFactory(new PropertyValueFactory<>("corPlacaDescricao"));
        colCorFrase.setCellValueFactory(new PropertyValueFactory<>("corFraseDescricao"));
        colDataEntrega.setCellValueFactory(new PropertyValueFactory<>("dataentrega"));
        colValorServico.setCellValueFactory(new PropertyValueFactory<>("valorservico"));
        colValorSinal.setCellValueFactory(new PropertyValueFactory<>("valorsinal"));
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamentoDescricao"));
        colPendente.setCellValueFactory(new PropertyValueFactory<>("pagamentopendente"));
     
        
        encomendaTableview.getSelectionModel().selectedIndexProperty().addListener(
            evt -> {
//                mostrarClientes(encomendaTableview.getSelectionModel().getSelectedItem());
            }
        );
    }
}
