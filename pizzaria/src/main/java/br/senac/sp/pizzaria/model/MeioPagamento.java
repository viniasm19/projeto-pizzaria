package br.senac.sp.pizzaria.model;

public enum MeioPagamento {
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    DINHEIRO("Dinheiro"),
    PIX("PIX"),
    VOUCHER("Voucher");
    String desc;

    MeioPagamento(String desc){
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
