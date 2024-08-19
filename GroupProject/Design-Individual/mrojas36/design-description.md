1.	When the app is started, the user is presented with the main menu, which allows the user to 
(1) enter or edit current job details, 
(2) enter job offers, 
(3) adjust the comparison settings, or 
(4) compare job offers (disabled if no job offers were entered yet).  

These four actions are represented by four methods in the class mainmenu; mainmenu class is the entry point to the system.  The four methods attached to these actions are (inputcurrentjob, inputjoboffer, adjustcompareset, comparejobsentered). 

2.	When choosing to enter current job details, a user will:
a.	Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
i.	Title
ii.	Company
iii.	Location (entered as city and state)
iv.	Cost of living in the location (expressed as an index)
v.	Yearly salary
vi.	Yearly bonus
vii.	Retirement benefits (as percentage matched) (Given as Integer 0-100)
viii.	Relocation stipend
ix.	Restricted stock unit award (expressed as a lump sum vested over 4 years)

I added the class Job with the attributes (job title, company name, state where job is located, city where job is located, the annual salary of the job, the annual bonus for the job, the retirement benefits, relocation stipend, restricted stock unit award).  The jobtitle, company name, state location, city location values are strings.  The annual salary, annual bonus, relocation stipend, restricted stock are entered as a float.  The retirement benefits and job index number are entered as integers.  The job index number (indnum) is needed for ranking the jobs based on value.  
The cost of living is handled by the class costoflivingindex. The costlivingindex contains methods that compares the combination of city and state entered (strings) to a table of consumer price indexes for the United States in order to determine the cost of living index (an int).   


b.	Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

The class job contains a method (saveandreturn2mainmenu) that saves job details and then returns to the main menu. The class job contains a method (unduereturn2mainmenu) that cancels job details entered and then returns to the main menu. 

3.	When choosing to enter job offers, a user will:
a.	Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
This is also handled by method 
b.	Be able to either save the job offer details or cancel.
c.	Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).
This is handled by the method inputjoboffer in the class main menu; the user is then redirected to the class job to enter detail. The class job contains a method (saveandreturn2mainmenu) that saves job details and then returns to the main menu. The class job contains a method (unduereturn2mainmenu) that cancels job details entered and then returns to the main menu. The class job contains a method (comparejobsentered) that compares the value of your current job to the job entered. 

4.	When adjusting the comparison settings, the user can assign integer weights to:
a.	Yearly salary
b.	Yearly bonus
c.	Retirement benefits
d.	Relocation stipend
e.	Restricted stock unit award
If no weights are assigned, all factors are considered equal.

I created the class adjcompsettings to allow the user to adjust the weight of yearly salary, yearly bonus, retirement benefits, relocation stipend, and restricted stock award by entering as an integer value.  The class adjcompsettings contains a method (saveadj2weight) that saves job details and then returns to the main menu. The class adjcompsettings contains a method (unduereturn2mainmenu) that cancels job details entered and then returns to the main menu. By default the different attributes are given the same weight. 

5.	When choosing to compare job offers, a user will:
a.	Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
b.	Select two jobs to compare and trigger the comparison.
c.	Be shown a table comparing the two jobs, displaying, for each job:
i.	Title
ii.	Company
iii.	Location 
iv.	Yearly salary adjusted for cost of living
v.	Yearly bonus adjusted for cost of living
vi.	Retirement benefits
vii.	Relocation stipend
viii.	Restricted stock unit award
d.	Be offered to perform another comparison or go back to the main menu.

The title, company, location (state and city), yearly salary, yearly bonus, retirement benefits, relocation stipend, and restricted stock unit award are stored in a array all jobs.  The method in comparejobsentered in the class mainmenu compares and ranks the jobs based on the value calculation in the class mostvaluablejob.  

6.	When ranking jobs, a job’s score is computed as the weighted sum of:

AYS + AYB + RS + (RPB * AYS / 100) + (RSUA / 4)

where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
RBP = retirement benefits percentage
RS = relocation stipend
RSUA = restricted stock unit award

The rationale for the RSUA subformula is:
a.	value of a restricted stock unit award vests over 4 years
b.	average value of the restricted stock unit award per year (RSUA / 4)

For example, if the weights are 2 for the yearly salary, 2 for relocation stipend, and 1 for all other factors, the score would be computed as:


2/7 * AYS + 1/7 * AYB + 2/7 * RS + 1/7 * (RPB * AYS / 100) + 1/7 * (RSUA / 4)

The RSUA subformula is used in class mostvaluablejob via method computejobvalue.

7.	The user interface must be intuitive and responsive.
This cannot be represented in this UML diagram.  This is just a design of the system, not an implementation.  This aspect will be handled when the GUI is implemented.

8.	For simplicity, you may assume there is a single system running the app (no 
communication or saving between devices is necessary).

My diagram is intended to represent the design of a single system running the app. 
