package br.edu.ifsp.financasdroid.controller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.add.AddTransaction;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private List<Transaction> transactions;
    private Fragment parentFragment;
    private LayoutInflater inflater;
    private  TransactionType type;

    public TransactionAdapter(Fragment parentFragment, List<Transaction> transactions, TransactionType type){
        this.transactions = transactions;
        this.inflater = LayoutInflater.from(parentFragment.getContext());
        this.parentFragment = parentFragment;
        this.type = type;

    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_transaction, viewGroup, false);
        return new TransactionAdapter.TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TextView category = holder.itemView.findViewById(R.id.category);
        TextView value = holder.itemView.findViewById(R.id.value);
        TextView date = holder.itemView.findViewById(R.id.date);
        TextView description = holder.itemView.findViewById(R.id.description);
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("PT","BR"));

        category.setText(transactions.get(position).getCategory().getDescription());
        value.setText(format.format(transactions.get(position).getValue()));

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        date.setText(formato.format(transactions.get(position).getDate()));
        description.setText(transactions.get(position).getDescription());

        holder.itemView.setOnLongClickListener(view -> {
            Transaction transaction = transactions.get(position);
            Intent intent = new Intent(parentFragment.getContext(), AddTransaction.class);
            intent.putExtra("type", type.getType());
            intent.putExtra("transaction_id", transaction.getId());
            parentFragment.startActivityForResult(intent, 1);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
