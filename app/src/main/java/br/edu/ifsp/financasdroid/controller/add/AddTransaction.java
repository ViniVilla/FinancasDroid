package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;

import android.os.Bundle;

public class AddTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        String type = getIntent().getStringExtra("type");

        if(type.equals(TransactionType.DEBIT)){
            getSupportActionBar().setTitle(getString(R.string.add_debit_transaction));
        } else if(type.equals(TransactionType.CREDIT)){
            getSupportActionBar().setTitle(getString(R.string.add_credit_transaction));
        }
    }
}
