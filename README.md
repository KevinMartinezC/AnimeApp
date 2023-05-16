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

<p float="left">
    <img src="https://github.com/KevinMartinezC/AnimeApp/assets/90873838/f36c5251-e30b-4c13-943f-f908da7000de" width="180" alt="Screenshot_20230516-094027" />
    <img src="https://github.com/KevinMartinezC/AnimeApp/assets/90873838/3882f319-5982-4f43-a78d-c71b17dc87a6" width="180" alt="Screenshot_20230516-094027" />
    <img src="https://github.com/KevinMartinezC/AnimeApp/assets/90873838/2cda0191-9e0f-4d1e-8af6-62540c0fd206" width="180" alt="Screenshot_20230516-094027" />
    <img src="https://github.com/KevinMartinezC/AnimeApp/assets/90873838/31e78fcd-c04c-4277-8f5e-0d1ed1f6b57a" width="180" alt="Screenshot_20230516-094027" />
</p>

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
