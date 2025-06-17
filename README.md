🎥 TMBD
=============

🎯 **An Android application for searching movie information from MovieDB**

🎥 Demo
--------------
https://github.com/user-attachments/assets/6d258c48-9763-4ec6-a3ee-3f1321ce95d5

📁 Project Structure
--------------------

```
📦 tmdb/
├── 📂 androidApp/          # Main application module
├── 📂 build-logic/         # Plugin convention module
├── 📂 core/                # Core utilities and shared logic
│      └── 📂 bridge/       # Bridge module for Koin injection
│      └── 📂 common/       # Common module shared between modules
│      └── 📂 data/         # Data module which provides repositories, paging sources
│      └── 📂 database/     # Database module
│      └── 📂 domain/       # Domain module which provides use cases
│      └── 📂 model/        # Model module
│      └── 📂 network/      # Network module
│      └── 📂 testing/      # Testing module
│      └── 📂 ui/           # UI module, provide navigation host and UI elements
├── 📂 features/            # Features module
│      └── 📂 details/      # Movie details feature
│      └── 📂 movies/       # Movie searching and trending feature
├── 📂 iosApp/              # Main application module
└── 📂 gradle/              # Gradle wrapper and build scripts

```

🔧 Prerequisites
--------------

- 🧑‍💻 **Android Studio**: [Meerkat](https://developer.android.com/studio)
- 🛠️ **Android Gradle Plugin**: 8.10.1
- ⚙️ **Gradle**: 8.11.1
- 🔑 **MovieDB API key**: You will have to create a MovieDB account to get an API key.

✨ Key Features
--------------

- 🔎 Search Movies — Explore movies with real-time search using TMDB API.

- 🎬 Trending Movies — See what's trending today.

- 💾 Offline-First Architecture — Local caching with Room to allow browsing without immediate network access.

- ⚡ Pagination Support — Infinite scrolling for large movie search results.

- 📱 Modern Android UI — Built with Jetpack Compose.

- 📦 Multiplatform Ready — Business logic shared between Android and future iOS.

- 🔑 Secrets Handling — API keys securely handled with local properties.

✅ Extra Points
---------------

- 📱 **iOS Compatibility (KMP-Ready)**  
  The shared codebase (business logic, caching, repository) is designed to be fully **iOS-friendly**, using `expect/actual` where required and adhering to Kotlin Multiplatform best practices.  
  
- 🧪 **Unit Tests for Cache Validation**  
  - Unit tests have been written to ensure that **cache invalidation works as expected**, respecting the **24-hour stale time** logic, and that new data is fetched only when necessary. These tests verify both **valid cache scenarios** and **expired cache refreshes**.
  - Located at: **core/data/commonTest**

- 🖥️ **Simple UI Test for Compose Screens**  
  A **UI Test** is included for critical flows such as:
  - Displaying **Trending Movies** list.
  - Showing **Loading Indicators**.
  - Displaying **Error Messages** when failures occur.
  - **Search Query Input** interaction and validation.
  - Located at: **feature/movies/androidTest**

- ⚠️ **Error Message Handling (Offline/Failed Calls)**  
  - Gracefully handles **offline mode** or **network failures** with appropriate user-facing error messages.
  - Differentiates between **network errors**, **server errors**, and **unexpected exceptions**.

🏗️ How to Build
--------------

1. Clone the repository:
   ```bash
   git clone https://github.com/ptv1811/pexels.git
   ```

2. Create a `secrets.properties` file at root directory and fill in these options:
    ```
    MOVIE_DB_API_TOKEN=<API key>
    ```

3. Sync the build project as usual.

📦APK File
--------------

In case you can't build the project, \
Check out the [Release](https://github.com/vanxioussss/TMDB/releases/tag/v1.0.0) section.

🛠️ Tech Stack
--------------

- 🧑‍💻 **Language**: ![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue?logo=kotlin&logoColor=white)

- 🏗️ **Architecture**: Clean architecture - Single Activity - MVVM Design pattern - Multi-moduled

- 📦 **Libraries**:

    - **Kotlin MultiPlatform**: [KMP](https://kotlinlang.org/lp/multiplatform/)

    - **Jetpack Compose**: [Jetpack Compose](https://developer.android.com/compose)

    - **Dependency Injection**: [Koin](https://insert-koin.io/)

    - **Networking**: [Ktor](https://ktor.io/)

    - **Database**: [Room](https://developer.android.com/jetpack/androidx/releases/room)

    - **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

    - **Reactive Streams**: [Flow](https://kotlinlang.org/docs/flow.html)

    - **Pagingination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

    - **Unit testing**: [JUnit](https://junit.org/junit4/), [Mockk](https://mockk.io/), [Turbine](https://code.cash.app/flow-testing-with-turbine),
      etc

🧠 Decision Making
-------------
This section outlines the rationale behind key architectural and UX decisions made during the
development of this application.

- 🏗️ **Architecture**:

  **Clean Architecture + MVVM** \
  Ensures clear separation of concerns across layers: UI, Domain, and Data. Each layer has a
  well-defined responsibility, improving testability and maintainability.

  **Modularization** \
  Even though the app requirements are simple, the project is modularized to support scalability.
  This allows different features to be developed in isolation without conflicts, making the codebase
  future-proof and team-friendly.

  **Single Activity with Navigation Compose** \
  The app uses a single-activity architecture, with each feature represented by a Fragment. This
  centralizes navigation and simplifies lifecycle handling across screens.

- 🗄️**Cache Validation Strategy**:

    **Why Cache?** \
    Reduces redundant network calls, improves loading speed, enhances offline support, and minimizes TMDB API quota usage.

    **Cache Expiry (Validation Time)** \
    Cached data is considered valid for 24 hours by default. After that, a fresh fetch from the API is triggered when the user accesses that content again.

    **Why 24 Hours?** \

    Trending and popular movies don’t change frequently on TMDB’s end.

    Balances performance (reduces unnecessary network usage) with data freshness.

    Customizable in the future — individual features (e.g., trending, search) can have different expiry strategies.

- 🎨 **UI/UX Decision**:

  **Real-Time Image Search** \
  As the user types, search results update in real time, creating an engaging and dynamic
  experience. Input is debounced to prevent excessive network requests.

🚀 Future Improvements
---------------
Some planned or possible enhancements:

**Improve image loading performance** \
Currently the time to load each image is still high. Also when we scroll far away, when coming back to start of the image list, some images is already been cleared due to memory issue.

**Improve reusability** \
Some components can be implemented in a generic way to reuse between different modules.

**Support Build variants** \
Due to time constraint, I don't have enough time to support build variance but it will be a good
improvements in the future.

**Support for Light/Dark Theme**  
Provide full support for dark mode following Material guidelines.
