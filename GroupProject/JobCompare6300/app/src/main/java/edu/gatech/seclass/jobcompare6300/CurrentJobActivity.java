package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class CurrentJobActivity extends AppCompatActivity {

    EditText editTxtObjForCompany,editTxtObjForTitle,editTxtObjForState,editTxtObjForCity,editTxtObjForColIndex, editTxtObjForYearlyBonus, editTxtObjForYearlySalary,editTxtObjForRetirementBenefit,editTxtObjForRelocationStipend,editTxtObjForRsua;
    Button btnSave, btnCancel;
    public CurrentJobActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job);
        editTxtObjForCompany = findViewById(R.id.editTextCompany);
        editTxtObjForTitle =  findViewById(R.id.editTxtJobTitle);
        editTxtObjForColIndex = findViewById(R.id.editTxtColIndex);
        editTxtObjForRetirementBenefit = findViewById(R.id.editTxtRetBenefit);
        editTxtObjForYearlyBonus = findViewById(R.id.editTxtYb);
        editTxtObjForYearlySalary =  findViewById(R.id.editTxtYs);
        editTxtObjForCity =  findViewById(R.id.editTxtCity);
        editTxtObjForState = findViewById(R.id.editTxtState);
        editTxtObjForRsua =  findViewById(R.id.editTxtRsua);
        editTxtObjForRelocationStipend =  findViewById(R.id.editTxtRelocStipend);
        btnSave = findViewById(R.id.buttonSave);
        btnCancel =  findViewById(R.id.buttonCancel);
        JobDatabase jobDatabase = new JobDatabase(CurrentJobActivity.this);
        Job currentJob = jobDatabase.getCurrentJob();
        if(currentJob !=null)
        {
            editTxtObjForCompany.setText(String.valueOf(currentJob.getCompany()));
            editTxtObjForTitle.setText(String.valueOf(currentJob.getTitle()));
            editTxtObjForState.setText(String.valueOf(currentJob.getLocation().getState()));
            editTxtObjForCity.setText(String.valueOf(currentJob.getLocation().getCity()));
            editTxtObjForColIndex.setText(String.valueOf(currentJob.getCostOfLiving()));
            editTxtObjForYearlySalary.setText(String.valueOf(currentJob.getYearlySalary()));
            editTxtObjForYearlyBonus.setText(String.valueOf(currentJob.getYearlyBonus()));
            editTxtObjForRetirementBenefit.setText(String.valueOf(currentJob.getRetirementBenefits()));
            editTxtObjForRelocationStipend.setText(String.valueOf(currentJob.getRelocationStipend()));
            editTxtObjForRsua.setText(String.valueOf(currentJob.getRestrictedStockUnitAward()));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company = editTxtObjForCompany.getText().toString();
                String title = editTxtObjForTitle.getText().toString();
                String state = editTxtObjForState.getText().toString();
                String city = editTxtObjForCity.getText().toString();
                String colIndex = editTxtObjForColIndex.getText().toString();
                String yearlySalary = editTxtObjForYearlySalary.getText().toString();
                String yearlyBonus = editTxtObjForYearlyBonus.getText().toString();
                String retirementBenefit = editTxtObjForRetirementBenefit.getText().toString();
                String relocationStipend = editTxtObjForRelocationStipend.getText().toString();
                String rsua = editTxtObjForRsua.getText().toString();

                boolean result = validateUserInput(company,title,state,city,colIndex,yearlySalary,yearlyBonus,retirementBenefit,relocationStipend,rsua,false);
                if(!result)
                {
                    Toast.makeText(CurrentJobActivity.this,"Missing or invalid field",Toast.LENGTH_LONG).show();
                }
                else
                {
                    JobDatabase jobDatabase = new JobDatabase(CurrentJobActivity.this);
                    Location location = new Location(city,state);
                    Job job = new Job(title,company,location,Integer.parseInt(colIndex),new BigDecimal(yearlySalary), new BigDecimal(yearlyBonus), Integer.parseInt(retirementBenefit),
                            new BigDecimal(relocationStipend), new BigDecimal(rsua),true);

                    boolean retStatus ;
                    if (jobDatabase.getCurrentJob() !=null)
                    {
                        retStatus = jobDatabase.updateCurrentJob(job);
                    }
                    else {
                        retStatus = jobDatabase.addJob(job);
                    }
                    if(retStatus) {
                        Intent mainActivityIntent = new Intent(CurrentJobActivity.this, MainActivity.class);
                        startActivity(mainActivityIntent);
                    }
                    else
                    {
                        Toast.makeText(CurrentJobActivity.this,"Details cannot be saved in DB due to DB error",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnBackIntent = new Intent(CurrentJobActivity.this,MainActivity.class);
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
                editTxtObjForCompany.setError(" Company field cannot be empty");
            }
            validInput = false;
        }
        if(title.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForTitle.setError(" Title field cannot be empty");
            }
            validInput = false;
        }
        if(state.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForState.setError(" State field cannot be empty");
            }
            validInput = false;
        }
        if(city.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForCity.setError(" City field cannot be empty");
            }
            validInput = false;
        }
        if(costOfLivingIndex.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForColIndex.setError(" Cost of living index cannot be empty");
            }
            validInput = false;
        }
        else
        {
            if(Integer.parseInt(costOfLivingIndex) == 0)
            {
                if(!forTesting)
                {
                    editTxtObjForColIndex.setError("Cost of living index cannot be zero");
                }
                validInput = false;
            }
        }
        /*else
        {
            try {
                int colIndexInt = Integer.parseInt(costOfLivingIndex);
                if (colIndexInt < 0) {
                    if (!forTesting) {
                        editTxtObjForColIndex.setError("Cost of living index cannot be negative value");
                    }
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                if(!forTesting) {
                    editTxtObjForColIndex.setError("Cost of living index must be a numeric integer");
                }
                validInput = false;
            }
        }*/
        if(yearlySalary.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForYearlySalary.setError(" Yearly Salary cannot be empty");
            }
            validInput = false;
        }
        /*else
        {
            try {
                BigDecimal yearlySalaryDecimal = BigDecimal.valueOf(Double.parseDouble(yearlySalary));
                BigDecimal zero = new BigDecimal("0");
                int res = yearlySalaryDecimal.compareTo(zero);
                if (res == -1) {
                    if (!forTesting) {
                        editTxtObjForYearlySalary.setError("Yearly salary cannot be negative value");
                    }
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                if(!forTesting) {
                    editTxtObjForYearlySalary.setError("Yearly Salary must be a number");
                }
                validInput = false;
            }
        }*/
        if(yearlyBonus.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForYearlyBonus.setError(" Yearly bonus cannot be empty");
            }
            validInput = false;
        }
       /* else
        {
            try {
                BigDecimal yearlyBonusDecimal = BigDecimal.valueOf(Double.parseDouble(yearlyBonus));
                BigDecimal zero = new BigDecimal("0");
                int res = yearlyBonusDecimal.compareTo(zero);
                if (res == -1) {
                    if (!forTesting) {
                        editTxtObjForYearlyBonus.setError("Yearly bonus cannot be negative value");
                    }
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                if(!forTesting) {
                    editTxtObjForYearlyBonus.setError("Yearly bonus must be a number");
                }
                validInput = false;
            }
        }*/
        if(retirementBenefit.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForRetirementBenefit.setError(" Retirement Benefit cannot be empty");
            }
            validInput = false;
        }
        else
        {
            try {
                int retirementBenefitInt = Integer.parseInt(retirementBenefit);
                if ((retirementBenefitInt < 0) || (retirementBenefitInt > 100)) {
                    if (!forTesting) {
                        editTxtObjForRetirementBenefit.setError("Retirement benefit should be within 0-100 range");
                    }
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                if(!forTesting) {
                    editTxtObjForRetirementBenefit.setError("Retirement Benefit must be a numeric integer");
                }
                validInput = false;
            }
        }
        if(relocationStipend.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForRelocationStipend.setError(" Relocation Stipend cannot be empty");
            }
            validInput = false;
        }
        if(rsua.equals(""))
        {
            if(!forTesting)
            {
                editTxtObjForRsua.setError(" Restricted stock unit award cannot be empty");
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