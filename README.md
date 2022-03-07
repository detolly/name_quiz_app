# The Name Quiz  

This project was completed as part of the class "Mobile og Distribuerte Applikasjoner" at HVL (Western Norway University of Applied Sciences.)  

## Espresso Test Information  

A successful run of `./gradlew connectedAndroidTest --info` will contain the following output:  
```
com.example.thenamequiz.DatabaseTest > number_of_registered_cards[Pixel_5_API_30(AVD) - 11] SUCCESS 
com.example.thenamequiz.ExampleInstrumentedTest > useAppContext[Pixel_5_API_30(AVD) - 11] SUCCESS 
com.example.thenamequiz.NavigationTest > main_button_to_add[Pixel_5_API_30(AVD) - 11] SUCCESS 
com.example.thenamequiz.NavigationTest > main_button_to_database[Pixel_5_API_30(AVD) - 11] SUCCESS 
com.example.thenamequiz.NavigationTest > main_button_to_quiz[Pixel_5_API_30(AVD) - 11] SUCCESS 
com.example.thenamequiz.ScoreTest > wrong_answer[Pixel_5_API_30(AVD) - 11] SUCCESS 
com.example.thenamequiz.ScoreTest > right_answer[Pixel_5_API_30(AVD) - 11] SUCCESS 
```  

  
The APKs used during testing are `app-debug.apk` and `app-debug-androidTest.apk`.  
  
I'm not sure exactly which commands gradle uses to install and run the tests, but it's probably something along the lines of this:  
  
```bash
adb install -r app-debug.apk
adb install -r app-debug-androidTest.apk
adb shell am instrument -w com.android.foo/androidx.test.runner.AndroidJUnitRunner
```