### Documentation is included in the Documentation folder ###

[ReFrameWork Documentation](https://github.com/nrkreddy94/UiPath_ReFramework_HelloWorld/blob/master/Documentation/REFramework%20documentation.pdf)

### ReFrameWork Template ###
**Robotic Enterprise Framework**

* built on top of *Transactional Business Process* template
* using *State Machine* layout for the phases of automation project
* offering high level exception handling and application recovery
* keeps external settings in *Config.xlsx* file and Orchestrator assets
* pulls credentials from *Credential Manager* and Orchestrator assets
* gets transaction data from Orchestrator queue and updates back status
* takes screenshots in case of application exceptions
* provides extra utility workflows like sending a templated email
* runs sample Notepad application with dummy Excel input data


### How It Works ###

1. **INITIALIZE PROCESS**
 + *InitiAllSettings* - Load config data from file and from assets
 + *InitiAllApplications* - Login to applications as per Config("OpenApps") field
   + *GetAppCredentials* - From Orchestrator assets or local Credential Manager
 + If failing, retry a few times as per Config("ProcessRetries")

2. **GET TRANSACTION DATA**
   + ./Framework/*GetTransactionData* - Fetches from Orchestrator queue as per Config("TransactionQueue")
   + ./*GetTransactionData* - Sample for working with Excel input files

3. **PROCESS TRANSACTION**
 + Try *ProcessTransaction*
 + If application exceptions happen
   + *SaveErrorScreen* - In Config("ErrorsFolder") with the exception message
   + Going to re/INITIALIZE
 + *SetTransactionStatus* - As Success, Failed or Rejected with reason
   + ./Framework/*SetTransactionStatus* - Updates the Orchestrator queue item
   + ./*SetTransactionStatus* - Sample for updating Excel input file

4. **END PROCESS**
 + *CloseAllApplications* - As listed in Config("CloseApps")


###  ReFramework HelloWorld Application ###
![RE_HelloWorld_Files](/MarkDown/RE_HelloWorld_Files.PNG)


#### WorkFlow Steps:
1) Input Frist name,Last name, Emails to send welcome message notifications
2) Pass Frstname and LastName to java method (JavaActivities) and it returns welcome message as response
3) Pass Emails list to Activity "WriteEmailTo" which parse the emails and add to "SendMailConfig.xslx"
4) Read all emailIds from this .xslx and add to "SendGMailQueue" 
5) Read  Body and Subject of Email template from "SendMailConfig.xslx"
6) Read all email Id one after one (Process) from Queue and send an email with welcome message
7) Close all excel files which are opended

#### Snippets

1) Inputs:


 ![FirstName](/MarkDown/FirstName.PNG) |
![LastName](/MarkDown/LastName.PNG)  |

![EmailList](/MarkDown/EmailList.PNG)  |   

 2) Email Sent Confirmation:

![Confirmation](/MarkDown/Confirmation.PNG)

3) Email Notification:

![EmailNotification](/MarkDown/ReceivedMail.PNG)

4) Logs:

![Logs](/MarkDown/Logs.PNG)

5) Queue Transaction:

![Transaction](/MarkDown/TransactionStatus.PNG)

6) SendGMail_Queue:

![SendGMail_Queue](/MarkDown/SendGMail_Queue.PNG)

7) Java Code for Welcome Message:

![JavaCode](/MarkDown/JavaCode.PNG)



Java code:

```Java
public static String welcomeMessage(String firstName, String lastName) {
		logger.info("Enter into welcomeMessage(): firstName=" + firstName + ", lastName=" + lastName);
		String message = "Welcome to UiPath!! " + lastName + ", " + firstName;
		logger.info("Exit from welcomeMessage() with message= " + message);
		return message;
	}
  
```

Logs:

 ```text
 07-13-2020 05:17:05 PM IST [ INFO - com.uipath.javaextesion.HelloWorld - line#: 29] - Enter into welcomeMessage(): firstName=JagadheeswarReddy, lastName=Punnati
07-13-2020 05:17:05 PM IST [ INFO - com.uipath.javaextesion.HelloWorld - line#: 31] - Exit from welcomeMessage() with message= Welcome to UiPath!! Punnati, JagadheeswarReddy

 ```
