package projetoencomendadeplacas.Utils.Enums;

public enum TipoModalEnum {
    WARNING("WARNING"),
    INFORMATION("INFORMATION"),
    CONFIRMATION("CONFIRMATION"),
    ERROR("ERROR");

    private final String descricao;

    TipoModalEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}