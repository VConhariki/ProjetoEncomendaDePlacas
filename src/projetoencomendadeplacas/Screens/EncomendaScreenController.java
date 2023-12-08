package projetoencomendadeplacas.Screens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import projetoencomendadeplacas.DAOs.ClienteDAO;
import projetoencomendadeplacas.DAOs.EncomendaDAO;
import projetoencomendadeplacas.Entities.Cliente;
import projetoencomendadeplacas.Entities.Encomenda;
import projetoencomendadeplacas.Utils.Enums.CorFraseEnum;
import projetoencomendadeplacas.Utils.Enums.CorPlacaEnum;
import projetoencomendadeplacas.Utils.Enums.FormaPagamentoEnum;
import projetoencomendadeplacas.Utils.Modal;

public class EncomendaScreenController implements Initializable {

    ObservableList<Encomenda> lista;
    EncomendaDAO banco;
    Encomenda encomendaSelecionada;
    ClienteDAO clienteDAO;
    List<Cliente> listClientes;
    private static final Double CUSTO_MATERIAL_BASE = 147.30;
    private static final Double CUSTO_FRASE_BASE = 0.32;
    private static final int LIMITE_DIARIO = 6;
    
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
    private TableColumn<Encomenda, String> colDataEntrega;
    @FXML
    private TableColumn<Encomenda, Double> colValorServico;
    @FXML
    private TableColumn<Encomenda, Double> colValorSinal;
    @FXML
    private TableColumn<Encomenda, String> colFormaPagamento;
    @FXML
    private TableColumn<Encomenda, Boolean> colPendente;
    @FXML
    private TextField cpfClienteTextfield;
    @FXML
    private TableColumn<Encomenda, String> colCliente;
    @FXML
    private Button concluirPagamentoButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        encomendaCorPlacaCombobox.getItems().addAll(CorPlacaEnum.values());
        encomendaCorPlacaCombobox.setValue(CorPlacaEnum.values()[0]);
        encomendaCorFraseCombobox.getItems().addAll(CorFraseEnum.values());
        encomendaCorFraseCombobox.setValue(CorFraseEnum.values()[0]);
        encomendaFormaPagamentoCombobox.getItems().addAll(FormaPagamentoEnum.values());
        encomendaFormaPagamentoCombobox.setValue(FormaPagamentoEnum.values()[0]);
        dataEntregaDatepicker.setValue(LocalDate.now());
        concluirPagamentoButton.setDisable(true);
        
