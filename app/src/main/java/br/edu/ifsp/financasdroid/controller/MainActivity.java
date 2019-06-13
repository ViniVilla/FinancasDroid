package br.edu.ifsp.financasdroid.controller;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import br.edu.ifsp.financasdroid.R;
import br.edu.ifsp.financasdroid.controller.fragment.BudgetFragment;
import br.edu.ifsp.financasdroid.controller.fragment.CategoriesFragment;
import br.edu.ifsp.financasdroid.controller.fragment.ChartFragment;
import br.edu.ifsp.financasdroid.controller.fragment.CreditFragment;
import br.edu.ifsp.financasdroid.controller.fragment.DebitFragment;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getCheckedItem());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_categories) {
            CategoriesFragment categoriesFragment = new CategoriesFragment();
            showFragment(categoriesFragment);
        } else if (id == R.id.nav_credit) {
            CreditFragment creditFragment = new CreditFragment();
            showFragment(creditFragment);
        } else if (id == R.id.nav_debit) {
            DebitFragment debitFragment = new DebitFragment();
            showFragment(debitFragment);
        } else if (id == R.id.nav_budget) {
            BudgetFragment budgetFragment = new BudgetFragment();
            showFragment(budgetFragment);
        } else if (id == R.id.nav_chart){
            ChartFragment chartFragment = new ChartFragment();
            showFragment(chartFragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame , fragment);
        fragmentTransaction.commit();
    }
}
