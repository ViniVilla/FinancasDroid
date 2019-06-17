package br.edu.ifsp.financasdroid.controller.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.service.SnackbarService;

public class DatePickerFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        return new DatePickerDialog(getActivity(), R.style.DialogTheme, (DatePickerDialog.OnDateSetListener) getActivity(),
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
    }
}
