package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.List;

public class Compare2JobsActivity extends AppCompatActivity {

        Button backButton;
        Button mainMenu;
        TextView titleJob1, titleJob2, companyJob1, companyJob2, locationJob1, locationJob2,
        aysJob1, aysJob2, aybJob1, aybJob2, retirementJob1, retirementJob2, relocationJob1,
        relocationJob2, stockJob1, stockJob2;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.compare2jobs);
                mainMenu = findViewById(R.id.compare2MainMenu);
                backButton = findViewById(R.id.compareBackButton);
                titleJob1 = findViewById(R.id.titleJob1);
                titleJob2 = findViewById(R.id.titleJob2);
                companyJob1 = findViewById(R.id.companyJob1);
                companyJob2 = findViewById(R.id.companyJob2);
                locationJob1 = findViewById(R.id.locationJob1);
                locationJob2 = findViewById(R.id.locationJob2);
                aysJob1 = findViewById(R.id.aysJob1);
                aysJob2 = findViewById(R.id.aysJob2);
                aybJob1 = findViewById(R.id.aybJob1);
                aybJob2 = findViewById(R.id.aybJob2);
                retirementJob1 = findViewById(R.id.retirementJob1);
                retirementJob2 = findViewById(R.id.retirementJob2);
                relocationJob1 = findViewById(R.id.relocationJob1);
                relocationJob2 = findViewById(R.id.relocationJob2);
                stockJob1 = findViewById(R.id.stockJob1);
                stockJob2 = findViewById(R.id.stockJob2);
                //get jobs with adjusted salaries and bonuses
                List<Job> twoJobs = Comparer
                        .compareTwoJobs(CompareJobsAdapter.getTwoJobs().get(0),
                                CompareJobsAdapter.getTwoJobs().get(1));
                if (!twoJobs.isEmpty()){
                        titleJob1.setText(twoJobs.get(0).getTitle());
                        titleJob2.setText(twoJobs.get(1).getTitle());
                        companyJob1.setText(twoJobs.get(0).getCompany());
                        companyJob2.setText(twoJobs.get(1).getCompany());
                        locationJob1.setText(twoJobs.get(0).getLocation().getCompleteLocation());
                        locationJob2.setText(twoJobs.get(1).getLocation().getCompleteLocation());
                        aysJob1.setText(fmt.format(twoJobs.get(0).getYearlySalary()));
                        aysJob2.setText(fmt.format(twoJobs.get(1).getYearlySalary()));
                        aybJob1.setText(fmt.format(twoJobs.get(0).getYearlyBonus()));
                        aybJob2.setText(fmt.format(twoJobs.get(1).getYearlyBonus()));
                        retirementJob1.setText(fmt.format(twoJobs.get(0).getRetirementBenefits()));
                        retirementJob2.setText(fmt.format(twoJobs.get(1).getRetirementBenefits()));
                        relocationJob1.setText(fmt.format(twoJobs.get(0).getRelocationStipend()));
                        relocationJob2.setText(fmt.format(twoJobs.get(1).getRelocationStipend()));
                        stockJob1.setText(fmt.format(twoJobs.get(0).getRestrictedStockUnitAward()));
                        stockJob2.setText(fmt.format(twoJobs.get(1).getRestrictedStockUnitAward()));

                }
                backButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent cancelIntent = new Intent(Compare2JobsActivity.this, CompareJobsActivity.class);
                                startActivity(cancelIntent);
                        }
                });
                mainMenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent cancelIntent = new Intent(Compare2JobsActivity.this, MainActivity.class);
                                startActivity(cancelIntent);
                        }
                });
        }
}
