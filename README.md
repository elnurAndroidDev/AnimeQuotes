# Top Anime
Top Anime is a modern Android application that showcases top anime, built with **Clean Architecture**, **Jetpack Compose**, and **Kotlin**.  
It follows best practices in modularization, state management, and UI/UX design.

<p align="center"> 
  <img src="![photo_2025-08-06_23-24-05](https://github.com/user-attachments/assets/17f82fb8-2e43-4ebc-aa62-ba0710d295d2)" width="30%" /> 
  <img src="![photo_2025-08-06_23-23-59](https://github.com/user-attachments/assets/c5aa9642-21c1-4bfe-b369-0b2b82d3d5f7)" width="30%" /> 
  <img src="![photo_2025-08-06_23-24-04](https://github.com/user-attachments/assets/c277b484-efa5-4251-a868-ec8c4256f676)" width="30%" /> </p> 
<!-- <p align="center"> 
  <img src="screenshots/screen4.png" width="30%" /> 
  <img src="screenshots/screen5.png" width="30%" /> 
  <img src="screenshots/screen6.png" width="30%" /> </p> -->

## 📱 Features

- **Top Anime List**:  
  Home screen displays a list of top-rated anime using remote data. Tapping an item opens a detail screen with a **shared element transition**.

- **Search Screen**:  
  Users can search for anime and see a **search history** of previous queries.

- **Favorites Screen**:  
  Users can **add/remove anime to favorites**, and view their saved list anytime.

## ✨ Highlights

- Beautiful shared element transition between list and detail
- Search suggestions from previous history
- Offline-first support
- Modular and testable codebase

## 🧱 Architecture

This project follows the **Clean Architecture** principles with a multi-module structure

## 🚀 Tech Stack

| Layer        | Libraries / Tools                  |
|--------------|------------------------------------|
| UI           | Jetpack Compose, Coil              |
| State Mgmt   | ViewModel, StateFlow, Flow         |
| DI           | Hilt                               |
| Networking   | Retrofit                   |
| Database     | Room                               |
| Architecture | Clean Architecture (Domain Driven) |
