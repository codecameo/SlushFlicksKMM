# SlushFlicks

SlushFlicks has been built upon public APIs from IMDB. This application helps users to view trending, popular, upcoming and top-rated movies and Tv shows and their detailed information. Users can also search for a movie or Tv show. This app also cache data to support offline browsing.
This is a personal project and the core objective of this project was to have hands-on experience on Android jetpack library components (like Navigation, *JetpackCompose*), MVI architecture, *Kotlin Multiplatform Mobile(KMM)*.<br><br>
[![SlushFlicks](https://img.shields.io/badge/MAD-Score-green)](https://madscorecard.withgoogle.com/scorecards/3640381993/)
[![SlushFlicks](https://img.shields.io/badge/KMM-Project-blue)](https://kotlinlang.org/lp/mobile)
[![SlushFlicks](https://img.shields.io/badge/Jetpack-Compose-orange)](https://developer.android.com/jetpack/compose)

<div>
<img src=https://github.com/codecameo/SlushFlicksKMM/blob/main/screenshots/slushflicks_demo.gif >
</div>

# Libraries & Components
+ Kotlin Multiplatform Mobile(KMM)
+ Jetpack Compose (Android' Modern UI toolkit)
+ SqlDelight (Database)
+ Kotlin Coroutines (Asynchronous programming)
+ Jetpack Navigation Component
+ Koin (Dependency Injection)
+ Ktor (Network Library)
+ ViewModel
+ Firebase

# Architecture
**MVI** architecture has been followed in this project where the *View* is producing *Event*s (Intent) based on user interaction. When *ViewModel* receives these event it updates the *ViewState* to show loading state if necessary and observe for data from the Repository. Repository responds to the request with either *Success* or *Error* and upon receiving the response *ViewModel* update the *ViewState* accordingly. As the View is observing for the *ViewState*, it eventually gets notified about the response and show relevant information to the user.

+ **ViewState:** ViewState can be considered as the snapshot of the current state of the screen.
+ **Event:**  Events are being fired from the screen based on user interaction.
+ **ViewEvent:** When *ViewState* changes it fires the *ViewEvent*s which eventually reflects the changes to the user on the screen.
<br><br>
<img height="60%" width="60%" src=https://github.com/codecameo/SlushFlicksKMM/blob/main/screenshots/mvi.png >

# DataManager
As the **Facade** design pattern suggests, to create an abstraction layer on top to hide the internal complex implementation. DataManager works as an abstraction layer on top of the local data storage system. As local data can be saved in *Database*, *SharedPreference* or even in the *asset* folder in different files, *DataManager* provides a simple interface, hiding the information about the actual storage location of data. It also enhances the scalability, as new Local Storage can be added in the future with minimal change.
+ **DatabaseManager:** Provides data from local database
+ **LocalDataManager:** Provides non-persistent data. Data that is essential to the different states of the application can be kept here to ensure faster access.
+ **SharedPrefManager:** Provides data from *SharedPreference*
+ **FireStoreManager:** Provides data from firestore. As firestore manages a persistent database for itself and data can be accessed locally from firestore, it has been kept here in the *DataManager*.
