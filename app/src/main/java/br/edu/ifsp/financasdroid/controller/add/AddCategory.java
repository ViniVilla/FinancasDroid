package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifsp.financasdroid.R;

import android.os.Bundle;

public class AddCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        getSupportActionBar().setTitle(getString(R.string.add_category));
    }
}
