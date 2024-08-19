package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;


public class MainActivity extends AppCompatActivity {



    Button btnCurrentJob,btnNewOffer,btnComparisonSettings,btnCompareJobs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCurrentJob=(Button) findViewById(R.id.currentjob);
        btnNewOffer=(Button) findViewById(R.id.newoffer);
        btnComparisonSettings=(Button) findViewById(R.id.comparisonsettings);
        btnCompareJobs=(Button) findViewById(R.id.comparejobs);
        JobDatabase jobDatabase = new JobDatabase(MainActivity.this);
        long totalNumberOfJobs = jobDatabase.getTotalJobsCount();
        if(totalNumberOfJobs < 2) {
            btnCompareJobs.setEnabled(false);
        }
        else {
            btnCompareJobs.setEnabled(true);
        }

        btnCurrentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentJobIntent = new Intent(MainActivity.this,CurrentJobActivity.class);
                startActivity(currentJobIntent);
            }
        });
        btnNewOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newJobIntent = new Intent(MainActivity.this,NewOfferActivity.class);
                startActivity(newJobIntent);
            }
        });
        btnComparisonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
        btnCompareJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compareJobsIntent = new Intent(MainActivity.this,CompareJobsActivity.class);
                startActivity(compareJobsIntent);
            }
        });


    }

}