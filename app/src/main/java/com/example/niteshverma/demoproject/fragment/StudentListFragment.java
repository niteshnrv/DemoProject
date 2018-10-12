package com.example.niteshverma.demoproject.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.niteshverma.demoproject.DemoApplication;
import com.example.niteshverma.demoproject.R;
import com.example.niteshverma.demoproject.adapters.RandomStudentAdapter;
import com.example.niteshverma.demoproject.models.RandomStudent;
import com.example.niteshverma.demoproject.network.RandomStudentApi;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StudentListFragment extends BaseFragment{

    @BindView(R.id.recyclerView) protected RecyclerView recyclerView;

    private RandomStudentAdapter mAdapter;

    private ProgressDialog progressDialog;

    private RandomStudentApi randomStudentApi;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_student_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        randomStudentApi = DemoApplication.get().getComponent().getRandomStudentService();
        initViews();
        populateStudents();
    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RandomStudentAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.refresh)
    public void onRefreshButtonClicked(){
        populateStudents();
    }

    private void populateStudents(){
        showProgressDialog();
        randomStudentApi.getRandomStudent(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<RandomStudent>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // No-Opp
                    }

                    @Override
                    public void onNext(Response<RandomStudent> response) {
                        if(response.isSuccessful()){
                            mAdapter.setItems(response.body().getResults());
                            mAdapter.notifyDataSetChanged();
                            dismissProgressDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // No-Opp
                    }

                    @Override
                    public void onComplete() {
                        // No-Opp
                    }
                });


    }

    private void showProgressDialog(){
        dismissProgressDialog();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissProgressDialog(){
        if(progressDialog != null ){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }
}
