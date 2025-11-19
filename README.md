# Wizard World CLI Application

A Spring Shell-based command-line application that interacts with the
Wizard World API to retrieve **ingredients** and **elixirs** from the
magical world.\
You can select ingredients and view all related elixirs with detailed
information.

------------------------------------------------------------------------

## 🚀 Features

-   View all available magical ingredients.
-   Select **one or multiple ingredients**.
-   Retrieve all elixirs containing the selected ingredients.
-   Clean, readable log output.

------------------------------------------------------------------------

## 🧰 Requirements

Before running the application, ensure you have the following installed:

  -----------------------------------------------------------------------------
Requirement                              Version
  ---------------------------------------- ------------------------------------
**Java**                                 17 or newer

**Maven**                                3.8+

**Spring Boot**                          3.x

**Internet connection**                  Required to call Wizard World API

**Heroku API endpoint**                  Already included
(`wizard-world-api.herokuapp.com`)
  -----------------------------------------------------------------------------

------------------------------------------------------------------------

## 📦 How to Build and Run

### 1. **Clone the repository**

``` bash
git clone https://github.com/your-repo/wizard-world-cli.git
cd wizard-world-cli
```

### 2. **Build the application**

``` bash
mvn clean install
```

### 3. **Run the application**

You can run it using:

``` bash
mvn spring-boot:run
```

Or, if you prefer running the JAR:

``` bash
java -jar target/world-0.0.1-SNAPSHOT.jar
```

------------------------------------------------------------------------

## ⚙️ Configuration

You can configure the API endpoint in **application.yml**:

``` yaml
heroku:
  url:
    path: https://wizard-world-api.herokuapp.com
```

The `ApiClient` automatically reads this property when configured in
your `WebClientConfig`.

------------------------------------------------------------------------

## 🧙‍♂️ Using the CLI

After starting the app, type:

``` bash
help
```

You'll see all available commands --- the main one is:

### **🔮 select-ingredients**

Select one or more ingredients (comma-separated) to display all
available elixirs.

Example CLI usage:

    shell:> select-ingredients
    Choose one or more ingredients by number (comma-separated, e.g. 1,3,5):
    1: Newt Spleens
    2: Mandrake Root
    3: Granian Hair
    Enter choices:
    1,3
    Fetching elixirs for ingredients: Newt Spleens, Granian Hair
    ...

Output includes each elixir with:

-   Name\
-   Difficulty\
-   Effect\
-   Side Effects

Once you are finished with your inquiries, you can use "exit" on the command line to exit the application.

------------------------------------------------------------------------

## 🧪 Running Tests

The project includes **JUnit + Mockito** unit tests.

Run them with:

``` bash
mvn test
```

------------------------------------------------------------------------

## 📁 Project Structure

    src/
     ├── main/java/
     │    ├── config/            → WebClient API clients
     │    ├── mapper/            → DTO → domain mappers
     │    ├── command/           → Shell commands
     │    ├── util/              → Utilities (input reader, printing)
     │    └── service/           → WizardWorldApiService -> manages calls to external API
     ├── test/java/
     │    ├── mapper/            → Mapper tests
     │    ├── service/           → Service tests with Mockito
     │    └── shell/             → ShellCommand tests
     └── resources/application.yml

------------------------------------------------------------------------

## 📝 Notes

-   The Wizard World API is public and free to use.
-   This CLI relies on **reactive WebClient**, but the shell uses
    blocking `.block()` for simplicity.
-   You may extend this CLI with additional shell commands (e.g., search
    elixirs, print by difficulty, etc.).

------------------------------------------------------------------------

## ✨ Enjoy Exploring the Wizarding World!

If you need improvements, more documentation, or packaging into a
downloadable ZIP, just ask!
