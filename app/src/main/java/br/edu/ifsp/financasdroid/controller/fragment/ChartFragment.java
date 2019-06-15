package br.edu.ifsp.financasdroid.controller.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.model.TransactionType;
import br.edu.ifsp.financasdroid.model.entity.Category;
import br.edu.ifsp.financasdroid.model.entity.Transaction;
import br.edu.ifsp.financasdroid.model.service.TransactionService;

public class ChartFragment extends Fragment {

    private PieChart pieChart;

    private TransactionService transactionService;

    private int[] COLORS = new int[] {
            R.color.green900,
            R.color.green700,
            R.color.green500,
            R.color.lime500,
            R.color.lime700,
            R.color.green800,
            R.color.green600,
            R.color.green400,
            R.color.lime600,
            R.color.limeA700
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        pieChart = (PieChart) view.findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(R.color.background);

        transactionService = new TransactionService(getContext());
        Map<String, List<Transaction>> transactions = transactionService
                .findByTypeSortedByCategory(TransactionType.DEBIT);

//        ArrayList<PieEntry> yValues = new ArrayList<>();
//
//        yValues.add(new PieEntry(34f, "Brasil"));
//        yValues.add(new PieEntry(23f, "EUA"));
//        yValues.add(new PieEntry(14f, "Japão"));
//        yValues.add(new PieEntry(35, "Alemanha"));
//        yValues.add(new PieEntry(40, "China"));
//        yValues.add(new PieEntry(23, "Rússia"));


        List<PieEntry> pieData = getPieData(transactions);
        PieDataSet dataSet = new PieDataSet(pieData, "Débitos");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(COLORS, getContext());

        pieChart.animateY(1000, Easing.EaseInCirc);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);

        pieChart.getLegend().setEnabled(false);

        pieChart.setData(data);


        return view;
    }

    private List<PieEntry> getPieData(final Map<String, List<Transaction>> transactions) {
        final List<PieEntry> pieEntries = new ArrayList<>(transactions.keySet().size());
        for (String category : transactions.keySet()) {
            float value = calcTransactionsValue(transactions.get(category));
            pieEntries.add(new PieEntry(value, category));
        }
        return pieEntries;

    }

    private float calcTransactionsValue(final List<Transaction> transactions) {
        float total = 0;
        for (Transaction t: transactions) {
            if (t != null && t.getValue() != null) {
                total += t.getValue();
            }
        }
        return total;
    }
}
