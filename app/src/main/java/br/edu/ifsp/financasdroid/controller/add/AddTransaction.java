package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifsp.financasdroid.R;

import android.os.Bundle;

public class AddTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        String type = getIntent().getStringExtra("type");

        if(type.equals("D")){
            getSupportActionBar().setTitle(getString(R.string.add_debit_transaction));
        } else if(type.equals("C")){
            getSupportActionBar().setTitle(getString(R.string.add_credit_transaction));
        }
    }
}
