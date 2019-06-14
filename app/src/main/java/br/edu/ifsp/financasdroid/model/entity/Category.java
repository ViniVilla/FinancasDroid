package br.edu.ifsp.financasdroid.model.entity;

import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "_category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo
    private String description;

    @ColumnInfo(name = "transaction_type")
    private String transactionType;

    public Category(){}

    public Category(String description, String transactionType) {
        this.description = description;
        this.transactionType = transactionType;
    }

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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                description.equals(category.description) &&
                transactionType.equals(category.transactionType);
    }
}
