package br.edu.ifsp.financasdroid.controller.add;

import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.service.CategoryService;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddCategory extends AppCompatActivity {

    private EditText editText;

    private RadioGroup radioGroup;

    private Button button;

    private String type;

    private String description;

    private CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        getSupportActionBar().setTitle(getString(R.string.add_category));

        editText = findViewById(R.id.editText);
        radioGroup = findViewById(R.id.radioType);
        button = findViewById(R.id.button);

        categoryService = new CategoryService(this);

        button.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
        type = radioButton.getText().toString();
        description = editText.getText().toString();
        if (!"".equals(description) && type != null) {
            Category category = new Category();
            category.setDescription(description);
            category.setTransactionType(view.getResources().getString(R.string.credit).equals(type)? "C" : "D");
            categoryService.save(category);
        }

    }
}
