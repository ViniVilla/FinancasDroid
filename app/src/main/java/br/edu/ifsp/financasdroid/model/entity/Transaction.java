package br.edu.ifsp.financasdroid.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "_transaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String date;

    @ColumnInfo
    private Double value;

    @ColumnInfo(name = "category_id")
    @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
    private Long categoryId;

    @Ignore
    private Category category;

    public Transaction(){

    }

    public Transaction(String description, String date, Double value, Category category) {
        this.description = description;
        this.date = date;
        this.value = value;
        this.category = category;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
