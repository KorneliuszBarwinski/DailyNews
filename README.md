# DailyNews
Android application that shows news from around the world.

Application offers dark/light mode.
News are loaded dynamically into RecyclerView (10 at a time) - there is used pagination.
There are severals news categories.
News details are opened in WebView.
Application shows errors from api in Toasts.

## Architecture
App is written in Kotlin.
I tried to use Clean Architecture with MVVM.
Navigation - androidx.navigation
DI - Hilt
API communication - Retrofit with OkHttp client
Pagination - androidx.paging
Loading images - Glide

## Used APIs
All news are fetched from NewsAPI -  https://newsapi.org 
