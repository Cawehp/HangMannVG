package com.example.cawehpassereh.hangmannvg.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.cawehpassereh.hangmannvg.AboutFragment;
import com.squareup.picasso.Picasso;
import com.example.cawehpassereh.HangMannVG.R;


public class MainFragment extends Fragment {

    private ImageView imageView2;
    private MainViewModel mViewModel;
    private ImageButton play;
    private ImageButton about;

    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        imageView2 = getView().findViewById(R.id.image2);
        play = getView().findViewById(R.id.imageButtonP);
        about = getView().findViewById(R.id.imageButtonS);

        Picasso.with(getActivity()).load("https://blog.flamingtext.com/blog/2018/10/17/flamingtext_com_1539809478_505183599.png").into(imageView2);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new PlayFragment());
                fragmentTransaction.commit();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new AboutFragment());
                fragmentTransaction.commit();
            }
        });
    }


}
