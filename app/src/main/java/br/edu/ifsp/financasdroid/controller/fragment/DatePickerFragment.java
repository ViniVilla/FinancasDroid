package br.edu.ifsp.financasdroid.controller.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import br.edu.ifsp.financasdroid.R;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private String formattedDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, (DatePickerDialog.OnDateSetListener) getActivity(),
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        StringBuilder sb = new StringBuilder(dayOfMonth);
        sb.append("/");
        sb.append(month);
        sb.append("/");
        sb.append(year);
        this.formattedDate = sb.toString();
    }

    public String getFormattedDate() {
        return formattedDate;
    }
}
