package com.example.cawehpassereh.hangmannvg;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import com.example.cawehpassereh.HangMannVG.R;
import com.example.cawehpassereh.hangmannvg.ui.main.MainFragment;
import com.example.cawehpassereh.hangmannvg.ui.main.PlayFragment;


public class MainActivity extends AppCompatActivity {

    //private boolean isSwitch;
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Switch theme = findViewById(R.id.theme);
        background = findViewById(R.id.background);
        //isSwitch = theme.isChecked();


        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setImageResource(R.drawable.halloween);

            }
        });




        Toolbar myToolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();

        FloatingActionButton play = findViewById(R.id.floatingPlay);
        FloatingActionButton back = findViewById(R.id.floatingBack);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new PlayFragment());
                fragmentTransaction.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new MainFragment());
                fragmentTransaction.commit();
            }
        });

    }


}
