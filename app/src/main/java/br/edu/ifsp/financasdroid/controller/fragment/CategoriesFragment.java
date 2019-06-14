package br.edu.ifsp.financasdroid.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.adapter.CategoryAdapter;
import br.edu.ifsp.financasdroid.controller.add.AddCategory;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.service.CategoryService;

public class CategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<Category> categories = new ArrayList<>();

    private CategoryService categoryService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this::fabClick);

//        Category c1 = new Category("Descrição", TransactionType.CREDIT.getType());
//        Category c2 = new Category("Descrição", TransactionType.DEBIT.getType());
//
//        categories.add(c1);
//        categories.add(c2);
        categoryService = new CategoryService(view.getContext());
        categories = categoryService.findAll();

        updateRecyclerView(view);

        return view;
    }

    private void updateRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new CategoryAdapter(this, categories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void fabClick(View v) {
        Intent intent = new Intent(getContext(), AddCategory.class);
        startActivityForResult(intent, 1);
    }

}
