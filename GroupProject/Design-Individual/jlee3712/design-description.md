# Requirements
1. When the app is started, the user is presented with the main menu
   1. enter or edit current job details
   1. enter job offers 
   1. adjust the comparison settings
   1. compare job offers (disabled if no job offers were entered yet[^1]).  

   This requirement will not be represented in my design. It will be handled by the user via the GUI of the application 

1. When choosing to *enter current job details,* a user will:
   1. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
      1. Title
      1. Company
      1. Location (entered as city and state)
      1. Cost of living in the location (expressed as an [index](https://www.expatistan.com/cost-of-living/index/north-america))
      1. Yearly salary
      1. Yearly bonus
      1. Retirement benefits (as percentage matched) (Given as Integer 0-100)
      1. Relocation stipend
      1. Restricted stock unit award (expressed as a lump sum vested over 4 years)

      To realize this requirement, I have a CurrentJob class which contains the attributes:

      1. currentTitle for the title
      1. currentCompany for the company
      1. currentLocation for the location (entered as city and state)
      1. currentCoL for the cost of living in the location (expressed as an index)
      1. currentSalary for the yearly salary
      1. currentBonus for the yearly bonus
      1. currentRetBenefits for the retirement benefits (as percentage matched) (given as Int 0-100)
      1. currentRelStipend for the relocation stipend
      1. currentResStock for the restricted stock unit award (expressed as a lump sum vested over 4 years)

      These values are handled by the createCurrentJob method of the CurrentJob class that will initialize the attributes with the values retrieved from the GUI.

   1. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
      To realize this requirement, the User class has methods:
      1. saveCurrentJob() - saves the values put in by the user via the GUI
      2. cancelCreateEditJob()- exits the create/edit interface without saving the values put in by the user via the GUI
1. When choosing to *enter job offers,* a user will:
   1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
      To realize this requirement, I have a CurrentJob class which contains the attributes:
      1. offerTitle for the offer title
      1. offerCompany for the offering company
      1. offerLocation for the location (entered as city and state)
      1. offerCoL for the cost of living in the location (expressed as an index)
      1. offerSalary for the yearly salary
      1. offerBonus for the yearly bonus
      1. offerRetBenefits for the retirement benefits (as percentage matched) (given as Int 0-100)
      1. offerRelStipend for the relocation stipend
      1. offerResStock for the restricted stock unit award (expressed as a lump sum vested over 4 years)

      These values are handled by the createJobOffer method of the JobOffer class that will initialize the attributes with the values retrieved from the GUI.

   1. Be able to either save the job offer details or cancel.

      To realize this requirement, the JobOffer class will have the methods:

      1. saveJobOffer() - saves the values put in by the user via the GUI 
      1. cancelJobOffer() - exits the create/edit interface without saving the values put in by the user via the GUI
   1. Be able to 
      1. enter another offer
         This requirement is realized with the createJobNewOffer() method of the User class.
      1. return to the main menu
         This requirement is realized with the mainMenu() method of the User class which will redirect the user to the main menu page
      1. ` `compare the offer (if they saved it) with the current job details (if present).
         This requirement will be realized with a compareOfferToCurrentJob() method of the User class which will compare the user’s current job to the job offer created and saved.
1. When *adjusting the comparison settings,* the user can assign integer *weights* to:
   1. Yearly salary
   1. Yearly bonus
   1. Retirement benefits
   1. Relocation stipend
   1. Restricted stock unit award

   This requirement will be realized through the ComparionSettings class and its attributes and methods:
      1. salaryWeight: weight for the salary
      2. bonusWeight: weight for the bonus
      3. retBenefitsWeight: weight for retirement benefits
      4. relStipendWeight: weight for the relocation stipend
      5. resStockWeights: weight for restricted stock unit award

   If no weights are assigned, all factors are considered equal.
   This requirement will not be modeled in the UML diagram.

1. When choosing to *compare job offers,* a user will:
   1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

      This requirement will be realized mainly by the GUI however, in the UML class diagram the User class has a compareJobOffersPage() which when executed, will direct the user to a page that contains a list of job offers, ranked from best to worst (including the current job if present)

   1. Select two jobs to compare and trigger the comparison.
      This requirement will be realized mainly by the GUI. The user will select two offers using the interface which will then be set to two variabels (JobOfferA and JobOfferB)
   1. Be shown a table comparing the two jobs, displaying, for each job:
      1. Title
      1. Company
      1. Location 
      1. Yearly salary adjusted for cost of living
      1. Yearly bonus adjusted for cost of living
      1. Retirement benefits
      1. Relocation stipend
      1. Restricted stock unit award

      Requirements 5.2 and 5.3 will be realized by the compareJobOffers(JobOfferA, JobOfferB) method of the User class. The user will select two jobs from the list displayed by requirement 5.1 above. Those two jobs will be passed as parameters to the compareJobOffers() method and will then display a table with each respective JobOffer’s title, company, location, salary, bonus, retirement, benefits, stipend, and stock. The salary and bonus will be adjusted using the cost of living index value.

   1. Be offered to perform another comparison or go back to the main menu.
      This requirement will be realized through the GUI. The interface will have a “Main Menu” button that will execute the mainMenu() method of the User class and a “Compare More Offers” button that will execute the compareJobOffersPage() method of the User class that will direct the user back to the ranked list of job offers.
1. When ranking jobs, a job’s score is computed as the **weighted** sum of:

   AYS + AYB + RS + (RPB \* AYS / 100) + (RSUA / 4)

   where:
   AYS = yearly salary adjusted for cost of living
   AYB = yearly bonus adjusted for cost of living
   RBP = retirement benefits percentage
   RS = relocation stipend
   RSUA = restricted stock unit award


   The rationale for the RSUA subformula is:

   1. value of a restricted stock unit award vests over 4 years
   1. average value of the restricted stock unit award per year (RSUA / 4)

   For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:

   2/7 \* AYS + 1/7 \* AYB + 2/7 \* RS + 1/7 \* (RPB \* AYS / 100) + 1/7 \* (RSUA / 4)

   This is not represented in my design as it will be handled entirely within the logic of the GUI implementation. However, when the mathematical logic is executed for the job, the rankingScore attribute of the JobOffer and/or CurrentJob object will be set to the calculated value.

1. The user interface must be intuitive and responsive.

   This is not represented in my design as it will be handled entirely within the GUI implementation

1. For simplicity, you may assume there is a *single system* running the app (no communication or saving between devices is necessary).

   This is not represented in my design as it will be handled entirely within the GUI implementation


[^1]: To be precise, this functionality will be enabled if there are either (1) at least two job offers, in case there is no current job, or (2) at least one job offer, in case there is a current job.
