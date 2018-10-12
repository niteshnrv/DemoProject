package com.example.niteshverma.demoproject.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.niteshverma.demoproject.DemoApplication;
import com.example.niteshverma.demoproject.R;
import com.example.niteshverma.demoproject.adapters.RandomStudentAdapter;
import com.example.niteshverma.demoproject.fragment.StudentListFragment;
import com.example.niteshverma.demoproject.models.RandomStudent;
import com.example.niteshverma.demoproject.network.RandomStudentApi;
import com.squareup.picasso.Picasso;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openFragment(new StudentListFragment());
        }
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.contentFrame, fragment, Fragment.class.getSimpleName())
                .commit();
    }
}
