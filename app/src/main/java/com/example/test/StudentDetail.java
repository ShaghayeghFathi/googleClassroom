package com.example.test;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StudentDetail extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    HomeworkAdapter adapter;
    ArrayList<Assignment> assignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        recyclerView = findViewById(R.id.homeworks);
        layoutManager = new LinearLayoutManager(this);
        try {
            assignments = new FetchAssignments("S").execute().get();
        } catch (Exception e) {
            assignments = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                assignments.add(new Assignment("title " + i, "description " + i, "status " + i));
            }
        }

        adapter = new HomeworkAdapter(assignments, getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private class Assignment {
        String title;
        String description;
        String status;

        public Assignment(String title, String description, String status) {
            this.title = title;
            this.description = description;
            this.status = status;
        }
    }

    private class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkHolder> {
        ArrayList<Assignment> assignments;
        Context ctx;

        public HomeworkAdapter(ArrayList<Assignment> assignments, Context c) {
            this.assignments = assignments;
            ctx = c;
        }

        @NonNull
        @Override
        public HomeworkHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_homework_item, viewGroup, false);
            HomeworkHolder hwh = new HomeworkHolder(v);
            return hwh;
        }

        @Override
        public void onBindViewHolder(@NonNull HomeworkHolder homeworkHolder, int i) {
            homeworkHolder.title.setText(assignments.get(i).title);
            homeworkHolder.description.setText(assignments.get(i).description);
            homeworkHolder.status.setText(assignments.get(i).status);
        }

        @Override
        public int getItemCount() {
            return assignments.size();
        }

        private class HomeworkHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView description;
            TextView status;

            public HomeworkHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.hw_title);
                description = itemView.findViewById(R.id.hw_desc);
                status = itemView.findViewById(R.id.hw_status);
            }
        }
    }

    private class FetchAssignments extends AsyncTask<Void, Void, ArrayList<Assignment>> {
        String id = "";

        public FetchAssignments(String id) {
            this.id = id;
        }

        @Override
        protected ArrayList<Assignment> doInBackground(Void... voids) {
            ArrayList<Assignment> assignments = new ArrayList<>();
            try {
                SocketThread.dos.writeUTF("getAssignments");
                SocketThread.dos.writeUTF(id);
                int size = SocketThread.dis.readInt();
                for (int i = 0; i < size; i++) {
                    String title = SocketThread.dis.readUTF();
                    String desc = SocketThread.dis.readUTF();
                    String status = SocketThread.dis.readUTF();
                    assignments.add(new Assignment(title, desc, status));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return assignments;
        }
    }
}