        try{
            banco = new EncomendaDAO();
            clienteDAO = new ClienteDAO();
            encomendaSelecionada = new Encomenda();
            lista = FXCollections.observableArrayList(banco.getAll());
            listClientes = clienteDAO.getAll();
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
            if(novaEncomenda == null)
                return;
            System.out.println(novaEncomenda.toString());
            banco.inserir(novaEncomenda);
            lista.add(novaEncomenda);
            montaTabela();
            concluirPagamentoButton.setDisable(true);
            Modal.displayMensagem("INFORMATION", mensagemTipo + " realizada com sucesso", "Sucesso!");
            limparTela();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private Encomenda montarEncomenda(){
        Encomenda novaEncomenda = new Encomenda();
        Date dataEntrega = Date.from(dataEntregaDatepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Boolean limiteAtingido = estaNoLimite(dataEntrega);
        if(limiteAtingido)
            return null;
        Cliente cliente = obterCliente(cpfClienteTextfield.getText());
        if(cliente == null)
            return null;
        
        novaEncomenda.setCpfcnpj(cliente);
        novaEncomenda.setId(Integer.valueOf(encomendasIdLabel.getText()));
        novaEncomenda.setAlturaplaca(Double.valueOf(encomendaAlturaTextfield.getText()));
        novaEncomenda.setLarguraplaca(Double.valueOf(larguraEcomendaTextfield.getText()));
        novaEncomenda.setFrase(encomendaFraseTextfield.getText());
        novaEncomenda.setCorfrase(encomendaCorFraseCombobox.getValue().ordinal());
        novaEncomenda.setCorplaca(encomendaCorPlacaCombobox.getValue().ordinal());
        novaEncomenda.setDataentrega(dataEntrega);
        novaEncomenda.setFormapagamento(encomendaFormaPagamentoCombobox.getValue().ordinal());
        novaEncomenda.setPagamentopendente(Boolean.TRUE);
        novaEncomenda.setValorservico(calcularValorServico(novaEncomenda));
        novaEncomenda.setValorsinal(calcularValorSinal(novaEncomenda.getValorservico()));
        return novaEncomenda;
    }
    
    private void mostrarEncomenda(Encomenda selectedItem) {
        concluirPagamentoButton.setDisable(false);
        encomendaSelecionada = selectedItem;
        this.encomendasIdLabel.setText(String.valueOf(encomendaSelecionada.getId()));
        this.encomendaAlturaTextfield.setText(encomendaSelecionada.getAlturaplaca().toString());
        this.larguraEcomendaTextfield.setText(encomendaSelecionada.getLarguraplaca().toString());
        this.encomendaFraseTextfield.setText(encomendaSelecionada.getFrase());
        this.cpfClienteTextfield.setText(String.valueOf(encomendaSelecionada.getCpfcnpj().toString()));
        this.encomendaCorPlacaCombobox.setValue(CorPlacaEnum.values()[encomendaSelecionada.getCorplaca()]);
        this.encomendaCorFraseCombobox.setValue(CorFraseEnum.values()[encomendaSelecionada.getCorfrase()]);
        this.encomendaFormaPagamentoCombobox.setValue(FormaPagamentoEnum.values()[encomendaSelecionada.getFormapagamento()]);
        Instant instant = encomendaSelecionada.getDataentrega().toInstant();
        this.dataEntregaDatepicker.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
    }
    
    private void montaTabela() {
        lista.forEach((e) -> {
            e.setTransientFields();
        });
        
        encomendaTableview.setItems(lista);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cpfcnpj"));
        colAltura.setCellValueFactory(new PropertyValueFactory<>("alturaplaca"));
        colLargura.setCellValueFactory(new PropertyValueFactory<>("larguraplaca"));
        colFrase.setCellValueFactory(new PropertyValueFactory<>("frase"));
        colCorPlaca.setCellValueFactory(new PropertyValueFactory<>("corPlacaDescricao"));
        colCorFrase.setCellValueFactory(new PropertyValueFactory<>("corFraseDescricao"));
        colDataEntrega.setCellValueFactory(new PropertyValueFactory<>("dataFormatada"));
        colValorServico.setCellValueFactory(new PropertyValueFactory<>("valorservico"));
        colValorSinal.setCellValueFactory(new PropertyValueFactory<>("valorsinal"));
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamentoDescricao"));
        colPendente.setCellValueFactory(new PropertyValueFactory<>("pagamentopendente"));
        
        encomendaTableview.getSelectionModel().selectedIndexProperty().addListener(
            evt -> {
                mostrarEncomenda(encomendaTableview.getSelectionModel().getSelectedItem());
            }
        );
    }
    
    private Double calcularValorServico(Encomenda encomenda){
        Double area = encomenda.getAlturaplaca() * encomenda.getLarguraplaca();
        Double custoMaterial = area * CUSTO_MATERIAL_BASE;
        Double custoDesenho = encomenda.getFrase().length() * CUSTO_FRASE_BASE;
        Double valorTotal = custoMaterial + custoDesenho;
        
        Long finalValue = Math.round(valorTotal);
        return finalValue.doubleValue();
    }
    
    private Double calcularValorSinal(Double custoPlaca){
        return custoPlaca/2;
    }
    
    private Boolean estaNoLimite(Date dataAlvo){
        int contadorCasos = 0;
        for (Encomenda atual : lista) {
            if (atual.getDataentrega().equals(dataAlvo)) {
                contadorCasos++;
            }
            if (contadorCasos >= LIMITE_DIARIO) {
                Modal.displayMensagem("ERROR", "Limite diário atingido! Selecione outra data.", "Atenção!");
                return true;
            }
        }
        return false;
    }

    private Cliente obterCliente(String cpf) {
        try{
            for(Cliente cliente : listClientes){
                if(cliente.getCpfcnpj().equals(cpf))
                    return cliente;
            }
            Modal.displayMensagem("ERROR", "Cliente não encontrado!", "Atenção!");
            return null;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    private void limparTela() {
        this.encomendasIdLabel.setText("0");
        this.encomendaAlturaTextfield.clear();
        this.larguraEcomendaTextfield.clear();
        this.encomendaFraseTextfield.clear();
        this.cpfClienteTextfield.clear();
        this.encomendaCorPlacaCombobox.setValue(CorPlacaEnum.values()[0]);
        this.encomendaCorFraseCombobox.setValue(CorFraseEnum.values()[0]);
        this.encomendaFormaPagamentoCombobox.setValue(FormaPagamentoEnum.values()[0]);
        this.dataEntregaDatepicker.setValue(LocalDate.now());
    }

    @FXML
    private void concluirPagamento(ActionEvent event) throws Exception {
        try{
            if(!encomendaSelecionada.getPagamentopendente()){
                Modal.displayMensagem("WARNING", "O cliente já efetuou o pagamento", "Atenção!");
                return;
            }
            lista.remove(encomendaSelecionada);
            encomendaSelecionada.setPagamentopendente(Boolean.FALSE);
            banco.editar(encomendaSelecionada);
            lista.add(encomendaSelecionada);
            montaTabela();
            limparTela();
            gerarVias(encomendaSelecionada);
            Modal.displayMensagem("INFORMATION","Serviço concluído com sucesso!", "Sucesso!");
        }catch(Exception ex){
            ex.printStackTrace();
            Modal.displayMensagem("ERROR", "Ocorreu um erro ao finalizar entrega da placa!", "Atenção!");
        }
    }
    
    private void gerarVias(Encomenda encomenda){
        gerarViaCliente(encomenda);
        gerarViaEmpresa(encomenda);
    }
    
    private void gerarViaCliente(Encomenda encomenda){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(encomenda.getId() + "-ViaDoCliente.txt"))) {
            writer.write("VIA DO CLIENTE: " + encomenda.getCpfcnpj());
            writer.newLine();
            writer.write("Altura Placa: " + encomenda.getAlturaplaca());
            writer.newLine();
            writer.write("Largura Placa: " + encomenda.getLarguraplaca());
            writer.newLine();
            writer.write("Valor: " + encomenda.getValorservico());
            writer.newLine();
            writer.write("Forma de Pagamento: " + encomenda.getFormaPagamentoDescricao());
            writer.newLine();
            writer.write("Data Entrega: " + encomenda.getDataFormatada());

            System.out.println("Atributos escritos com sucesso em ViaDoCliente.txt");
        } catch (IOException e) {
            System.err.println("Erro ao escrever os atributos em ViaDoCliente.txt: " + e.getMessage());
        }
    }
    
    private void gerarViaEmpresa(Encomenda encomenda){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(encomenda.getId() + "-ViaDaEmpresa.txt"))) {
            writer.write("VIA DO CLIENTE: " + encomenda.getCpfcnpj());
            writer.newLine();
            writer.write("Altura Placa: " + encomenda.getAlturaplaca());
            writer.newLine();
            writer.write("Largura Placa: " + encomenda.getLarguraplaca());
            writer.newLine();
            writer.write("Valor: " + encomenda.getValorservico());
            writer.newLine();
            writer.write("Forma de Pagamento: " + encomenda.getFormaPagamentoDescricao());
            writer.newLine();
            writer.write("Data Entrega: " + encomenda.getDataFormatada());

            System.out.println("Atributos escritos com sucesso em ViaDaEmpresa.txt");
        } catch (IOException e) {
            System.err.println("Erro ao escrever os atributos em ViaDaEmpresa.txt: " + e.getMessage());
        }
    }
}
