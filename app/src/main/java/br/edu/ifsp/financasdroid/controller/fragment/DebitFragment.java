package br.edu.ifsp.financasdroid.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.financasdroid.controller.adapter.TransactionAdapter;
import br.edu.ifsp.financasdroid.controller.add.AddTransaction;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.entity.Transaction;

public class DebitFragment extends Fragment {

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private List<Transaction> transactions = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debit, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this::fabClick);

        Transaction t1 = new Transaction("Descrição", "01/01/2019", 10.50, new Category("Categoria", TransactionType.DEBIT));
        Transaction t2 = new Transaction("Descrição", "02/01/2019", 10.50, new Category("Categoria", TransactionType.DEBIT));
        transactions.add(t1);
        transactions.add(t2);

        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new TransactionAdapter(this, transactions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    private void fabClick(View v){
        Intent intent = new Intent(getContext(), AddTransaction.class);
        intent.putExtra("type", "D");
        startActivityForResult(intent, 1);
    }
}
