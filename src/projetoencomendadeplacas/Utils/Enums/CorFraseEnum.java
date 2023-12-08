package projetoencomendadeplacas.Utils.Enums;

public enum CorFraseEnum {
    AZUL("Azul"),
    VERMELHO("Vermelho"),
    AMARELO("Amarelo"),
    PRETO("Preto"),
    VERDE("Verde");

    private final String descricao;

    CorFraseEnum(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public static String getDescricaoPelaPosicao(int posicao) {
        return values()[posicao].getDescricao();
    }
}

