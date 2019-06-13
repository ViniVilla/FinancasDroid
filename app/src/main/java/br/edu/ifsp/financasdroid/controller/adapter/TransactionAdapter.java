package br.edu.ifsp.financasdroid.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.entity.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{

    private List<Transaction> transactions;
    private Fragment parentFragment;
    private LayoutInflater inflater;

    public TransactionAdapter(Fragment parentFragment, List<Transaction> transactions){
        this.transactions = transactions;
        this.parentFragment = parentFragment;
        this.inflater = LayoutInflater.from(parentFragment.getContext());
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
        date.setText(transactions.get(position).getDate());
        description.setText(transactions.get(position).getDescription());
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
