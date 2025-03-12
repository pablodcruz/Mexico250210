## 1. Overview of the Todo Application

In this tutorial, we’ll build a **basic command-line “Todo” system**. The main features:

- **Add a Todo item** (with a description).
- **List all Todo items**.
- **Mark a Todo as done**.

---

## 2. What Is TDD?

**Test-Driven Development** is an agile software development approach where you:

1. **Write a failing test** that describes a small piece of functionality you want.  
2. **Implement** the minimal code required to make that test pass.  
3. **Refactor** your code (improve structure, remove duplication) while keeping tests green.

This cycle is often referred to as **Red-Green-Refactor**:

- **Red**: Write a test that fails (no implementation yet).  
- **Green**: Write just enough code to pass the test.  
- **Refactor**: Clean up the code without breaking existing tests.

---

## 3. Project Setup

**Recommended Tools**:
- **JDK 8 or higher** (We will highlight Java 8 features.)
- **Maven** (or Gradle) for dependency management.
- **JUnit 5** (we’ll rely on the `org.junit.jupiter` packages).
- **Logback** (classic + core) for logging.

### Sample `pom.xml` (Maven)
```xml
<project ...>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>todo-app</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <dependencies>
    <!-- JUnit 5 -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.9.2</version>
      <scope>test</scope>
    </dependency>

    <!-- Logback -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.4.11</version>
    </dependency>
    
    <!-- SLF4J (API used by Logback) -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>2.0.5</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Surefire plugin to run JUnit tests -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0-M8</version>
      </plugin>
    </plugins>
  </build>
</project>
```

**Note**: Adjust versions as needed. Once your `pom.xml` is set up, run:
```bash
mvn clean install
```
to confirm it builds and downloads necessary dependencies.

---

## 4. Introduction to JUnit

JUnit is a popular testing framework. In **JUnit 5**, key annotations include:

- **`@Test`** – Marks a method as a test.  
- **`@BeforeEach`** – Runs before each test method (setup code).  
- **`@AfterEach`** – Runs after each test method (cleanup).  
- **`@BeforeAll`** and **`@AfterAll`** – Run once before/after all tests in a class (usually for expensive setup or teardown).  

JUnit 5 also provides **assertions** like `Assertions.assertEquals(expected, actual)`.

---

## 5. First TDD Cycle: Implementing Todo Creation

### 5.1 Write a Failing Test (RED)

Create a test class in `src/test/java/com/example/todo/TodoServiceTest.java`:

```java
package com.example.todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoServiceTest {

    @Test
    void shouldAddTodoItem() {
        // Arrange
        TodoService service = new TodoService();

        // Act
        service.addTodo("Buy milk");
        
        // Assert
        assertEquals(1, service.getAllTodos().size(), "Should have 1 todo after adding");
        assertEquals("Buy milk", service.getAllTodos().get(0).getDescription());
    }
}
```

- We expect to call `addTodo("Buy milk")` and see the new item in `getAllTodos()`.

### 5.2 Make It Pass (GREEN)

Create `TodoService` and `Todo` classes in `src/main/java/com/example/todo`:

```java
package com.example.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoService {
    private List<Todo> todos = new ArrayList<>();

    public void addTodo(String description) {
        todos.add(new Todo(description));
    }

    public List<Todo> getAllTodos() {
        return todos;
    }
}
```

```java
package com.example.todo;

public class Todo {
    private String description;
    private boolean done;

    public Todo(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }
}
```

Now run the test again:
```bash
mvn test
```
The test should pass, turning **red** to **green**.

### 5.3 Refactor
If we see any duplication or messy code, we can refactor. Right now, it’s fairly straightforward—no major refactoring needed.

---

## 6. JUnit Annotations & More Tests

Let’s demonstrate a few more JUnit features and create an additional method to **mark a Todo as done**.

**Add a second test** to `TodoServiceTest`:

```java
@Test
void shouldMarkTodoAsDone() {
    // Arrange
    TodoService service = new TodoService();
    service.addTodo("Clean room");

    // Act
    service.markTodoAsDone(0);  // We'll add this method soon

    // Assert
    assertTrue(service.getAllTodos().get(0).isDone(), "Todo should be marked as done");
}
```

Try to compile and run tests now: it **fails** because `markTodoAsDone(int index)` does not exist yet.

**Implement `markTodoAsDone`** in `TodoService`:

```java
public void markTodoAsDone(int index) {
    if (index >= 0 && index < todos.size()) {
        todos.get(index).markDone();
    }
}
```

Re-run your tests: they should pass.

### `@BeforeEach`, `@AfterEach` Example

If we had **repetitive setup** (e.g., creating a `TodoService` for every test), we can do:

