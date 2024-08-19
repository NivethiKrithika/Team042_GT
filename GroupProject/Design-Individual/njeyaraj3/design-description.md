*1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4)* compare job offers (disabled if no job offers were entered yet[1]).  

JobComparerService is the entry point for this application and thus the main function in JobComparerService will be called when the app is started. All the initializations will be done in main function. The four other important classes for this app will be instantiated with default values, and the default value of 1 will be set for all the comparison parameters. Also, the main function displays the menu with four options.  Based on the option selected, corresponding functions will be called.

*2. When choosing to enter current job details, a user will:*

`   `*1. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:*

`      `All the attributes below are added to a class Job, which is a parent class that has two children, currentJob and Offer. Since most of the parameters are common to both the classes, the common parameters are added to parent class, Job. To differentiate current job from an offer, ‘isCurrentJob’ attribute is used which is set to 1 only if it is a current job. By default, currentJobInfoPresent will be set to false when this singleton instance of the class ‘CurrentJob’ is created when main function is invoked.

The GUI drives calls to ‘enterOrEditCurrentJob()’ using ‘currentJob’ instance which presents a page wherein the below details can be entered freshly or edited. 

`      `*1. Title*

`          `For this requirement, an attribute ‘title’ of type String is added to Job class.

`      `*2. Company*

`         `To satisfy this requirement, an attribute ‘company’ of type String is added to Job class

`      `*3. Location (entered as city and state)*

`         `To satisfy this requirement, two attributes ‘state’ and ‘city’ of type String are added to Location class. The user enters the input as state and city which will be stored in location instance in Job class

`      `*4. Cost of living in the location (expressed as an index)*

`         `To realize this requirement, an attribute ‘costOfLiving’ of type int is added to Job class.

`      `*5. Yearly salary*

`         `To realize this requirement, an attribute ‘yearlySalary’ of type Currency is added to Job class.

`      `*6. Yearly bonus*

`          `To realise this requirement, an attribute ‘yearlyBonus’ of type Currency is added to Job class.

`      `*7. Retirement benefits (as percentage matched) (Given as Integer 0-100)*

`          `For this requirement, an attribute ‘retirementBenefits’ of type int is added to Job class.

`      `*8. Relocation stipend*

`          `To satisfy this requirement, an attribute ‘relocationStipend’ of type Currency is added to Job class.

`      `*9. Restricted stock unit award (expressed as a lump sum vested over 4 years)*

`          `For this requirement, an attribute ‘rsua’ of type long is added to Job class.

`   `*2. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.*

When the save button is pressed, ‘handleSave()’ function is called in which all the details will be saved to database and ‘currentJobInfoPresent’ is set to ‘True’. Then ‘returnToMenu()’ function is called which returns to Main class that displays the main menu. If suppose cancel button is pressed, ‘handleCancel()’ is called in which the control returns to main, neither the details will be saved to database nor the ‘currentJobInfoPresent’ attribute is set to True.

*3. When choosing to enter job offers, a user will:*

‘Offer’ class has all the required attributes and operations to track job offers and compare the recently saved job offer with current job. By default, ‘numberOfOffers’ is set to 0. 

`   `*1. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.*

When the user presses the second option from main menu to add job offer, the ‘addOffer()’ function in Offer() is called which presents a page with all details similar to above for current Job with few additional buttons.

`   `*2. Be able to either save the job offer details or cancel*

When the save button is pressed, handleSave() is called, a new instance of offer is created with details given. ‘isSavedRecently’ variable helps to track the offer recently saved . This tracking helps the user to compare the recently saved offer with the current job. On pressing save button, the list of offers are traversed and ‘isSavedRecently’ is set to false for all the list of offers. ‘isSavedRecently’ is set to ‘True’ to mark the offer details that is recently entered. Then ‘numberOfOffers’ counter is incremented by 1 and the instance is added to ‘offers’ list with “addOfferToList()’ function. Also the info is saved to database.

`   `*3. Be able to*

` `*(1) enter another offer,*

If this button is pressed, ‘addOffer()’ is called which again displays the interface with all job details, thus enabling the  user to add details of another offer.

*(2) return to the main menu, or*

If return button is pressed, ‘returnToMenu()’ is called in which the variable ‘isSavedRecently’ is set to false for all the offers in the offers list and the control returns back to main. 

` `*(3) compare the offer (if they saved it) with the current job details (if present).*

If ‘compare offer’ button is pressed, ‘handleComparision()’ function is called. Inside this function, the ‘getInstance()’ function of ‘CurrentJob’  is called which returns the instance of current job. With ‘currentJob’ instance, ‘isCurrentJobInfoPresent()’ function is called to check if current job details is present in the system. If so, the ‘getInstance()’ function in ‘ComparisionSettings’ is called and with the instance of ‘ComparisionSettings’ the information regarding weights are obtained. The recently saved offer is fetched by traversing the list and checking ‘isSavedRecently’ variable. If the variable is set to true for any offer in the ‘offers’ list, the rank is computed for recently saved offer and current job using the details obtained from comparison settings with ‘computeRank()’ function. Next ‘displayComparisionResults()’ function is called which lists the recently saved offer and the current job details based on the ranking.

