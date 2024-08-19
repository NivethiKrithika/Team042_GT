package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

public class NewOfferActivity extends AppCompatActivity {
    EditText newOfferJobTitle, newOfferCompany, newOfferCity, newOfferState, newOfferCoL, newOfferSalary, newOfferBonus, newOfferBenefits, newOfferStipend, newOfferStock;
    Button btnCancel, btnSave, btnCompare, btnEnterNewOffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_offer);

        // determine if offer was succesfully saved
        boolean savedOffer = false;

        // initialize the edit boxes
        newOfferJobTitle = findViewById(R.id.newOfferJobTitle);
        newOfferCompany = findViewById(R.id.newOfferCompany);
        newOfferCity = findViewById(R.id.newOfferCity);
        newOfferState = findViewById(R.id.newOfferState);
        newOfferCoL = findViewById(R.id.newOfferCoL);
        newOfferSalary = findViewById(R.id.newOfferSalary);
        newOfferBonus = findViewById(R.id.newOfferBonus);
        newOfferBenefits = findViewById(R.id.newOfferBenefits);
        newOfferStipend = findViewById(R.id.newOfferStipend);
        newOfferStock = findViewById(R.id.newOfferStock);


        btnSave = findViewById(R.id.buttonSaveNewOffer);
        btnCompare = findViewById(R.id.buttonCompareWithCurrentJob);
        btnSave = findViewById(R.id.buttonSaveNewOffer);
        btnEnterNewOffer = findViewById(R.id.buttonEnterNewOffer);
        btnCancel =  findViewById(R.id.buttonCancelNewOffer);

        btnCompare.setEnabled(false);
        //btnCompare.setTooltipText("This button is enabled only if the user has entered current job details and saved the job offer details on this page.");
        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent compareJobsActivity = new Intent(NewOfferActivity.this, CompareToCurrentJobActivity.class);
                //compareJobsActivity.putExtra("comaparing_new_offer", true);
                startActivity(compareJobsActivity);
            }
        });

        // save the new job offer into DB
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the DB
                JobDatabase jobDatabase = new JobDatabase(NewOfferActivity.this);

                String jobTitle = newOfferJobTitle.getText().toString();
                String company = newOfferCompany.getText().toString();
                String city = newOfferCity.getText().toString();
                String state = newOfferState.getText().toString();
                String costOfLiving = newOfferCoL.getText().toString();
                String salary = newOfferSalary.getText().toString();
                String bonus = newOfferBonus.getText().toString();
                String benefits = newOfferBenefits.getText().toString();
                String stipend = newOfferStipend.getText().toString();
                String stock = newOfferStock.getText().toString();

                // do validation here
                // if
                boolean isValid = validateUserInput(company, jobTitle, state, city, costOfLiving, salary, bonus, benefits, stipend, stock, false);
                if (!isValid) {
                    Toast.makeText(NewOfferActivity.this,"Missing or invalid field",Toast.LENGTH_LONG).show();
                }
                // else
                else{
                    Location location = new Location(city,state);

                    Job newJob = new Job (jobTitle, company, location, Integer.parseInt(costOfLiving), new BigDecimal(salary), new BigDecimal(bonus), Integer.parseInt(benefits), new BigDecimal(stipend), new BigDecimal(stock), false);
                    boolean retStatus;
                    retStatus = jobDatabase.addJob(newJob);
                    if (retStatus)
                    {
                        if(jobDatabase.getCurrentJob()!= null){
                            btnCompare.setEnabled(true);
                            btnSave.setEnabled(false);
                            //btnCompare.setTooltipText("");
                    }
                    }
                    else
                    {
                        Toast.makeText(NewOfferActivity.this, "Details cannot be saved in DB due to DB error", Toast.LENGTH_LONG);
                    }
                }

            }
        });

        btnEnterNewOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newOfferActivityIntent = new Intent(NewOfferActivity.this, NewOfferActivity.class);
                startActivity(newOfferActivityIntent);
            }
        });
        // go back to main menu
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnBackIntent = new Intent(NewOfferActivity.this,MainActivity.class);
                startActivity(returnBackIntent);
            }
        });

    }

    public boolean validateUserInput(String company, String title, String state, String city, String costOfLivingIndex,
                                     String yearlySalary,String yearlyBonus, String retirementBenefit,
                                     String relocationStipend, String rsua, boolean forTesting )
    {
        boolean validInput = true;
        if(company.equals(""))
        {
            if(!forTesting)
            {
                newOfferCompany.setError(" Company cannot be empty");
            }
            validInput = false;
        }
        if(title.equals(""))
        {
            if(!forTesting)
            {
                newOfferJobTitle.setError(" Title cannot be empty");
            }
            validInput = false;
        }
        if(state.equals(""))
        {
            if(!forTesting)
            {
                newOfferState.setError(" State cannot be empty");
            }
            validInput = false;
        }
        if(city.equals(""))
        {
            if(!forTesting)
            {
                newOfferCity.setError(" City cannot be empty");
            }
            validInput = false;
        }
        if(costOfLivingIndex.equals(""))
        {
            if(!forTesting)
            {
                newOfferCoL.setError(" Cost of living index cannot be empty");
            }
            validInput = false;
        }
        else
        {
            if(Integer.parseInt(costOfLivingIndex) == 0)
            {
                if(!forTesting)
                {
                    newOfferCoL.setError("Cost of living index cannot be zero");
                }
                validInput = false;
            }
        }

        if(yearlySalary.equals(""))
        {
            if(!forTesting)
            {
                newOfferSalary.setError(" Yearly Salary cannot be empty");
            }
            validInput = false;
        }
        if(yearlyBonus.equals(""))
        {
            if(!forTesting)
            {
                newOfferBonus.setError(" Yearly bonus cannot be empty");
            }
            validInput = false;
        }
        if(retirementBenefit.equals(""))
        {
            if(!forTesting)
            {
                newOfferBenefits.setError(" Retirement Benefit cannot be empty");
            }
            validInput = false;
        }
        else
        {
            try {
                int retirementBenefitInt = Integer.parseInt(retirementBenefit);
                if ((retirementBenefitInt < 0) || (retirementBenefitInt > 100)) {
                    if (!forTesting) {
                        newOfferBenefits.setError("Retirement benefit should be within 0-100 range");
                    }
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                if(!forTesting) {
                    newOfferBenefits.setError("Retirement Benefit must be a numeric integer");
                }
                validInput = false;
            }
        }
        if(relocationStipend.equals(""))
        {
            if(!forTesting)
            {
                newOfferStipend.setError(" Relocation Stipend cannot be empty");
            }
            validInput = false;
        }
        if(rsua.equals(""))
        {
            if(!forTesting)
            {
                newOfferStock.setError(" Restricted stock unit award cannot be empty");
            }
            validInput = false;
        }
        /*else
        {
            try {
                BigDecimal relocationStipendDecimal = BigDecimal.valueOf(Double.parseDouble(relocationStipend));
                BigDecimal zero = new BigDecimal("0");
                int res = relocationStipendDecimal.compareTo(zero);
                if (res == -1) {
                    if (!forTesting) {
                        editTxtObjForRelocationStipend.setError("Relocation Stipend cannot be negative value");
                    }
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                if(!forTesting) {
                    editTxtObjForRelocationStipend.setError("Relocation Stipend must be a number");
                }
                validInput = false;
            }
        }*/
        return validInput;
    }
}