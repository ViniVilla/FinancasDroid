package br.edu.ifsp.financasdroid.controller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.add.AddCategory;
import br.edu.ifsp.financasdroid.controller.fragment.CategoriesFragment;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private CategoriesFragment parentFragment;
    private LayoutInflater inflater;

    public CategoryAdapter(Fragment parentFragment, List<Category> categories) {
        this.categories = categories;
        this.parentFragment = (CategoriesFragment) parentFragment;
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
        Button remove = holder.itemView.findViewById(R.id.remove);

        description.setText(categories.get(position).getDescription());
        String typeS;
        if (categories.get(position).getTransactionType().equals(TransactionType.CREDIT.getType())) {
            typeS = parentFragment.getString(R.string.credit);
        } else {
            typeS = parentFragment.getString(R.string.debit);
        }
        type.setText(parentFragment.getString(R.string.type) + typeS);

        remove.setOnClickListener(view -> parentFragment.removeCategory(position));

        holder.itemView.setOnLongClickListener(view -> {
            Category category = categories.get(position);
            Intent intent = new Intent(parentFragment.getContext(), AddCategory.class);
            intent.putExtra("category_id", category.getId());
            parentFragment.startActivityForResult(intent, 1);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
