package br.edu.ifsp.financasdroid.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private Fragment parentFragment;
    private LayoutInflater inflater;

    public CategoryAdapter(Fragment parentFragment, List<Category> categories){
        this.categories = categories;
        this.parentFragment = parentFragment;
        this.inflater = LayoutInflater.from(parentFragment.getContext());
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_category, viewGroup, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        TextView description = holder.itemView.findViewById(R.id.description);
        TextView type = holder.itemView.findViewById(R.id.type);

        description.setText(categories.get(position).getDescription());
        String typeS;
        if(categories.get(position).getTransactionType() == TransactionType.CREDIT){
            typeS = parentFragment.getString(R.string.credit);
        } else{
            typeS = parentFragment.getString(R.string.debit);
        }
        type.setText(parentFragment.getString(R.string.type) + typeS);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
