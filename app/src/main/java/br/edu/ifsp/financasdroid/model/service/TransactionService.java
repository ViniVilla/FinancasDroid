package br.edu.ifsp.financasdroid.model.service;

import android.content.Context;
import android.util.Pair;

import androidx.room.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsp.financasdroid.model.AppDatabase;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;
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

    public List<Transaction> findByType(final String transactionType) {
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

    public Map<String, List<Transaction>> findByTypeSortedByCategory(final TransactionType transactionType) {
        final List<Transaction> transactions = findByType(transactionType.getType());
        final Map<String, List<Transaction>> sorted = new HashMap<>();

        for (Transaction t: transactions) {
            if (sorted.containsKey(t.getCategory().getDescription())) {
                sorted.get(t.getCategory().getDescription()).add(t);
            } else {
                List<Transaction> newEntry = new ArrayList<>();
                newEntry.add(t);
                sorted.put(t.getCategory().getDescription(), newEntry);
            }
        }
        return sorted;
    }

}
