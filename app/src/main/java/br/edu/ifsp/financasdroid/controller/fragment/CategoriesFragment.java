package br.edu.ifsp.financasdroid.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.add.AddCategory;

public class CategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this::fabClick);
        return view;
    }

    private void fabClick(View v){
        Intent intent = new Intent(getContext(), AddCategory.class);
        startActivityForResult(intent, 1);
    }

}
