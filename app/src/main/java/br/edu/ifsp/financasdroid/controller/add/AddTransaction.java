package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.fragment.DatePickerFragment;
import br.edu.ifsp.financasdroid.model.AppDatabase;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.dao.CategoryDao;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.service.CategoryService;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddTransaction extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ViewHolder viewHolder;
    private CategoryService categoryService;
    private DatePickerFragment datePicker = new DatePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        this.viewHolder = new ViewHolder();
        categoryService = new CategoryService(this);

        String type = getIntent().getStringExtra("type");

        if(type.equals(TransactionType.DEBIT.getType())){
            getSupportActionBar().setTitle(getString(R.string.add_debit_transaction));
        } else if(type.equals(TransactionType.CREDIT.getType())){
            getSupportActionBar().setTitle(getString(R.string.add_credit_transaction));
        }

        viewHolder.imageView.setOnClickListener(this::showDateDialog);
        populateSpinner(type);
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
            monthS = "0" + month+1;
        } else{
            monthS = String.valueOf(month+1);
        }
        viewHolder.date.setText(String.format("%s/%s/%s", dayS, monthS, year));
    }

    private void populateSpinner(final String type) {
        final List<Category> categories = categoryService.findByCategory(type);
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        adapter.setDropDownViewResource(R.layout.category_spinner_item);
        viewHolder.spinner.setAdapter(adapter);
    }

    private class ViewHolder {
        private TextView date;
        private Spinner spinner;
        private ImageView imageView;

        public ViewHolder() {
            date = findViewById(R.id.date);
            spinner = findViewById(R.id.spinner);
            imageView = findViewById(R.id.imageDate);
        }
    }
}
