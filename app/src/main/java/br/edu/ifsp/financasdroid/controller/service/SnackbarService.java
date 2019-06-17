package br.edu.ifsp.financasdroid.controller.service;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import br.edu.ifsp.financasdroid.R;

public class SnackbarService {

    private View view;

    public SnackbarService(View view) {
        this.view = view;
    }

    public void make(String text, SnackType type) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        Resources resources = view.getContext().getResources();
        snackbarView.setBackgroundColor(resources.getColor(type.getColor()));

        if (SnackType.WARNING.equals(type)) {
            TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            tv.setTextColor(Color.BLACK);
        }
        snackbar.show();
    }

    public enum SnackType {
        INFO(R.color.green800),
        WARNING(R.color.ambar600),
        ERROR(R.color.crimson900);

        private int colorResId;

        SnackType(int colorResId) {
            this.colorResId = colorResId;
        }

        public int getColor() {
            return colorResId;
        }
    }
}
