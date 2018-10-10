package com.example.githubrepos.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.githubrepos.R;
import com.example.githubrepos.models.Repo;

public class RepoViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView description;
    private TextView stars;
    private TextView language;
    private TextView forks;

    private Repo mRepo;

    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.repo_name);
        description = itemView.findViewById(R.id.repo_description);
        stars = itemView.findViewById(R.id.repo_stars);
        language = itemView.findViewById(R.id.repo_language);
        forks = itemView.findViewById(R.id.repo_forks);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepo.getUrl()));
                v.getContext().startActivity(intent);
            }
        });
    }

    void bindData(Repo repo) {
        this.mRepo = repo;

        if (TextUtils.isEmpty(mRepo.getFullName())) {
            name.setText(mRepo.getFullName());
        }

        if (!TextUtils.isEmpty(mRepo.getDescription())) {
            description.setText(mRepo.getDescription());
        }



    }

}
