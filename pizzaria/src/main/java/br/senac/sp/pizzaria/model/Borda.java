package br.senac.sp.pizzaria.model;

public enum Borda {
            NORMAL("Normal"),
            RECH_CATUPIRY("Recheada com Catupiry"),
            RECH_CHEDDAR("Recheada com Cheddar"),
            RECH_CHOCOLATE("Recheada com chocolate");
    private String descricao;

    @Override
    public String toString(){
        return this.descricao;
    }

    Borda(String descricao){
        this.descricao = descricao;
    }
}
