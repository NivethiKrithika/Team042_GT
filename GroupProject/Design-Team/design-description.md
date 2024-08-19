# Requirements
1.When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet). 

>    The GUI aspect is not shown on the design. However, from the MainMenu class, there is a method to edit current job, if there is no current job then the view would show that it does not exist(1). From the MainMenu the user can also create(enter) jobs (which add them to the job list) (2). Users will also be able to adjust the comparison settings (3). The MainMenu will use the Comparer class to compare the jobs in the job list(4) and show them in a rank with their scores. If there the list size of joblist is less than 1 then we will disable the compare job menu option.

2. When choosing to *enter current job details, a user will:
   
   a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
      1. Title
      2. Company
      3. Location (entered as city and state)
      4. Cost of living in the location (expressed as an index)
      5. Yearly salary
      6. Yearly bonus
      7. Retirement benefits (as percentage matched) (Given as Integer 0-100)
      8. Relocation stipend
      9. Restricted stock unit award (expressed as a lump sum vested over 4 years)
     
     
> To realize this requirement, I have a Job class which contains the attributes(a):**
> 
> 1. title for the title
> 2. company for the company
> 3. location for the location (entered as city and state - this is handled by class "Location")
> 4. costOfLiving for the cost of living in the location (expressed as an index)
> 5. yearlySalary for the yearly salary
> 6. yearlyBonus for the yearly bonus
> 7. retirementBenefits for the retirement benefits (as percentage matched) (given as Int 0-100)
> 8. relocationStipend for the relocation stipend
> 9. rusa: BigDecimal for the restricted stock unit award (expressed as a lump sum vested over 4 years)
> 10. isCurrentJob to indicate if the true, the job is the current job. If false, the job is a regular job offer
> 11. currentJobInfoPresent will indicate if true: the user does have a current job. If false, the user does not have a current job

   b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
      
> To realize this requirement, the MainMenu class has the editCurrentJob method which allows the user to enter and edit the items listed above for their current job.  This method will set the currentJob in the MainMenu class.  To cancel and exit without saving is handled by the GUI and is not part of our design. 
      
3. When choosing to *enter job offers,* a user will:
   a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
      To realize this requirement, we have method "createJobs" in class Job which allows the user to enter the attributes noted in requirement 2. 
    
   
   b. Be able to either save the job offer details or cancel.

> To realize this requirement, the MainMenu class will handle this:
> 
> 1. within the createJobs() method, the logic will create a Job object with the user entered values and add it to the jobList array.
> 2. The UI will have a "back" button that will redirect the user to the Main Menu simulating a "cancel" action.
>       
   c. Be able to enter another offer

> This requirement is realized with the createJobs() method of the MainMenu class.

  return to the main menu

> This requirement is realized with the UI itself. It has a back button which will redirect the user to the main menu page

  compare the offer (if they saved it) with the current job details (if present).

> This requirement will be realized with a compareOfferToCurrentJob() method of the MainMenu class which will compare the user’s current job to the job offer created and saved.

4. When *adjusting the comparison settings,* the user can assign integer *weights* to:
   1. Yearly salary
   2. Yearly bonus
   3. Retirement benefits
   4. Relocation stipend
   5. Restricted stock unit award

>    This requirement will be realized through the "ComparisonSettings" class and its attributes and methods:
>       1. yearlySalaryWeight: weight for the salary
>       2. yearlyBonusWeight: weight for the bonus
>       3. retirementBenefitsWeight: weight for retirement benefits
>       4. relocationStipendWeight: weight for the relocation stipend
>       5. restrictstockawardWeights: weight for restricted stock unit award

   If no weights are assigned, all factors are considered equal.
>    All of the weights are set to "1" in compareSettings

5. When choosing to *compare job offers,* a user will:
     a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if            present), clearly indicated.

>   This requirement will be realized mainly by the GUI however, in the UML class diagram the Comparer class has a rankJobs() method which when executed, will 
>   direct the user to a page that contains a list of job offers, ranked from best to worst (including the current job if present)


b. Select two jobs to compare and trigger the comparison.
  
>   This requirement will be realized mainly by the GUI. The user will select two offers using the interface which will then be set to two variabels (JobOfferA       and JobOfferB)
  
c. Be shown a table comparing the two jobs, displaying, for each job:
1. Title
2. Company
3. Location 
4. Yearly salary adjusted for cost of living
5. Yearly bonus adjusted for cost of living
6. Retirement benefits
7. Relocation stipend
8. Restricted stock unit award

> Requirements 5.2 and 5.3 will be realized by the handleComparison() method of the MainMenu class. The user will select two jobs from the list displayed by requirement 5.1 above. Those two jobs will be passed in an array as a parameter to the handleComparison() method and will then display a table with each respective job's title, company, location, salary, bonus, retirement, benefits, stipend, and stock. The salary and bonus will be adjusted using the cost of living index value.

   d. Be offered to perform another comparison or go back to the main menu.
      
> This requirement will be realized through the GUI. The interface will have a “Main Menu” button that will execute the mainMenu() method of the MainMenu class         and a “Compare More Offers” button that will execute the rankJobs() method of the MainMenu class that will direct the user back to the ranked list of job             offers.
      
6. When ranking jobs, a job’s score is computed as the **weighted** sum of:
   AYS + AYB + RS + (RPB \* AYS / 100) + (RSUA / 4)
   where:
   AYS = yearly salary adjusted for cost of living
   AYB = yearly bonus adjusted for cost of living
   RBP = retirement benefits percentage
   RS = relocation stipend
   RSUA = restricted stock unit award

   The rationale for the RSUA subformula is:

   1. value of a restricted stock unit award vests over 4 years
   2. average value of the restricted stock unit award per year (RSUA / 4)

   For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:

   2/7 \* AYS + 1/7 \* AYB + 2/7 \* RS + 1/7 \* (RPB \* AYS / 100) + 1/7 \* (RSUA / 4)

> For this requirement, the "Comparer" classes contains methods to calculate AYS, ABS, and overall job score. The ‘computeScore()’ function is used to calculate a job's score. The ‘computeAYS()’ method is used to calculate AYS.  Also, the ‘computeAYB()’ method is used to calculate AYB.    The Comparer class also contains a tree map (jobScoreMap) for ranking jobs based on computed value.

7. The user interface must be intuitive and responsive.
   
> This is not represented in my design as it will be handled entirely within the GUI implementation


8. For simplicity, you may assume there is a *single system* running the app (no communication or saving between devices is necessary). 
  
> In our diagram, we only focus on a single-system running app and all jobs and settings can be saved in main class for now.


