package com.example.kw1_display_text;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kw1_display_text.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_info){
            Toast.makeText(this, "Build 1.1, VladCopyright@", Toast.LENGTH_SHORT)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private String action = "";
    private Boolean checkAction(Integer current, TextView result){
        if(action == "+")
        {
            Integer resInt = current + Integer.parseInt(result.getText().toString());
            result.setText(resInt.toString());
            action = "";
            return true;
        }
        return false;
    }
    public void onClickButtonCalc(View view)
    {
        int id = view.getId();
        Button current = this.findViewById(id);
        TextView result = this.findViewById(R.id.text_result);
        switch (id){
            case R.id.btn_plus:
                action = "+";
                break;
            case R.id.btn_div:
                action = "/";
                break;
            case R.id.btn_clear:
                result.setText("");
                break;
            case R.id.btn_one:
                if(checkAction(1, result))
                    break;
                result.setText(result.getText() + "1");
                break;
            case R.id.btn_two:
                if(checkAction(2, result))
                    break;
                result.setText(result.getText() + "2");
                break;
            case R.id.btn_three:
                if(checkAction(3, result))
                    break;
                result.setText(result.getText() + "3");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }
}