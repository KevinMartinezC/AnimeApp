![Anime Icon](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCZiuFNdeGN8SLCWAvhBSMY1NPeJS6RKQLBA&usqp=CAU)

# 🎉 Anime App 


A modern Android application using Clean Architecture  and the latest development tools and libraries such as Kotlin, Hilt, Apollo Client, Room, and .

## 🚀 Features

- Search for anime by title 🕵️
- View detailed information about an anime 📚
- Add anime to your favorites list ❤️
- View detailed information about anime characters 👥

## 📚 App Structure

The app is composed of several screens:

- **Search Screen**: Allows users to search for anime by title. The search results are displayed in a grid view. Users can also filter the results by type and sort them by popularity. Each anime card in the grid view can be favorited/unfavorited by the user.

- **Favorite Screen**: Displays the user's favorited anime in a horizontal pager. Users can remove an anime from their favorites list by clicking on it in this view.

- **Detail Screen**: Shows detailed information about a selected anime, including its title, description, number of episodes, average score, genres, and characters.

- **Character Screen**: Provides detailed information about a specific character from an anime.

## 🛠️ Tech Stack

The application is built using the following technologies:

- Kotlin: The project is entirely written in Kotlin.
- Apollo GraphQL: Used for handling network requests and fetching data from the server.
- Room: Used for local data storage.
- Hilt: A dependency injection library.
- Flow: A reactive stream handling library.
- Coroutines: Used for asynchronous programming.
- 
## 🏗️  Clean Architecture <a name = "architecture"></a>

The application follows Clean Architecture and MVVM design pattern.

## ⚙️ Installation & Setup

You can clone this repository and import it into Android Studio. Make sure to have the latest version installed.

## 📜 License

This project is licensed under the terms of the MIT license.
