package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.fragment.DatePickerFragment;
import br.edu.ifsp.financasdroid.model.TransactionType;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

public class AddTransaction extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView date;
    DatePickerFragment datePicker = new DatePickerFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        String type = getIntent().getStringExtra("type");

        if(type.equals(TransactionType.DEBIT.getType())){
            getSupportActionBar().setTitle(getString(R.string.add_debit_transaction));
        } else if(type.equals(TransactionType.CREDIT.getType())){
            getSupportActionBar().setTitle(getString(R.string.add_credit_transaction));
        }

        ImageView dateView = findViewById(R.id.imageDate);
        dateView.setOnClickListener(this::showDateDialog);
        date = findViewById(R.id.date);
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
        date.setText(String.format("%s/%s/%s", dayS, monthS, year));
    }
}
