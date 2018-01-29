package com.example.giangdam.cleanarchitecturesearchingsample.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giangdam.cleanarchitecturesearchingsample.R;
import com.example.giangdam.cleanarchitecturesearchingsample.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>{

    private List<UserModel> userModelList;

    @Inject
    SearchListAdapter(){
        userModelList = new ArrayList<>();
    }

    @Override
    public SearchListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false
        );
        return new SearchListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchListViewHolder holder, int position) {
        final UserModel userModel = userModelList.get(position);
        holder.textUserName.setText(userModel.getUserName());
        holder.textEmail.setText(userModel.getEmail());
    }

    public void updateDataSet(List<UserModel> newDataSet) {
        if(newDataSet != null) {
            //userModelList.clear();

            userModelList = newDataSet;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class SearchListViewHolder extends RecyclerView.ViewHolder{
        private TextView textUserName;
        private TextView textEmail;

        public SearchListViewHolder(View itemView) {
            super(itemView);

            textUserName = itemView.findViewById(R.id.textViewName);
            textEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}
