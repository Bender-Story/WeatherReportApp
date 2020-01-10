
# Fetch Latest weather reports of different cities

This App has been created to show the latest weather reports

Screen 1 : On the launch of the app user will see a edit text where he can enter the city name to see the results of cities that are available.

Screen 2: click on a city will take the user to the second screen which show details of the weather.

Dialog : when there is a network error show error screen with a retry button.

Use Case 1.
As a user Given I am on the home screen When I type in to the search bar on the home page to see a list of available cities that pattern matches what I have typed

Use Case 2.
As a user Given I am on the home screen And there is a list of available cities (based on what I've
typed) When I tap on a city Then I will be on the city Screen Then I will see the current weather
image Then I will see the current humidity Then I will see the current weather in text form Then I will
see the current weather temperature

Use Case 3.
As a user Given I am on the home screen And I have not viewed a City's weather Then I should see a
list view empty state

Use Case 4.
As a user Given I am on the home screen
And I have previously viewed any city's weather Then I should see an ordered list of the recent 10
cities that I have previously seen. And I should see the latest City that I have viewed at the top of the
list

Use Case 5.
As a user Given I have previously viewed any city's weather When I have relaunched the app
(terminating the app an





The Code has following things:

It has been written in Kotlin - Android using Androidx components in MVVM Pattern.

It has Koin integration for dependency injection . It is injecting view model, app repo and service interface.

It has a mock service setup for unit test cases This fetches response locally instead of fetching from server for unitests.

It has the unittests written using junit and Instrumentation test cases written using espresso.



This app also consists of a local cache, so whenever there is no service it would fetch the data from the locale cache.



The libraries or languages that are being used for this project are.

1. Kotlin

2. Retrofit - service calls

3. junit - Testing

4. espresso - ui Testing

5. Mockito - Testing

6. Gson

7. Rxjava

8. RecyclerViews

9. androidx components

10. coroutines

11.Koin
