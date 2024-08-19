package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    EditText editTxtObjForRetirementBenefitWeight,editTxtObjForYearlySalaryWeight,editTxtObjForYearlyBonusWeight,editTxtObjForRsuaWeight,editTxtObjForRelocationStipendWeight;
    Button btnObjSaveSettings,btnObjReturn;
    public SettingsActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparer_settings);
        editTxtObjForRetirementBenefitWeight = findViewById(R.id.editTxtRetBenefitWeight);
        editTxtObjForYearlyBonusWeight = findViewById(R.id.editTxtYbWeight);
        editTxtObjForYearlySalaryWeight =  findViewById(R.id.editTxtYsWeight);
        editTxtObjForRsuaWeight =  findViewById(R.id.editTxtRsuaWeight);
        editTxtObjForRelocationStipendWeight =  findViewById(R.id.editTxtRelocStipendWeight);
        btnObjSaveSettings = findViewById(R.id.buttonSaveSettings);
        btnObjReturn = findViewById(R.id.buttonReturn);

        JobDatabase jobDatabase = new JobDatabase(SettingsActivity.this);
        ComparisonSettings updatedComparisonSettings = jobDatabase.getComparisonSettings();
        editTxtObjForYearlySalaryWeight.setText(String.valueOf(updatedComparisonSettings.getYearlySalaryWeight()));
        editTxtObjForYearlyBonusWeight.setText(String.valueOf(updatedComparisonSettings.getYearlyBonusWeight()));
        editTxtObjForRetirementBenefitWeight.setText(String.valueOf(updatedComparisonSettings.getRetiremenetBenefitsWeight()));
        editTxtObjForRsuaWeight.setText(String.valueOf(updatedComparisonSettings.getRestrictedStockAwardWeight()));
        editTxtObjForRelocationStipendWeight.setText(String.valueOf(updatedComparisonSettings.getRelocationStipendWeight()));

        btnObjSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobDatabase jobDatabase = new JobDatabase(SettingsActivity.this);
                String yearlySalaryWt = editTxtObjForYearlySalaryWeight.getText().toString();
                String yearlyBonusWt = editTxtObjForYearlyBonusWeight.getText().toString();
                String retirementBenefitWt = editTxtObjForRetirementBenefitWeight.getText().toString();
                String relocationStipendWt = editTxtObjForRelocationStipendWeight.getText().toString();
                String rsuaWt = editTxtObjForRsuaWeight.getText().toString();
                boolean result = validateComparisonSettings(yearlySalaryWt, yearlyBonusWt, retirementBenefitWt, relocationStipendWt, rsuaWt, false);
                if (!result) {
                        Toast.makeText(SettingsActivity.this, "Missing or invalid field", Toast.LENGTH_LONG).show();
                } else {
                    if((Integer.parseInt(yearlySalaryWt) == 0) && (Integer.parseInt(yearlyBonusWt) == 0) && (Integer.parseInt(retirementBenefitWt) == 0) && (Integer.parseInt(relocationStipendWt) == 0) && (Integer.parseInt(rsuaWt) == 0))
                    {
                            Toast.makeText(SettingsActivity.this, "All the weights cannot be zero. Set atleast a weight that is non-zero", Toast.LENGTH_LONG).show();
                    }
                    else
                        {
                        ComparisonSettings comparisonSettings = new ComparisonSettings(Integer.parseInt(yearlySalaryWt),
                                Integer.parseInt(yearlyBonusWt), Integer.parseInt(retirementBenefitWt),
                                Integer.parseInt(relocationStipendWt), Integer.parseInt(rsuaWt));
                        boolean retStatus;
                        retStatus = jobDatabase.updateComparisonSettings(comparisonSettings);
                        if (retStatus) {
                            Intent backToMainMenuIntent = new Intent(SettingsActivity.this, MainActivity.class);
                            startActivity(backToMainMenuIntent);
                        } else {
                            Toast.makeText(SettingsActivity.this, "DB error. new values are not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        btnObjReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnBackIntent = new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(returnBackIntent);
            }
        });
    }
    public boolean validateComparisonSettings(String yearlySalaryWeight, String yearlyBonusWeight, String retirementBenefitWeight,
                                              String relocationStipendWeight, String restrictedStockAwardWeight, boolean forTestingWeight) {
        boolean validWeight = true;

        if(yearlySalaryWeight.equals(""))
        {
            if(!forTestingWeight)
            {
                editTxtObjForYearlySalaryWeight.setError(" Yearly Salary weight cannot be empty");
            }
            validWeight = false;
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
        if(yearlyBonusWeight.equals(""))
        {
            if(!forTestingWeight)
            {
                editTxtObjForYearlyBonusWeight.setError(" Yearly bonus weight cannot be empty");
            }
            validWeight = false;
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
        if(retirementBenefitWeight.equals(""))
        {
            if(!forTestingWeight)
            {
                editTxtObjForRetirementBenefitWeight.setError(" Retirement Benefit Weight cannot be empty");
            }
            validWeight = false;
        }
        /*else
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
        }*/
        if(relocationStipendWeight.equals(""))
        {
            if(!forTestingWeight)
            {
                editTxtObjForRelocationStipendWeight.setError(" Relocation Stipend weight cannot be empty");
            }
            validWeight = false;
        }
        if(restrictedStockAwardWeight.equals(""))
        {
            if(!forTestingWeight)
            {
                editTxtObjForRsuaWeight.setError(" Restricted stock unit award weight cannot be empty");
            }
            validWeight = false;
        }
        return validWeight;
    }
}