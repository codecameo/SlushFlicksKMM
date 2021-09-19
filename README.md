# SlushFlicks

SlushFlicks has been built upon public APIs from IMDB. This application helps users to view trending, popular, upcoming and top-rated movies and Tv shows and their detailed information. Users can also search for a movie or Tv show. This app also cache data to support offline browsing.
This is a personal project and the core objective of this project was to have hands-on experience on Android jetpack library components (like Navigation, *JetpackCompose*), MVI architecture, *Kotlin Multiplatform Mobile(KMM)*.<br><br>
[![SlushFlicks](https://img.shields.io/badge/MAD-Score-green)](https://madscorecard.withgoogle.com/scorecards/3640381993/)

<div>
<img src=https://github.com/codecameo/SlushFlicksKMM/blob/readme_content/main/slushflicks_demo.gif >
</div>

# Architecture
**MVI** architecture has been followed in this project where the *View* is producing *Event*s (Intent) based on user interaction. When *ViewModel* receives these event it updates the *ViewState* to show loading state if necessary and observe for data from the Repository. Repository responds to the request with either *Success* or *Error* and upon receiving the response *ViewModel* update the *ViewState* accordingly. As the View is observing for the *ViewState*, it eventually gets notified about the response and show relevant information to the user.

+ **ViewState:** ViewState can be considered as the snapshot of the current state of the screen.
+ **Event:**  Events are being fired from the screen based on user interaction.
+ **ViewEvent:** When *ViewState* changes it fires the *ViewEvent*s which eventually reflects the changes to the user on the screen.
<br><br>
<img height="60%" width="60%" src=https://github.com/codecameo/SlushFlicksKMM/blob/main/screenshots/mvi.png >