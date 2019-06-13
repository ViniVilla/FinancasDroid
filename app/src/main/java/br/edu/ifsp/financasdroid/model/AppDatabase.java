package br.edu.ifsp.financasdroid.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.edu.ifsp.financasdroid.model.dao.CategoryDao;
import br.edu.ifsp.financasdroid.model.dao.TransactionDao;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.entity.Transaction;

@Database(entities = {Category.class, Transaction.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryDao categoryDao();

    public abstract TransactionDao transactionDao();
}
