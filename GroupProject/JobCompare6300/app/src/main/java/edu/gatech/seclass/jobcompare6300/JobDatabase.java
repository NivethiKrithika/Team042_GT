package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;

public class JobDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Jobs.db";
    private static final int DATABASE_VERSION = 1;

    private static final String JOB_TABLE_NAME = "job_table";
    private static final String COLUMN_JOB_ID = "job_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_CostOfLiving = "cost_of_living";
    private static final String COLUMN_SALARY = "yearly_salary";
    private static final String COLUMN_BONUS = "yearly_bonus";
    private static final String COLUMN_RBENEFITS = "retirement_benefits";
    private static final String COLUMN_RELOCATION = "relocation_stipend";
    private static final String COLUMN_STOCK = "restricted_stock_unit_award";
    private static final String COLUMN_ISCURRENT= "current_job_boolean";

    private static final String COMPARISON_SETTING_TABLE = "comparison_settings_table";
    private static final String COLUMN_SALARY_WEIGHT = "yearly_salary_weight";
    private static final String COLUMN_BONUS_WEIGHT = "yearly_bonus_weight";
    private static final String COLUMN_RBENEFITS_WEIGHT = "retirement_benefits_weight";
    private static final String COLUMN_RELOCATION_WEIGHT = "relocation_stipend_weight";
    private static final String COLUMN_RESTRICTEDSTOCK_WEIGHT = "restricted_stock_award_weight";

    public JobDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String jobQuery = "CREATE TABLE " + JOB_TABLE_NAME +
                " (" + COLUMN_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, " +
                COLUMN_COMPANY + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_CostOfLiving + " INT, " +
                COLUMN_SALARY + " TEXT, " +
                COLUMN_BONUS + " TEXT, " +
                COLUMN_RBENEFITS + " INT, " +
                COLUMN_RELOCATION + " TEXT, " +
                COLUMN_STOCK + " TEXT," +
                COLUMN_ISCURRENT + " BOOL)";
        String comparisonSettingsQuery = "CREATE TABLE " + COMPARISON_SETTING_TABLE +
                " (" + COLUMN_SALARY_WEIGHT + " INT, " +
                COLUMN_BONUS_WEIGHT + " INT, " +
                COLUMN_RBENEFITS_WEIGHT + " INT, " +
                COLUMN_RELOCATION_WEIGHT + " INT, " +
                COLUMN_RESTRICTEDSTOCK_WEIGHT + " INT)";
        String comparisonSettingsDefaultQuery = "INSERT INTO " + COMPARISON_SETTING_TABLE +
                " (" +COLUMN_SALARY_WEIGHT+ "," +COLUMN_BONUS_WEIGHT+ "," + COLUMN_RBENEFITS_WEIGHT + "," +
                COLUMN_RELOCATION_WEIGHT +"," + COLUMN_RESTRICTEDSTOCK_WEIGHT +")" +
                " VALUES " + "(1,1,1,1,1)";
        sqLiteDatabase.execSQL(jobQuery);
        sqLiteDatabase.execSQL(comparisonSettingsQuery);
        sqLiteDatabase.execSQL(comparisonSettingsDefaultQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JOB_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COMPARISON_SETTING_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean addJob(Job job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, job.getTitle());
        cv.put(COLUMN_COMPANY, job.getCompany());
        cv.put(COLUMN_LOCATION, job.getLocation().getCity() + "," + job.getLocation().getState());
        cv.put(COLUMN_CostOfLiving, job.getCostOfLiving());
        cv.put(COLUMN_SALARY, job.getYearlySalary().toString());
        cv.put(COLUMN_BONUS, job.getYearlyBonus().toString());
        cv.put(COLUMN_RBENEFITS, job.getRetirementBenefits());
        cv.put(COLUMN_RELOCATION, job.getRelocationStipend().toString());
        cv.put(COLUMN_STOCK, job.getRestrictedStockUnitAward().toString());
        cv.put(COLUMN_ISCURRENT, job.isCurrentJob());

        long insert = db.insert(JOB_TABLE_NAME, null, cv);
        if(insert == -1) {
            //adding failed
            return false;
        }else {
            return true;
        }
    }
    public boolean updateJob(int rowid, Job job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, job.getTitle());
        cv.put(COLUMN_COMPANY, job.getCompany());
        cv.put(COLUMN_LOCATION, job.getLocation().getCity() + "," + job.getLocation().getState());
        cv.put(COLUMN_CostOfLiving, job.getCostOfLiving());
        cv.put(COLUMN_SALARY, job.getYearlySalary().toString());
        cv.put(COLUMN_BONUS, job.getYearlyBonus().toString());
        cv.put(COLUMN_RBENEFITS, job.getRetirementBenefits());
        cv.put(COLUMN_RELOCATION, job.getRelocationStipend().toString());
        cv.put(COLUMN_STOCK, job.getRestrictedStockUnitAward().toString());
        cv.put(COLUMN_ISCURRENT, job.isCurrentJob());

        long update = db.update(JOB_TABLE_NAME, cv, "COLUMN_JOB_ID = " +rowid,null);
        if(update == -1) {
            //adding failed
            return false;
        }else {
            return true;
        }
    }

    public boolean updateCurrentJob(Job job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, job.getTitle());
        cv.put(COLUMN_COMPANY, job.getCompany());
        cv.put(COLUMN_LOCATION, job.getLocation().getCity() + "," + job.getLocation().getState());
        cv.put(COLUMN_CostOfLiving, job.getCostOfLiving());
        cv.put(COLUMN_SALARY, job.getYearlySalary().toString());
        cv.put(COLUMN_BONUS, job.getYearlyBonus().toString());
        cv.put(COLUMN_RBENEFITS, job.getRetirementBenefits());
        cv.put(COLUMN_RELOCATION, job.getRelocationStipend().toString());
        cv.put(COLUMN_STOCK, job.getRestrictedStockUnitAward().toString());
        cv.put(COLUMN_ISCURRENT, job.isCurrentJob());

        long update = db.update(JOB_TABLE_NAME, cv, COLUMN_ISCURRENT + "=1",null);
        if(update == -1) {
            //adding failed
            return false;
        }else {
            return true;
        }
    }
    public ArrayList<Job> getAllJobs() {
        ArrayList<Job> jobList = new ArrayList<>();

        String query = "SELECT * FROM " + JOB_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String company = cursor.getString(2);
                String[] locationArray = cursor.getString(3).split(",");
                Location location = new Location(locationArray[0], locationArray[1]);
                int costOfLiving = cursor.getInt(4);
                BigDecimal yearlySalary = new BigDecimal(cursor.getString(5));
                BigDecimal yearlyBonus = new BigDecimal(cursor.getString(6));
                int retirementBenefits = cursor.getInt(7);
                BigDecimal relocationStipend = new BigDecimal(cursor.getString(8));
                BigDecimal restrictedStockUnitAward = new BigDecimal(cursor.getString(9));
                Boolean isCurrentJob = cursor.getInt(10) == 1 ? true : false;
                Job job = new Job(title, company, location, costOfLiving,
                        yearlySalary, yearlyBonus, retirementBenefits, relocationStipend,
                        restrictedStockUnitAward, isCurrentJob);
                jobList.add(job);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return jobList;
    }
    public Job getMostRecentJobOfferEntered() {
        String query = "SELECT * FROM " + JOB_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToLast();

        if (cursor != null) {
            String title = cursor.getString(1);
            String company = cursor.getString(2);
            String[] locationArray = cursor.getString(3).split(",");
            Location location = new Location(locationArray[0], locationArray[1]);
            int costOfLiving = cursor.getInt(4);
            BigDecimal yearlySalary = new BigDecimal(cursor.getString(5));
            BigDecimal yearlyBonus = new BigDecimal(cursor.getString(6));
            int retirementBenefits = cursor.getInt(7);
            BigDecimal relocationStipend = new BigDecimal(cursor.getString(8));
            BigDecimal restrictedStockUnitAward = new BigDecimal(cursor.getString(9));
            Boolean isCurrentJob = cursor.getInt(10) == 1 ? true : false;
            Job job = new Job(title, company, location, costOfLiving,
                    yearlySalary, yearlyBonus, retirementBenefits, relocationStipend,
                    restrictedStockUnitAward, isCurrentJob);
            return job;
        }
        cursor.close();
        db.close();
        return null;
    }

    public Job getCurrentJob() {
        String query = "SELECT * FROM " + JOB_TABLE_NAME
                + " WHERE " + COLUMN_ISCURRENT + " = " + 1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            String title = cursor.getString(1);
            String company = cursor.getString(2);
            String[] locationArray = cursor.getString(3).split(",");
            Location location = new Location(locationArray[0], locationArray[1]);
            int costOfLiving = cursor.getInt(4);
            BigDecimal yearlySalary = new BigDecimal(cursor.getString(5));
            BigDecimal yearlyBonus = new BigDecimal(cursor.getString(6));
            int retirementBenefits = cursor.getInt(7);
            BigDecimal relocationStipend = new BigDecimal(cursor.getString(8));
            BigDecimal restrictedStockUnitAward = new BigDecimal(cursor.getString(9));
            Boolean isCurrentJob = cursor.getInt(10) == 1 ? true : false;
            Job job = new Job(title, company, location, costOfLiving,
                    yearlySalary, yearlyBonus, retirementBenefits, relocationStipend,
                    restrictedStockUnitAward, isCurrentJob);
            return job;
        }
        cursor.close();
        db.close();
        return null;
    }

    public boolean deleteJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + JOB_TABLE_NAME + "WHERE " + COLUMN_TITLE + " = "
                + job.getTitle() + " AND " + COLUMN_COMPANY + " = "  +job.getCompany();

        Cursor cursor  = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCurrentJob(Job job) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + JOB_TABLE_NAME + "WHERE " + COLUMN_ISCURRENT + " = " + job.isCurrentJob() ;

        Cursor cursor  = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public ComparisonSettings getComparisonSettings() {
        String query = "SELECT * FROM " + COMPARISON_SETTING_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
          int salaryWeight = cursor.getInt(0);
          int bonusWeight = cursor.getInt(1);
          int retirementWeight = cursor.getInt(2);
          int relocationWeight = cursor.getInt(3);
          int stockWeight = cursor.getInt(4);

          ComparisonSettings comparisonSettings =
                  new ComparisonSettings(salaryWeight, bonusWeight, retirementWeight,
                          relocationWeight, stockWeight);

          return comparisonSettings;
        }
        cursor.close();
        db.close();
        return null;
    }


    public boolean updateComparisonSettings(ComparisonSettings comparisonSettings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SALARY_WEIGHT, comparisonSettings.getYearlySalaryWeight());
        cv.put(COLUMN_BONUS_WEIGHT, comparisonSettings.getYearlyBonusWeight());
        cv.put(COLUMN_RBENEFITS_WEIGHT, comparisonSettings.getRetiremenetBenefitsWeight());
        cv.put(COLUMN_RELOCATION_WEIGHT, comparisonSettings.getRelocationStipendWeight());
        cv.put(COLUMN_RESTRICTEDSTOCK_WEIGHT, comparisonSettings.getRestrictedStockAwardWeight());
        long update = db.update(COMPARISON_SETTING_TABLE, cv, null, null);
        if (update == -1) {
            //update failed
            return false;
        }else {
            return true;
        }
    }

    public long getTotalJobsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, JOB_TABLE_NAME);
        db.close();
        return count;
    }

}
