package br.edu.ifsp.financasdroid.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.ifsp.financasdroid.model.entity.Category;

@Dao
public interface CategoryDao {

    @Insert
    void save(final Category category);

    @Delete
    void delete(final Category category);

    @Update
    void update(final Category category);

    @Query("SELECT * FROM  _category")
    List<Category> findAll();

    @Query("SELECT * FROM _category WHERE transaction_type = :transactionType")
    List<Category> findByCategory(final String transactionType);

    @Query("SELECT * FROM _category WHERE id = :id LIMIT 1")
    Category findById(final Long id);

    @Query("SELECT COUNT(*) FROM _category WHERE description = :description")
    int isSaved(final String description);
}
