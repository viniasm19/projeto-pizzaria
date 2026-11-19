package br.senac.sp.pizzaria.model;

public enum Tamanho {
    BROTO("Broto",4),
    MEDIA("Média" , 6),
    GRANDE("Grande", 8);

    private int pedacos;
    private String descricao;

    public String toString(){
        return this.descricao + ", "+this.pedacos + " pedaços";
    }

    Tamanho(String descricao, int qtd){
        this.descricao = descricao;
        this.pedacos = qtd;
    }

}
