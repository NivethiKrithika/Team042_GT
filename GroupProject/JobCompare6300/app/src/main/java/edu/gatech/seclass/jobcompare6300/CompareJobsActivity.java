package edu.gatech.seclass.jobcompare6300;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompareJobsActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    CompareJobsAdapter adapter;
    private static Button compareButton;
    Button cancel;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_layout);
        recycler_view = findViewById(R.id.recycler_view);
        compareButton = findViewById(R.id.compare2Jobs);
        cancel = findViewById(R.id.jobCompareCancel);
        compareButton.setEnabled(false);
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compareJobsIntent = new Intent(CompareJobsActivity.this, Compare2JobsActivity.class);
                startActivity(compareJobsIntent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(CompareJobsActivity.this, MainActivity.class);
                startActivity(cancelIntent);
            }
        });
        setRecyclerView();
    }

    public void setRecyclerView(){
        JobDatabase jobDatabase = new JobDatabase(CompareJobsActivity.this);
        Comparer comparer = new Comparer(jobDatabase.getComparisonSettings());
        ArrayList<Job> jobList = jobDatabase.getAllJobs();
        HashMap rankedJobMap = comparer.rankJobs(jobList);
        Set<Map.Entry<Job, Double>> rankedJobSet = rankedJobMap.entrySet();
        Comparator<Map.Entry<Job,Double>> valueComparator=  new Comparator <Map.Entry<Job,Double>> () {
            public int compare(Map.Entry<Job,Double> job1, Map.Entry<Job,Double> job2){
                if(job1.getValue() - job2.getValue() < 0) return 1;
                else if (job1.getValue() - job2.getValue() > 0) return -1;
                else return 0;
            }
        };
        List<Map.Entry<Job, Double>> rankedJobList = new ArrayList<Map.Entry<Job, Double>>(rankedJobSet);
        Collections.sort(rankedJobList, valueComparator);
        LinkedHashMap<Job, Double> sortedByScore= new LinkedHashMap<Job, Double>(rankedJobList.size());
        for(Map.Entry<Job, Double> entry : rankedJobList)
        {
            sortedByScore.put(entry.getKey(), entry.getValue());
        }
        List<Job> rankedJobs = new ArrayList<>(sortedByScore.keySet());
        List<Double> rankedScore = new ArrayList<>();
        for(Job job:rankedJobs)
        {
            rankedScore.add(sortedByScore.get(job));
        }

        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompareJobsAdapter(this,rankedJobs, rankedScore);
        recycler_view.setAdapter(adapter);
    }

    public static void enableButton() {
        compareButton.setEnabled(true);
    }
    public static void disableButton() {
        compareButton.setEnabled(false);
    }
}
