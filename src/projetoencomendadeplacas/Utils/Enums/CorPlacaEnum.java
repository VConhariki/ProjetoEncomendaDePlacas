package projetoencomendadeplacas.Utils.Enums;

public enum CorPlacaEnum {
    BRANCA("Branca"),
    CINZA("Cinza");

    private final String descricao;

    CorPlacaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static String getDescricaoPelaPosicao(int posicao) {
        return values()[posicao].getDescricao();
    }
}
