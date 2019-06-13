package br.edu.ifsp.financasdroid.model;

public enum TransactionType {

    CREDIT("credit"),

    DEBIT("debit");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
