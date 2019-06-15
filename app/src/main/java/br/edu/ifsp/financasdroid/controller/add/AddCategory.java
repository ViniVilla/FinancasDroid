package br.edu.ifsp.financasdroid.controller.add;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.service.CategoryService;

public class AddCategory extends AppCompatActivity {

    private EditText editText;

    private RadioGroup radioGroup;

    private RadioButton radioButton;

    private CategoryService categoryService;

    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        getSupportActionBar().setTitle(getString(R.string.add_category));

        editText = findViewById(R.id.editText);
        radioGroup = findViewById(R.id.radioType);
        Button button = findViewById(R.id.button);

        categoryService = new CategoryService(this);

        category = new Category();

        Long id = getIntent().getLongExtra("category_id", 0);
        if (id != 0) {
            category = categoryService.findById(id);
            editText.setText(category.getDescription());
            String type = category.getTransactionType();
            int radioId = R.id.radioDebit;
            if (TransactionType.CREDIT.getType().equals(type)) {
                radioId = R.id.radioCredit;
            }
            radioButton = findViewById(radioId);
            radioButton.setChecked(true);
        }

        button.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        String type = radioButton.getText().toString();
        String description = editText.getText().toString();
        if (!"".equals(description) && !"".equals(type)) {
            String oldDescription = category.getDescription();
            category.setDescription(description);
            category.setTransactionType(view.getResources().getString(R.string.credit).equals(type) ? "C" : "D");
            if (categoryService.isSaved(oldDescription)) {
                categoryService.update(category);
            } else {
                categoryService.save(category);
            }
        }
        finish();
    }
}
