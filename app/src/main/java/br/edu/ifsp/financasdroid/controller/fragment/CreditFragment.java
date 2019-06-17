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
import br.edu.ifsp.financasdroid.controller.adapter.TransactionAdapter;
import br.edu.ifsp.financasdroid.controller.add.AddTransaction;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Transaction;
import br.edu.ifsp.financasdroid.model.service.TransactionService;

public class CreditFragment extends Fragment {

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private TransactionService transactionService;
    private List<Transaction> transactions = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this::fabClick);

        this.transactionService = new TransactionService(getContext());

        updateRecyclerView(view);

        return view;
    }

    private void fabClick(View v){
        Intent intent = new Intent(getContext(), AddTransaction.class);
        intent.putExtra("type", "C");
        startActivityForResult(intent, 1);
    }

    private void updateRecyclerView(View view) {
        transactions = transactionService.findByType(TransactionType.CREDIT.getType());
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new TransactionAdapter(this, transactions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateRecyclerView(recyclerView);
    }

}
