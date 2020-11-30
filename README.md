# What is Pento?

Pento is for anyone new to an area who are looking for new friend groups that share a common interest. Pento is a social networking mobile app that Connects you with other like-minded people in a simple and safe way. Unlike Facebook messenger’s popular group systemour app actively finds and presents small, interest-focused groups to our users without them having to already have any connections.

## Folder Organization
* PentoSource - Android app source code
* Backend - Pento API source code
* Databse and Frontend - Miscellaneous notes on design and development

## Development Tools

* [Android Studio](https://developer.android.com/studio/) - The IDE used
* [Gradle](https://gradle.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - API Development
* [Google Firebase](https://aws.amazon.com/) - Authentication, security and data storage

## Authors

* **Jeren Mckey** - *Developer* - [Github](https://github.com/Jeren-Mckey)
* **Jason Tang** - *Developer* - [Github](https://github.com/jason-T-Tang)
* **Evan Ogawa** - *Developer* - [Github](https://github.com/EOgawa)
* **Areeb Yaqub** - *Developer* - [Github](https://github.com/areebia)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

---

# Development Setup (Test Builds)

## Technical Requirements
* Android Studio (IDE)
    * Build Tools Version: 30.0.2
    * Compile Sdk Version: 30
    * JRE 11.0
    * Gradle Plugin Version: 4.0.1
    * Gradle Version: 6.3


## How to Run
1. Start the projects "PentoSource" and "backend" in Android studio and in seperate windows. "PentoSource" is the project that handles all of the frontend for the app while the "backend" project holds the source for the Pento API that is used to interact with the database.

2. In the frontend project make sure that you set up and android emulator for any android device after API 16 (Recommended: Google Pixel 2 emulator). Step by step instructions for setting up an adnroid emulator available here: https://developer.android.com/studio/run/emulator

3. In backend project, open the terminal plugin available in Android Studio and run the command "gradlew bootrun". Running this command should start the embedded tomcat server processes for backend debugging and also is what the frontend will use while testing the app interactions. Whenever you want to make changes to the backend and test them you must remember to kill the process and restart using the same command.

4. In the frontend project use the "run" tab or press the play button to start running the application in the emulator that you created in step 2. From here you can begin testing and development for Pento.

## Common Issues

* When setting up the backend project you need to make sure that your JAVA_HOME path points to the jre folder that includes jre 11 instead of any other jdk that you might have installed. Otherwise, when running the spring boot processes you will get a java error.





