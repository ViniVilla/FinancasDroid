package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.fragment.DatePickerFragment;
import br.edu.ifsp.financasdroid.controller.service.SnackbarService;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.entity.Transaction;
import br.edu.ifsp.financasdroid.model.exceptions.ValidationException;
import br.edu.ifsp.financasdroid.model.service.CategoryService;
import br.edu.ifsp.financasdroid.model.service.TransactionService;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AddTransaction extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ViewHolder viewHolder;
    private CategoryService categoryService;
    private TransactionService transactionService;
    private SnackbarService snackbarService;
    private DatePickerFragment datePicker = new DatePickerFragment();
    private String type;
    private Transaction transaction = new Transaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        viewHolder = new ViewHolder();
        categoryService = new CategoryService(this);
        transactionService = new TransactionService(this);
        snackbarService = new SnackbarService(viewHolder.add);

        type = getIntent().getStringExtra("type");

        if(type.equals(TransactionType.DEBIT.getType())){
            getSupportActionBar().setTitle(getString(R.string.add_debit_transaction));
        } else if(type.equals(TransactionType.CREDIT.getType())){
            getSupportActionBar().setTitle(getString(R.string.add_credit_transaction));
        }

        viewHolder.imageView.setOnClickListener(this::showDateDialog);
        viewHolder.add.setOnClickListener(this::addTransaction);
        populateSpinner();

        Long id = getIntent().getLongExtra("transaction_id", 0);
        if (id != 0) {
            transaction = transactionService.findById(id);
            viewHolder.description.setText(transaction.getDescription());
            viewHolder.value.setText(transaction.getValue().toString());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            viewHolder.date.setText(format.format(transaction.getDate()));

        }
    }

    public void showDateDialog(View view){
        datePicker.show(getSupportFragmentManager(), "DatePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dayS;
        String monthS;

        if(dayOfMonth <= 9){
            dayS = "0" + dayOfMonth;
        } else{
            dayS = String.valueOf(dayOfMonth);
        }

        if(month <= 9+1){
            monthS = "0" + (month+1);
        } else{
            monthS = String.valueOf(month+1);
        }
        viewHolder.date.setText(String.format("%s/%s/%s", dayS, monthS, year));
    }

    private void addTransaction(final View view) {
        try {
            transaction = readInputData();
            if (transaction.getId() != null) {
                transactionService.update(transaction);
                transaction = transactionService.findById(transaction.getId());
            } else {
                transactionService.save(transaction);
            }
            setResult(RESULT_OK);
            finish();
        } catch (ValidationException e) {
            snackbarService.make(e.getMessage(), SnackbarService.SnackType.WARNING);
        } catch (Exception e) {
            snackbarService.make(getResources().getString(R.string.save_transaction_error_message),
                    SnackbarService.SnackType.ERROR);
        }
    }

    private Transaction readInputData() {
        final Category category = (Category) viewHolder.spinner.getSelectedItem();
        final String date = viewHolder.date.getText().toString();
        final String stringValue = viewHolder.value.getText().toString();
        final String description = viewHolder.description.getText().toString();

        if (date.isEmpty() || date.equals(getResources().getString(R.string.select_date))) {
            throw new ValidationException(getResources().getString(R.string.save_transaction_invalid_date));
        }
        if (stringValue.isEmpty()) {
            throw new ValidationException(getResources().getString(R.string.save_transaction_invalid_value));
        }

        final Double value = Double.parseDouble(stringValue);
        transaction.setCategory(category);

        String[] d = date.split("/");

        transaction.setDate(new Date(Integer.parseInt(d[2]), Integer.parseInt(d[1]), Integer.parseInt(d[0])));

        transaction.setValue(value);
        transaction.setCategoryId(category.getId());
        transaction.setDescription(description);
        return transaction;
    }

    private void populateSpinner() {
        final List<Category> categories = categoryService.findByCategory(type);
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        adapter.setDropDownViewResource(R.layout.category_spinner_item);
        viewHolder.spinner.setAdapter(adapter);
    }

    private class ViewHolder {
        private TextView date;
        private Spinner spinner;
        private ImageView imageView;
        private Button add;
        private EditText value;
        private EditText description;

        public ViewHolder() {
            date = findViewById(R.id.date);
            spinner = findViewById(R.id.spinner);
            imageView = findViewById(R.id.imageDate);
            add = findViewById(R.id.add);
            value = findViewById(R.id.value);
            description = findViewById(R.id.description);
        }
    }
}
