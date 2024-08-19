package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CompareJobsAdapter extends RecyclerView.Adapter<CompareJobsAdapter.ViewHolder> {

    Context context;
    List<Job> jobList;
    List<Double> compare_score;
    Button selected;
    private static List<Job> twoJobs = new ArrayList<>();
    int count = 0;
    public CompareJobsAdapter(Context context, List<Job> jobList, List<Double> compare_score) {
        this.context = context;
        this.jobList = jobList;
        this.compare_score = compare_score;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompareJobsAdapter.ViewHolder holder, int position) {
        clearTwoJobs();
        if (jobList != null && jobList.size() > 0) {
            Job job = jobList.get(position);
            holder.rank_tv.setText(String.valueOf(position+1));
            holder.company_tv.setText(job.getCompany());
            holder.title_tv.setText(job.getTitle());
            holder.score_tv.setText(String.valueOf(compare_score.get(position)));
            holder.current_tv.setText(String.valueOf(job.isCurrentJob()));
            holder.itemView.setBackgroundColor(job.isSelected() ? Color.YELLOW : Color.TRANSPARENT);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if(count < 2) {
                        job.setSelected(!job.isSelected());
                        if(job.isSelected()){
                            count++;
                            twoJobs.add(job);
                        }else{
                            count--;
                            twoJobs.remove(job);
                        }

                    }
                    else if (count == 2 ) {
                      if (job.isSelected()) {
                          job.setSelected(false);
                          count--;
                          twoJobs.remove(job);
                      }
                    }
                    else{
                        job.setSelected(false);
                        //count--;
                    }
                    holder.itemView.setBackgroundColor(job.isSelected() ? Color.YELLOW : Color.TRANSPARENT);
                    if(count == 2){
                        CompareJobsActivity.enableButton();
                    }
                    else {
                        CompareJobsActivity.disableButton();
                    }
                }
            });
        }
    }

    public static void clearTwoJobs() {
        twoJobs.clear();
    }
    public static List<Job> getTwoJobs(){
        return twoJobs;
    }
    @Override
    public int getItemCount() {

        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank_tv, company_tv, title_tv, score_tv, current_tv;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            rank_tv = itemView.findViewById(R.id.rank_tv);
            company_tv = itemView.findViewById(R.id.company_tv);
            title_tv = itemView.findViewById(R.id.title_tv);
            score_tv = itemView.findViewById(R.id.score_tv);
            current_tv = itemView.findViewById(R.id.current_tv);
        }
    }
}
