# Skillforge - Android Learning App

Skillforge is a modern Android learning application built in Kotlin. The app allows users to browse course categories, explore course details, and navigate through individual lessons using a clean and intuitive interface.

## Features

- Browse course categories
- View popular courses
- Course detail screen with:
  - Course information
  - Instructor details
  - Lesson list
- Highlight currently playing lesson
- Image loading using Coil
- Loading and error states
- MVVM Architecture
- RecyclerView
- Navigation Component
- ViewBinding
  
## Tech Stack

- Kotlin
- MVVM Architecture
- Retrofit
- LiveData
- RecyclerView
- Navigation Component
- ViewBinding
- Coil
- Material Design

---

# AI Usage

AI tools were used throughout the development process to improve productivity while maintaining code quality.

## AI Tools Used

1. Claude (Primary Assistant)
2. Gemini
3. ChatGPT

Claude was used most frequently for generating Android UI code, architecture suggestions, and Kotlin implementations. Gemini was used for alternative approaches and explanations. ChatGPT was mainly used for debugging, improving code quality, and refining the overall application.

---

## Example Prompts

### Prompt 1

> Build a modern Android application using Kotlin and MVVM architecture that consumes a nested JSON API using Retrofit, Coroutines, LiveData, RecyclerView, Navigation Component, and Coil.

---

### Prompt 2

> I have attached a reference image of a mobile application UI. Build Android XML layouts and Kotlin code that closely match the design, including spacing, colors, typography, cards, buttons, icons, and overall Material Design styling. Recreate the UI as accurately as possible while keeping the code clean and maintainable.

---

### Prompt 3

> Help debug Android Studio errors after updating multiple Kotlin files. Ensure changes in one file do not break other classes, adapters, or fragments, and provide clean, maintainable fixes while preserving MVVM architecture.

---

# AI Reflection

## What AI got right

AI significantly accelerated development by:

- Generating the overall MVVM project structure
- Creating RecyclerView adapters
- Writing Retrofit API integration
- Designing modern Android UI layouts
- Suggesting Material Design improvements
- Providing reusable Kotlin code for navigation and data binding

This allowed the project to be completed much faster while maintaining a clean architecture.

---

## What AI got wrong

Occasionally, AI-generated code introduced dependency issues where changes in one Kotlin file caused compilation errors in other files (such as adapters, fragments, or navigation components). Some generated code also required adjustments to match the existing project structure.

Instead of copying the code directly, I reviewed the generated code, identified the root causes, and debugged the application manually. I then used AI as a debugging assistant to verify fixes, adapt the code to my project structure, and ensure all components worked together correctly.

This iterative approach helped maintain code quality while improving my understanding of the Android framework and the overall application architecture.

---

## Build Instructions

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle dependencies.
4. Run the application on an emulator or physical Android device.
---

## Author

Developed as part of the Clickretina Android Developer Take-Home Assessment.
