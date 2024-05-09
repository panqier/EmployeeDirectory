## Build tools & versions used
Android studio, Kotlin DSL(build.gradle.kts), minSdk = 24, targetSdk = 34, compileSdk = 34
      
## Steps to run the app
Directly run, remember emulator or your device sdk should higher than 24, recommended 31-34

## What areas of the app did you focus on?
Strcutured. MVVM + JetPack Compose + Retrofit

## What was the reason for your focus? What problems were you trying to solve?
Reason: Structued code can make it easy to extendable, find where to locate when the bugs come.
Problems met : Build configuration set up, like lib version, 
Android Permission set up, 
Composable does not have rich libs or references

## How long did you spend on this project?
a week. Mainly worked on Saturday, and spent some time in the evening

## Did you make any trade-offs for this project? What would you have done differently with more time?
yes some spare time after work because I have a full-time job. 
With more time I can build more functions and UI

## What do you think is the weakest part of your project?
I could say I am not a fully experienced with Jetpack Compose, when coming to the request like image cache, I did use the coil to handle this, which I searched online it should work.
But I am not 100% percentage sure.

## Did you copy any code or dependencies? Please make sure to attribute them here!
I used depenencies like retrofit, okhttp, animation.. all dependencies can be found in build.gradle
I did not copy the code, mostly like the reference from other resources

## Is there any other information you’d like us to know?
I am gonna share some screenshots below and explain the flow and logic, I hope I can get interview and talk about more

Logic:
Fetch Service when app open -> Loading View display -> 
if service successed -> display list
if service failed or response malformed -> display error dialog

![Screenshot_20240508_232018_Gallery](https://github.com/panqier/EmployeeDirectory/assets/70109318/f71e4602-0c9a-4b47-9549-a0371d0c7ab4)

![Screenshot_20240508_232032_Gallery](https://github.com/panqier/EmployeeDirectory/assets/70109318/a61819d0-779b-40ab-8d65-dddd5ece6384)

![Screenshot_20240508_232004_Gallery](https://github.com/panqier/EmployeeDirectory/assets/70109318/0ae1fbfd-43cc-40db-9fb3-34d2094e9572)
