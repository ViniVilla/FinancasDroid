package br.edu.ifsp.financasdroid.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.ifsp.financasdroid.model.TransactionType;


@Dao
public interface TransactionDao {

    @Insert
    void save(final TransactionDao transaction);

    @Delete
    void delete(final TransactionDao transaction);

    @Update
    void update(final TransactionDao transaction);

    @Query("SELECT * FROM _transaction")
    List<TransactionDao> findAll();

    @Query("SELECT * FROM _category INNER JOIN _transaction ON")
    List<TransactionDao> findByType(final TransactionType);

    @Query("SELECT * FROM _transaction WHERE id = :id LIMIT 1")
    TransactionDao findById(final Long id);
}
