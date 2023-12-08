package projetoencomendadeplacas.Utils.Enums;

public enum FormaPagamentoEnum {
    DINHEIRO("Dinheiro"),
    CARTAO_DEBITO("Cartão de Débito"),
    CARTAO_CREDITO("Cartão de Crédito"),
    BOLETO_BANCARIO("Boleto Bancário"),
    PIX("PIX");

    private final String descricao;

    FormaPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static String getDescricaoPelaPosicao(int posicao) {
        return values()[posicao].getDescricao();
    }
}