```java
private TodoService service;

@BeforeEach
void initService() {
    service = new TodoService();
}

@Test
void shouldAddTodoItem() {
    service.addTodo("Buy milk");
    ...
}
```

This way, each test starts with a **fresh instance** of `TodoService`. 

---

## 7. Introduction to Logback

**Logback** is a popular logging framework. In modern Java apps, we often use **SLF4J** as a common logging API, with Logback as the backend.

### Basic Setup

1. **Add Logback to your `pom.xml`** (done earlier).  
2. **Create a `logback.xml`** (optional) in `src/main/resources` to configure logging:

```xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="CONSOLE" />
    </root>
</configuration>
```

- This config outputs logs to the console with a **DEBUG** level by default.

### Using Logback in Code

```java
package com.example.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoService {
    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
    ...
    
    public void addTodo(String description) {
        logger.debug("Adding a new Todo: {}", description);
        todos.add(new Todo(description));
    }
    
    public void markTodoAsDone(int index) {
        if (index >= 0 && index < todos.size()) {
            logger.info("Marking Todo as done at index: {}", index);
            todos.get(index).markDone();
        } else {
            logger.warn("Attempted to mark invalid index: {}", index);
        }
    }
}
```

When you run your application or tests, you’ll see **Logback** messages in the console.

---

## 8. Logging Levels in Logback

Standard levels (lowest to highest priority):

1. **TRACE** – Very fine‐grained, usually for debugging.  
2. **DEBUG** – Detailed debug info.  
3. **INFO** – General info about normal operations.  
4. **WARN** – Something unexpected, but not necessarily an error.  
5. **ERROR** – A serious issue, likely preventing normal app flow.

You can adjust the root level or configure per‐package logging in `logback.xml`:

```xml
<root level="info">
    <appender-ref ref="CONSOLE" />
</root>

<logger name="com.example.todo" level="debug" />
```
- This means overall logging is at **INFO**, but for classes in `com.example.todo`, we allow **DEBUG** level messages.

---

## 9. Introduction to Java 8 Features

Java 8 introduced major changes, notably:

- **Lambdas** (anonymous functions): `(parameters) -> { body }`.
- **Functional Interfaces** (interfaces with a single abstract method, e.g. `Runnable`, `Comparator`).
- **Streams API** (for processing collections in a functional style).
- **Optional** (to handle null‐safe operations).

---

## 10. Lambdas & Functional Interfaces Demo

### 10.1 Lambdas in Action

A quick **lambda** example using a built-in functional interface **`Runnable`**:

```java
Runnable r = () -> System.out.println("Hello from a lambda!");
r.run();
```

Or using the **`Comparator`** functional interface:

```java
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
names.sort((a, b) -> a.compareTo(b));
System.out.println(names); // [Alice, Bob, Charlie]
```

### 10.2 Creating a Custom Functional Interface

```java
@FunctionalInterface
public interface TodoFilter {
    boolean match(Todo todo);
}
```

Now we can define lambdas implementing this interface:

```java
TodoFilter isDoneFilter = (todo) -> todo.isDone();
TodoFilter containsMilk = (todo) -> todo.getDescription().contains("milk");
```

---

## 11. Adding Logging + Java 8 Features in the Todo App

Let’s enhance our `TodoService` to **filter** completed Todos using **streams** and a custom function:

```java
public List<Todo> getCompletedTodos() {
    return todos.stream()
            .filter(Todo::isDone)
            .collect(Collectors.toList());
}
```
- Here, `Todo::isDone` is a **method reference**, another Java 8 feature that’s a shorthand for `todo -> todo.isDone()`.

We can also create a more generic filter:

```java
public List<Todo> filterTodos(TodoFilter filter) {
    // Using our custom functional interface
    logger.debug("Filtering todos with a custom predicate");
    return todos.stream()
                .filter(filter::match)
                .collect(Collectors.toList());
}
```

**Demo usage**:
```java
TodoFilter doneFilter = Todo::isDone;
List<Todo> doneTodos = service.filterTodos(doneFilter);
logger.info("Number of completed todos: {}", doneTodos.size());
```

---

## 12. Summary & Next Steps

- **TDD**: We started with a **failing test**, implemented minimal code, and refactored—repeatedly.  
- **JUnit**: You learned how to write tests using `@Test`, how to do setup with `@BeforeEach`, and how to assert outcomes.  
- **Logback**: Set up a logging framework, used different **logging levels**.  
- **Java 8 Features**:
  - **Lambdas** simplify code when using functional interfaces.  
  - **Streams** provide a functional way to filter and map collections.  
  - **Method References** are syntactic sugar for common lambda patterns.
