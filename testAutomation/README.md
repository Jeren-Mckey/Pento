# Test Automation Steps

This is an in-depth explanation on how to use the test automator espresso and how to run the tests.

## How to Create an Espresso Test

* Espresso is a built in test automator and can be accessed from within Android Studio.
* Under the run tab, there will be an option called **"Record Espresso Test"**. 
* Click the aforementioned option and the app will begin to run.
* When the app options, go through the app however you like.
* Exit the app once finished, and Espresso will create a test file from all the inputs you gave to espresso. 
* These test files are locacated in **PentoSource/app/src/androidTest/java/com/example/pento/**.

## How to Run an Espresso Test

* Right click on the test and then click run among the list of Options. 
* The app should automatically open and begin to go through your recorded test as faihfully as possible. 
* In future cases that require quick testing, turn off animations for the App.
* Here is a [Post](https://stackoverflow.com/questions/43751079/espresso-testing-disable-animation) that can help with disabling animations. 

## Possible Issues and Solutions

* Incorporate all kinds of buttons whether from the the buttons on the app or the android provided keyboard inputs to prevent mid-test run issues.
* Decide on a path to follow in your app for precise testing. This will allow you to make sure certain portions of the app are working before pushing to a Branch/Master.
* If you change the front end code, you must change the test code to reflect new or removed buttons. 
* Deleting and remaking the espresso tests is the fastest way to make sure your tests are correct.