*4. When adjusting the comparison settings, the user can assign integer weights to:*

By default all the values are set to 1 during initialization in main function using ComparisonSettings method ‘initializeComparisionSettings’.

If the user chooses the option from main menu to adjust comparison settings, with the ‘comparisionSettings’ instance, editSettings() function is called which displays an interface with all the attributes listed below. 

`   `*1. Yearly salary*

`  `For this requirement, an attribute ‘yearlySalaryWeight’ of type int is added to Job class. 

`   `*2. Yearly bonus*

` `For this requirement, an attribute ‘yearlyBonusWeight’ of type int is added to Job class.

`   `*3. Retirement benefits*

For this requirement, an attribute ‘retirementBenefitsWeight’ of type int is added to Job class.

`   `*4. Relocation stipend*

For this requirement, an attribute ‘relocationStipendWeight’ of type int is added to Job class.

`   `*5. Restricted stock unit award*

For this requirement, an attribute ‘rsuaWeight’ of type int is added to Job class.

If no weights are assigned, all factors are considered equal. 

By default all the values are set to 1 during initialization in main function using ComparisonSettings method ‘initializeComparisionSettings’ and this will be maintained until the values are modified using ‘adjust settings’ option in main menu.

\5. When choosing to compare job offers, a user will:

Comparer class is responsible for comparing all the jobs in the system based on the weights in settings. It checks if there is sufficient number of job offers to make comparison using ‘getNumberOfOffers()’ in Offer class and ‘isCurrentJobInfoPresent()’ in CurrentJob class. If sufficient number of jobs are not present, then suitable message is displayed and the comparison will not be made.

`   `*1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.*

To realize this requirement, ‘handleComparisionOfAllOffers()’ function in Comparer class is called. This function gets the instance of current job to get current job details and gets the list of offers through ‘getOffersList’ function. The list is traversed and the job related details are fetched to compute the rank of all the offers and the current job using ‘computeRank()’ function. Once the rank is computed, the details are captured in tree map,’offerScoreMap’ with key as rank and instance of the job as value. This sorts the job instance based on the rank. For displaying, displayRankingInList() is called which prints the title and company details of the instance fetched from value in ‘offerScoreMap’ tree map in descending order based on key, i.e. rank.

`   `*2. Select two jobs to compare and trigger the comparison.*

When the comparison is triggered between two jobs in the list, the suitable instance of the job is recognised, and the corresponding details are fetched via the instance details.

`   `*3. Be shown a table comparing the two jobs, displaying, for each job:*

`      `*1. Title*

To realize this requirement, getTitle() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*2. Company*

To realize this requirement, getCompany() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*3. Location* 

To realize this requirement, getLocation() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*4. Yearly salary adjusted for cost of living*

Yearly salary adjusted for cost of living is computed whenever a new offer or current job is added to the system. The value is stored in ‘ays’ variable of type Currency in ‘Job’ class..To realize this requirement, getAYS() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*5. Yearly bonus adjusted for cost of living*

Yearly bonus adjusted for cost of living is computed whenever a new offer or current job is added to the system. The value is stored in ‘ayb’ variable of type Currency in ‘Job’ class. To realize this requirement, getAyb() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*6. Retirement benefits*

To realize this requirement, getRetirementBenefits() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*7. Relocation stipend*

To realize this requirement, getRelocationStipend() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`      `*8. Restricted stock unit award*

To realize this requirement, getRsua() in ‘Job’ class is used. This function is not captured in class diagram as getter and setter methods are not shown in UML diagram.

`   `*4. Be offered to perform another comparison or go back to the main menu.*

To perform another comparison , handleComparisionOfAllOffers() is called which again lists the jobs based on ranking from which the user can choose two jobs for comparison.

*6. When ranking jobs, a job’s score is computed as the weighted sum of:*

*AYS + AYB + RS + (RPB \* AYS / 100) + (RSUA / 4)*

*where:*

*AYS = yearly salary adjusted for cost of living*

*AYB = yearly bonus adjusted for cost of living*

*RBP = retirement benefits percentage*

*RS = relocation stipend*

*RSUA = restricted stock unit award*

This computation is done in computeRank() function.

*The rationale for the* RSUA subformula is:

`   `5. value of a restricted stock unit award vests over 4 years

For this requirement, ‘rsua’ attribute in class ‘Job’ of type long is used

`   `*6. average value of the restricted stock unit award per year (RSUA / 4)*

*For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:*

*2/7 \* AYS + 1/7 \* AYB + 2/7 \* RS + 1/7 \* (RPB \* AYS / 100) + 1/7 \* (RSUA / 4)*

For this requirement, ‘computeRank()’ function divides ‘rsua’ attribute by 4.

`   `*7. The user interface must be intuitive and responsive.*

This is not represented in my design, as it will be handled entirely within the GUI implementation.

`   `*8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).*

This is not represented in my design, as this UML diagram captured only the design for single system.







