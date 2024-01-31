# CS2212 - McBal Demographic Analysis Application

The goal of this project is to implement an application system that enables users to analyze and visualize key data metrics derived from the World Bank’s data repository. Additionally, the system must: a) enable the retrieval of demographic and other data for one selected country from the World Bank’s data repository; b) process the data using different types of analyses; c) render the retrieved data or the processed data using appropriately selected visualization mediums such as bar charts, line graphs, scattered plots, and pie charts. 

## Getting Started

These instructions will outline how to build and run our submission on your local machine for acceptance testing.

### Prerequisites

Please ensure that you have a Java IDE/Launcher which supports the import and export of Maven Projects. Further, please ensure that your machine has a working internet connection as HTTP Get Requests support the data retrieval mechanism of our project. See below for more details.
* [Eclipse IDE](https://www.eclipse.org/ide/) - Supported Java IDE
* [Maven](https://maven.apache.org/) - Dependency Management

### Installing

Please note, the steps to import a Maven projects may vary from IDE to IDE. As a general guideline to install our system, please refer to the following instructions:

* Download a copy of the project archive as a zip file
* Unzip the project zip file
* In your IDE/Launcher, click File > Import
* Select Maven > Existing Maven projects
* Browse for a root directory to import from
* Select the folder where you decompressed your zip file
* Select the project titled "/pom.xml Group_40:McBal_Demographic_Analysis_Application:1.0.0-SNAPSHOT:jar"

### Running the Program

After the program has been imported as a Maven project, simply run InitializeProgram.java under the default package. This will handle the execution of the entire application.

To add login accounts to the application, simply edit the Database plain text file following the formatting. A sample account is provided with:

* Username: User
* Password: Pass

Feel free to explore the user interface and play around with the selections!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

* Example code for the main GUI and API interactions was provided by Dr. Kostas Kontogiannis and the CS2212B teaching staff. 
* This project relies on the continual support of [World Bank's Data API](https://datahelpdesk.worldbank.org/knowledgebase/articles/889392-about-the-indicators-api-documentation).

