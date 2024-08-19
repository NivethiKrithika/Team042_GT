package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompareToCurrentJobActivity extends AppCompatActivity {
    TextView txtTitleCurrent, txtCompanyCurrent, txtLocationCurrent, txtYsCurrent,txtYbCurrent, txtRetBenefitCurrent, txtRelStipendCurrent,txtRsuaCurrent;
    TextView txtTitleOffer, txtCompanyOffer, txtLocationOffer, txtYsOffer,txtYbOffer, txtRetBenefitOffer, txtRelStipendOffer,txtRsuaOffer;
    Button btnBack, btnMainMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_with_current_job);
        txtTitleCurrent = findViewById(R.id.titleCurrentJob);
        txtCompanyCurrent = findViewById(R.id.companyCurrentJob);
        txtLocationCurrent = findViewById(R.id.locationCurrentJob);
        txtYsCurrent = findViewById(R.id.ysCurrentJob);
        txtYbCurrent = findViewById(R.id.ybCurrentJob);
        txtRetBenefitCurrent =findViewById(R.id.retBenCurrentJob);
        txtRelStipendCurrent = findViewById(R.id.relStipendCurrentJob);
        txtRsuaCurrent = findViewById(R.id.rsuaCurrentJob);
        txtTitleOffer = findViewById(R.id.titleOffer);
        txtCompanyOffer = findViewById(R.id.companyOffer);
        txtLocationOffer = findViewById(R.id.locationOffer);
        txtYsOffer = findViewById(R.id.ysOffer);
        txtYbOffer = findViewById(R.id.ybOffer);
        txtRetBenefitOffer =findViewById(R.id.retBenOffer);
        txtRelStipendOffer = findViewById(R.id.relStipendOffer);
        txtRsuaOffer = findViewById(R.id.rsuaOffer);
        btnBack = findViewById(R.id.buttonBack);
        btnMainMenu = findViewById(R.id.buttonMainMenu);
        JobDatabase jobDatabase =new JobDatabase(CompareToCurrentJobActivity.this);
        Comparer comparer = new Comparer(jobDatabase.getComparisonSettings());
        Job currentJob = jobDatabase.getCurrentJob();
        Job recentOffer = jobDatabase.getMostRecentJobOfferEntered();
        txtTitleCurrent.setText(currentJob.getTitle());
        txtCompanyCurrent.setText(currentJob.getCompany());
        txtLocationCurrent.setText(currentJob.getLocation().getCity()+",\n"+currentJob.getLocation().getState());
        txtYsCurrent.setText(String.valueOf(comparer.computeAdjustedYearlySalary(currentJob)));
        txtYbCurrent.setText(String.valueOf(comparer.computeAdjustedYearlyBonus(currentJob)));
        txtRelStipendCurrent.setText(String.valueOf(currentJob.getRelocationStipend()));
        txtRetBenefitCurrent.setText(String.valueOf(currentJob.getRetirementBenefits()));
        txtRsuaCurrent.setText(String.valueOf(currentJob.getRestrictedStockUnitAward()));
        txtTitleOffer.setText(recentOffer.getTitle());
        txtCompanyOffer.setText(recentOffer.getCompany());
        txtLocationOffer.setText(recentOffer.getLocation().getCity()+",\n"+recentOffer.getLocation().getState());
        txtYsOffer.setText(String.valueOf(comparer.computeAdjustedYearlySalary(recentOffer)));
        txtYbOffer.setText(String.valueOf(comparer.computeAdjustedYearlyBonus(recentOffer)));
        txtRelStipendOffer.setText(String.valueOf(recentOffer.getRelocationStipend()));
        txtRetBenefitOffer.setText(String.valueOf(recentOffer.getRetirementBenefits()));
        txtRsuaOffer.setText(String.valueOf(recentOffer.getRestrictedStockUnitAward()));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(CompareToCurrentJobActivity.this,NewOfferActivity.class);
                startActivity(backIntent);
            }
        });
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenuIntent = new Intent(CompareToCurrentJobActivity.this,MainActivity.class);
                startActivity(mainMenuIntent);
            }
        });

    }
}