package br.edu.ifsp.financasdroid.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Transaction;

@Dao
public interface TransactionDao {

    @Insert
    void save(final Transaction transaction);

    @Delete
    void delete(final Transaction transaction);

    @Update
    void update(final Transaction transaction);

    @Query("SELECT * FROM _transaction")
    List<Transaction> findAll();

    @Query("SELECT * FROM _transaction WHERE category_id IN (SELECT id FROM _category WHERE transaction_type = :transactionType)")
    List<Transaction> findByType(final String transactionType);

    @Query("SELECT * FROM _transaction WHERE id = :id LIMIT 1")
    Transaction findById(final Long id);
}
