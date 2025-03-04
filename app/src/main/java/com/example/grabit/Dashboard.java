package com.example.grabit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {


    public void goToActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();  // Finish login activity to prevent going back to it
    }

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Setup BottomNavigationView with NavController
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    public void logout(View view) {
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        // Save login state in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("logged", false);
        editor.apply();

        goToActivity();
    }
}
