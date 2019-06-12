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
import br.edu.ifsp.financasdroid.controller.add.AddTransaction;
import br.edu.ifsp.financasdroid.R;

public class CreditFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this::fabClick);
        return view;
    }

    private void fabClick(View v){
        Intent intent = new Intent(getContext(), AddTransaction.class);
        intent.putExtra("type", "C");
        startActivityForResult(intent, 1);
    }

}
