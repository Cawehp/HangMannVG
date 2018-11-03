package com.example.cawehpassereh.hangmannvg;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.cawehpassereh.HangMannVG.R;
import com.example.cawehpassereh.hangmannvg.ui.main.MainFragment;
import com.example.cawehpassereh.hangmannvg.ui.main.PlayFragment;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static com.example.cawehpassereh.hangmannvg.ui.main.PlayFragment.isWin;
import static com.example.cawehpassereh.hangmannvg.ui.main.PlayFragment.parts;
import static com.example.cawehpassereh.hangmannvg.ui.main.PlayFragment.randomWord;

public class FinalScreenFragment extends Fragment {

    private Button again;
    private Button home;

    public FinalScreenFragment() {

    }

    public static void setParameters(int parts, String randomWord, boolean isWin) {
        PlayFragment.parts = parts;
        PlayFragment.randomWord = randomWord;
        PlayFragment.isWin = isWin;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_screen, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView winOrLose = getView().findViewById(R.id.winOrLose);
        TextView showWord = getView().findViewById(R.id.showWord2);
        TextView tries = getView().findViewById(R.id.triesLeft2);

        String win = getString(R.string.YouWon);
        String lose = getString(R.string.YouLost);
        String showWordStr = getString(R.string.word) + randomWord;
        String triesLeft = getString(R.string.TriesLeft) + (6 - parts);

        if (isWin) {
            winOrLose.setTextColor(GREEN);
            winOrLose.setText(win);
            showWord.setText(showWordStr);
            tries.setText(triesLeft);

        } else if (!isWin) {
            winOrLose.setTextColor(RED);
            winOrLose.setText(lose);
            showWord.setText(showWordStr);
            tries.setText("");

        }

        again = getView().findViewById(R.id.imageButtonAgain);
        home = getView().findViewById(R.id.imageButtonHome);

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new PlayFragment());
                fragmentTransaction.commit();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new MainFragment());
                fragmentTransaction.commit();
            }
        });


    }

}


