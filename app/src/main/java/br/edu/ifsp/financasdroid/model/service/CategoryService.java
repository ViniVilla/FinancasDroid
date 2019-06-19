package br.edu.ifsp.financasdroid.model.service;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import br.edu.ifsp.financasdroid.model.AppDatabase;
import br.edu.ifsp.financasdroid.model.entity.Category;

public class CategoryService {

    private AppDatabase db;

    private Context context;

    public CategoryService(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, "finances")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public void save(final Category category) {
        db.categoryDao().save(category);
    }

    public void delete(final Category category) {
        db.categoryDao().delete(category);
    }

    public void update(final Category category) {
        db.categoryDao().update(category);
    }

    public List<Category> findAll() {
        return db.categoryDao().findAll();
    }

    public List<Category> findByCategory(final String transactionType) {
        return db.categoryDao().findByCategory(transactionType);
    }

    public Category findById(final Long id) {
        return db.categoryDao().findById(id);
    }

    public Boolean isSaved(final String description) {
        return db.categoryDao().isSaved(description) == 1;
    }

    public Boolean contais(final Category category) {
        return findAll().contains(category);
    }

}
