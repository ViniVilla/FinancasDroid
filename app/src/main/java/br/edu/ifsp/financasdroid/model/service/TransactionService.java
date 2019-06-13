package br.edu.ifsp.financasdroid.model.service;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import br.edu.ifsp.financasdroid.model.AppDatabase;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Transaction;

public class TransactionService {

    private AppDatabase db;

    private Context context;

    private CategoryService categoryService;

    public TransactionService(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, "finances")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        categoryService = new CategoryService(context);
    }

    public void save(final Transaction transaction) {
        db.transactionDao().save(transaction);
    }

    public void delete(final Transaction transaction) {
        db.transactionDao().delete(transaction);
    }

    public void update(final Transaction transaction) {
        db.transactionDao().delete(transaction);
    }

    public List<Transaction> findAll() {
        final List<Transaction> transactions = db.transactionDao().findAll();
        for (Transaction t: transactions) {
            t.setCategory(categoryService.findById(t.getCategoryId()));
        }
        return transactions;
    }

    public List<Transaction> findByType(final TransactionType transactionType) {
        final List<Transaction> transactions = db.transactionDao().findByType(transactionType);
        for (Transaction t: transactions) {
            t.setCategory(categoryService.findById(t.getCategoryId()));
        }
        return transactions;
    }

    public Transaction findById(final Long id) {
        Transaction transaction = db.transactionDao().findById(id);
        transaction.setCategory(categoryService.findById(transaction.getCategoryId()));
        return transaction;
    }

}
