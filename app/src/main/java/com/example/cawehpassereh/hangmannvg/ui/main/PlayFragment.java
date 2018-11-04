package com.example.cawehpassereh.hangmannvg.ui.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cawehpassereh.HangMannVG.R;
import com.example.cawehpassereh.hangmannvg.FinalScreenFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static android.view.View.VISIBLE;

public class PlayFragment extends Fragment {

    Random rand = new Random();

    private ArrayList<Character> faultyGuess;
    private ArrayList<ImageView> man;
    private boolean isUsed = false;
    public static boolean isWin = false;
    private EditText editText;
    private TextView usedLetters;
    private TextView textView;
    private String playerInput;
    public static String randomWord;
    private String reveal;
    private String usedLetters2;
    private char[] spacer;
    public static int parts = 0;
    private ImageButton check;


    public PlayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_play, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView head = getView().findViewById(R.id.head);
        ImageView torso = getView().findViewById(R.id.torso);
        ImageView leftArm = getView().findViewById(R.id.lArm);
        ImageView rightArm = getView().findViewById(R.id.rArm);
        ImageView leftLeg = getView().findViewById(R.id.lLeg);
        ImageView rightLeg = getView().findViewById(R.id.rLeg);

        man = new ArrayList<>();

        man.addAll(Arrays.asList(head, torso, leftArm, rightArm, leftLeg, rightLeg));

        usedLetters = getView().findViewById(R.id.usedLetters);

        usedLetters2 = getString(R.string.usedLetters);

        usedLetters.setText(usedLetters2);

        Resources res = getResources();

        String[] wordList = res.getStringArray(R.array.stringArray);
        randomWord = wordList[rand.nextInt(wordList.length)];

        textView = getView().findViewById(R.id.word);
        textView.setText(randomWord);

        editText = getView().findViewById(R.id.input);
        playerInput = editText.toString();
        faultyGuess = new ArrayList<>();

        isWin = false;
        parts = 0;
        reveal = "";

        editText = getView().findViewById(R.id.input);
        editText.setText(" ");
        editText.hasFocus();
        spacer = randomWord.toCharArray();

        for (int j = 0; j < randomWord.length(); j++) {

            Character space = '_';
            spacer[j] = space;

        }

        String hidden = new String(spacer);
        textView.setText(hidden);

        check = getView().findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                isUsed = true;
                playerInput = editText.getText().toString().toLowerCase();

                boolean checkSame = faultyGuess.contains(playerInput.charAt(0));

                if (checkSame) {
                    Toast.makeText(getActivity(), "You've already entered this letter!",
                            Toast.LENGTH_SHORT).show();

                    return;

                }

                editText.setText(" ");
                editText.hasFocus();


                if (playerInput.equals(" ")) {
                    Toast.makeText(getActivity(), "Enter a letter between a-z (swe a-ö)",
                            Toast.LENGTH_SHORT).show();

                    return;

                }

                gameLoop();

                checkWinLose();


            }
        });


    }

    private void checkWinLose() {

        boolean checkWin = randomWord.equals(reveal);
        boolean checkLose = parts == 6;

        if (checkLose) {

            isWin = false;

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new FinalScreenFragment());
            FinalScreenFragment.setParameters(parts, randomWord, isWin);
            fragmentTransaction.commit();


        } else if (checkWin) {

            isWin = true;

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new FinalScreenFragment());
            fragmentTransaction.commit();

        }

    }

    private void gameLoop() {

        char playerLetter = playerInput.charAt(0);

        usedLetters2 += (" " + playerInput.charAt(0) + ",");

        usedLetters.setText(usedLetters2);


        for (int j = 0; j < randomWord.length(); j++) {

            String checkLetter = "" + randomWord.charAt(j);

            if (playerInput.equals(checkLetter) && !playerInput.equals(" ")) {

                spacer[j] = playerLetter;
                reveal = new String(spacer);
                textView.setText(reveal);
                isUsed = false;
                faultyGuess.add(playerLetter);
                editText.hasFocus();

            }

        }

        if (isUsed && !playerInput.equals(" ")) {

            faultyGuess.add(playerLetter);

            man.get(parts).setVisibility(VISIBLE);
            parts++;
            editText.hasFocus();

        }

    }

}
