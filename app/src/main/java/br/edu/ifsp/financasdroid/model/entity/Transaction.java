package br.edu.ifsp.financasdroid.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "_transaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private Date date;

    @ColumnInfo
    private Double value;

    @ColumnInfo(name = "category_id")
    @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
    private Long categoryId;

    @Ignore
    private Category category;

    public Transaction() {}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
