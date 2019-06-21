package br.edu.ifsp.financasdroid.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Transaction;
import br.edu.ifsp.financasdroid.model.service.TransactionService;

public class BudgetFragment extends Fragment {

    private TransactionService transactionService;

    private TextView debitText;

    private TextView creditText;

    private TextView budgetText;

    private DatePickerFragment datePicker = new DatePickerFragment();

    private ImageView dateFrom;

    private ImageView dateTo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        transactionService = new TransactionService(view.getContext());

        debitText = view.findViewById(R.id.debit);

        creditText = view.findViewById(R.id.credit);

        budgetText = view.findViewById(R.id.difference);

//        dateFrom = view.findViewById(R.id.dateFrom);
//        dateFrom.setOnClickListener(this::showDateDialog);
//        dateTo = view.findViewById(R.id.dateTo);
//        dateTo.setOnClickListener(this::showDateDialog);
        calculate();
        return view;
    }

    private void showDateDialog(View view) {
        datePicker.show(getFragmentManager(), "DatePicker");
    }

    private void calculate() {
        List<Transaction> credits = transactionService.findByType(TransactionType.CREDIT.getType());
        List<Transaction> debits = transactionService.findByType(TransactionType.DEBIT.getType());
        double creditAmount = 0;
        double debitAmount = 0;
        for (Transaction t : credits) {
            creditAmount += t.getValue();
        }
        for (Transaction t : debits) {
            debitAmount += t.getValue();
        }
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("PT", "BR"));

        debitText.setText(format.format(debitAmount));
        creditText.setText(format.format(creditAmount));
        budgetText.setText(format.format(creditAmount - debitAmount));
    }

}
