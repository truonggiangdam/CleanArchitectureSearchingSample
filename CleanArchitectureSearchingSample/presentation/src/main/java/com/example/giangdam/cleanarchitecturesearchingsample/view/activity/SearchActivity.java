package com.example.giangdam.cleanarchitecturesearchingsample.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giangdam.cleanarchitecturesearchingsample.R;
import com.example.giangdam.cleanarchitecturesearchingsample.model.UserModel;
import com.example.giangdam.cleanarchitecturesearchingsample.presenter.SearchUserPresenter;
import com.example.giangdam.cleanarchitecturesearchingsample.view.SearchUserView;
import com.example.giangdam.cleanarchitecturesearchingsample.view.adapter.SearchListAdapter;

import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity implements SearchUserView {

    private RecyclerView recyclerView;
    private ProgressBar progressLoading;
    private TextView textEmpty;
    private EditText editKeyWork;

    @Inject
    SearchUserPresenter searchUserPresenter;

    @Inject
    SearchListAdapter searchListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeInjector();
        searchUserPresenter.setView(this);

        initializeView();

    }

    private void initializeView() {
        recyclerView = findViewById(R.id.recyclerView);
        textEmpty = findViewById(R.id.textEmpty);
        progressLoading = findViewById(R.id.progressLoading);
        editKeyWork = findViewById(R.id.editKeyWork);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchListAdapter);
    }

    private void initializeInjector() {
        getActivityComponent().inject(this);
    }

    @Override
    public void updateUI(List<UserModel> users) {
        recyclerView.setVisibility(View.VISIBLE);
        textEmpty.setVisibility(View.GONE);
        searchListAdapter.updateDataSet(users);
    }

    @Override
    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        textEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchUserPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        searchUserPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchUserPresenter.destroy();
        recyclerView.setAdapter(null);
    }

    public void onButtonSearchClick(View view) {
        String keyWork = editKeyWork.getText().toString();
        if(!TextUtils.isEmpty(keyWork)) {
            searchUserPresenter.search(keyWork.trim());
        }
    }
}
