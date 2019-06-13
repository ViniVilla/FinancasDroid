package br.edu.ifsp.financasdroid.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import br.edu.ifsp.financasdroid.model.TransactionType;

@Entity(tableName = "_category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private TransactionType transactionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
