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

public class DebitFragment extends Fragment {

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private List<Transaction> transactions = new ArrayList<>();
    private TransactionService transactionService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debit, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this::fabClick);

        transactionService = new TransactionService(getContext());
        recyclerView = view.findViewById(R.id.recyclerview);

        updateRecyclerView();
        return view;
    }

    private void fabClick(View v){
        Intent intent = new Intent(getContext(), AddTransaction.class);
        intent.putExtra("type", "D");
        startActivityForResult(intent, 1);
    }

    public void removeTransaction(int position) {
        transactionService.delete(transactions.get(position));
        updateRecyclerView();
    }

    private void updateRecyclerView() {
        transactions = transactionService.findByType(TransactionType.DEBIT.getType());
        adapter = new TransactionAdapter(this, transactions, TransactionType.DEBIT);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateRecyclerView();
    }

}
