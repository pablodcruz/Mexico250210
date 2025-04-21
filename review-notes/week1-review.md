# Week 1 Review
# Fullstack Overview
- Full stack refers to the entire depth of a system
    - Frontend + backend
- Frontend: Refers to the visible part of the application on browsers / mobile devices, etc.
    - Built using HTML, CSS, JS
- Backend: refers to servers, databases, and internal architecture that drives the application
    - Customer or end user usually never directly interacts with this realm directly
- Fullstack developers are versed in both disciplines
    - Versatile enough to work on both frontend and backend
    - Organizations value fullstack developers since it's often necessary to have team members who understand how the entire system works together

# Operating Systems
- Common OSes
    - Windows
    - Mac OS X
    - Linux
- Kernel: program at the core of an OS
    - Has full control over hardware resources
    - Performs arbitration between different processes that are running
    - Provides abstraction between applications and the hardware
- Process management
    - Process: an application in execution
        - Executed sequentially according to code instructions for that program
        - When a program is started, it becomes a process
        - 5 different states: Start, Ready, Running, Waiting, Terminated
- Threads
    - A thread is a flow of execution within a process
    - A thread keeps track of all the instructions that need to be executed next
    - Also contains all of the current variables being worked with and the execution history (stack memory)
- Scheduling
    - OS process manager is responsible for scheduling when each process gets execution time on the CPU
    - OS thread scheduler is responsible for scheduling when each thread within a process gets executed (for multi-threaded applications)
- Memory Management
    - OS manages RAM
    - Processes are allocates space on RAM
    - OS also handles file IO (writing and reading from a disk)
- Unix Style commands
    - Unix is an old operating system on which many modern systems are built
        - Linux is inspired by Unix (although they're not the same thing)
    - Commands
        - pwd
            - current directory
        - ls
            - list files + directories
        - cd
            - change directory
        - mkdir
            - make new directory
        - touch
            - make new blank file
        - cat
            - output contents of file
        - echo
            - print to console arguments passed in
        - grep
            - search for string in file/string input
        - diff
            - compare differences between files
        - rm
            - remove file or directory
        - cp
            - copy file or directory
        - mv
            - move file or directory

# SDLC
- SDLC
    - Software development lifecycle
    - 6 core phases
        - Requirements phase
            - Defining what an application is supposed to do
            - Defining what resources are required for the project
        - Design phase
            - Modeling how an application will be put together
            - Architecture, UI, platform, programming paradigms, networking, security, databases
        - Implementation phase
            - Writing of code
            - Large project may be tackled by multiple teams
            - Version control system often used such as **Git**
        - Testing phase
            - Making sure an application is working correctly according to the requirements
            - Manual OR automated
            - Testing helps reduce # of bugs/glitches in application
        - Deployment phase
            - Application is made available to users
            - Manual or automated
            - DevOps is a modern process that is helpful for automation of deployment
            - Deployment can be complex if there are many different systems and/or distributed architecture, etc.
        - Maintenance phase
            - Users may discover bugs that weren't found during testing phase that the development team must fix
            - Additional features may be planned for future releases based on customer suggestion, etc.
- Waterfall
    - Classic method of development
    - Progress goes sequentially from one phase to another
        - Cannot go back to a previous phase
    - Phases are fully completed before moving to the next
    - Advantages
        - Simple model
        - Easy to implement
        - Phases are clearly defined
        - Works well in short projects
    - Disadvantages
        - Not ideal for complex projects
        - Time may be wasted since each phase needs to be completed before moving to the next
        - Software product is not available for testing until later phases, which means bugs may not be caught until late in the development process
- Agile
    - Revolves around putting customer needs first
    - Focuses strongly on user experience and customer input
        - Development is highly responsive to customer feedback
        - Software is released in iterations to respond to a changing market
        - Requires a strong team with excellent communication
    - 4 core values of Agile
        - Individuals and interactions over processes and tools
        - Working software over comprehensive documentation
        - Customer collaboration over contract negotiation
        - Responding to change over following a plan
    - Advantages
        - Provides a responsive approach to development of software
        - Enhances flexiblity / agility
    - Disadvantages
        - If not implemented properly, inadequate documentation can hinder knowledge transfer to new members on the development team
        - Having a less rigid model compared to waterfall can lead to disorganization if team members are not good at self-organization
    - Agile user stories
        - Format
            - `As a <role>`
            - `I want <goal>`
            - `So that <benefit>`
        - Acceptance Criteria
            - Conditions that must be met for a user story to be considered complete in terms of functionality
        - Example user story:
            - As an account manager
            - I want a sales report of my account sent to my inbox daily
            - So that I can monitor the sales progress of my customer portfolio
            - Acceptance criteria:
                - The report is sent to my inbox daily
                - The report contains the following sales details: ...
                - The report is in csv format
        - Requirements are written from the perspective of the user to keep the development team in line with customer needs and wants
    - Agile standup meeting
        - 15 minute meeting
        - Occurs daily in the morning
        - Each team member talks about
            - What they worked on the previous day
            - What they will work on today
            - What issues/blockers/problems they are running into

# Git
- Version control system
    - Version control: the practice of tracking and managing code changes in a project
        - Save changes to project (creating new versions)
        - Revert to old versions of project
    - Version control systems are tools that facilitate the process of version control
    - Git is the most popular VCS
- Repository types
    - Local repository
        - Local copy on your computer
    - Remote repository
        - Copy that exists on a repository hosting website (such as Github, Gitlab, BitBucket, etc.)
- File states
    - Modified
        - A file has been changed, added, or deleted but the modification has not been staged
    - Staged
        - A modified file is ready to be committed
    - Committed
        - Staged files have been saved to the local repository
- Git commands
    - `git init` to create a new local repository
    - `git remote add` to link the local repository with a remote repository
    - `git clone` to create a local repository that is a copy of an already existing remote repository (ex. a repository that already exists on Github)
    - `git status` to view status of the repository and to see modified files and/or files ready to be committed
    - `git commit` to commit changes to local repository
    - `git log` to view newly created commit
    - `git push` to push commit to remote repository

## **6.2 Branching and Merging**

**Branching:**
- **Create a New Branch:**
  ```bash
  git branch <branch-name>
  git checkout <branch-name>
  ```
  - Alternatively, create and switch in one command:
    ```bash
    git checkout -b <branch-name>
    ```
  
- **List Branches:**
  ```bash
  git branch
  ```

**Merging:**
- **Merge a Branch into the Current Branch:**
  ```bash
  git merge <branch-name>
  ```
  
- **Fast-Forward vs. Three-Way Merge:**
  - **Fast-Forward:** Occurs when the current branch has no unique commits.
  - **Three-Way Merge:** Combines changes from divergent branches.

**Best Practices:**
- **Use Feature Branches:** Isolate development of new features to specific branches.
- **Regularly Merge Changes:** Keep branches up-to-date to minimize conflicts.
- **Descriptive Branch Names:** Use clear and consistent naming conventions (e.g., `feature/login`, `bugfix/header-error`).

### **6.3 Conflict Resolution**

**Understanding Merge Conflicts:**
- Occur when changes in different branches affect the same part of a file and cannot be automatically reconciled by Git.

**Resolving Conflicts:**
1. **Identify Conflicted Files:**
   ```bash
   git status
   ```
   
2. **Open Conflicted Files:**
   - Look for conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`).
   
3. **Manually Resolve Conflicts:**
   - Decide which changes to keep or combine.
   
4. **Mark Conflicts as Resolved:**
   ```bash
   git add <file-name>
   ```
   
5. **Commit the Merge:**
   ```bash
   git commit
   ```

**Best Practices:**
- **Communicate with Team Members:** Understand the intent behind conflicting changes.
- **Use Merge Tools:** Utilize GUI-based tools like `meld`, `kdiff3`, or IDE-integrated tools for easier conflict resolution.
- **Test After Merging:** Ensure that resolved conflicts do not introduce bugs or issues.

### **6.4 .gitignore and Repository Management**

**.gitignore File:**
- **Purpose:** Specifies intentionally untracked files that Git should ignore.
- **Common Uses:**
  - Ignoring build artifacts (e.g., `dist/`, `build/`).
  - Ignoring sensitive information (e.g., `.env` files).
  - Ignoring system-specific files (e.g., `.DS_Store`, `Thumbs.db`).

**Example `.gitignore`:**
```
    # Node.js
    node_modules/
    dist/

    # Python
    __pycache__/
    *.pyc

    # Environment Variables
    .env

    # OS Files
    .DS_Store
    Thumbs.db
```

**Best Practices:**
- **Define Clear Rules:** Ensure that necessary files are tracked while unnecessary or sensitive files are ignored.
- **Update Regularly:** Modify `.gitignore` as project requirements evolve.
- **Global Gitignore:** Configure a global `.gitignore` for files that should be ignored across all repositories on your machine.

### **6.5 Git vs GitHub**

**Git:**
- **Definition:** A distributed version control system that tracks changes in source code during software development.
- **Key Features:**
  - **Local Repositories:** Each developer has a full copy of the repository history.
  - **Branching and Merging:** Efficient handling of branches for feature development.
  - **Commit History:** Detailed records of changes over time.

**GitHub:**
- **Definition:** A cloud-based platform that hosts Git repositories and provides collaboration features.
- **Key Features:**
  - **Remote Repositories:** Centralized storage for Git repositories.
  - **Pull Requests:** Facilitate code reviews and discussions before merging changes.
  - **Issue Tracking:** Manage bugs, enhancements, and tasks.
  - **CI/CD Integration:** Integrate with tools like Jenkins, Travis CI, or GitHub Actions for automated workflows.
  - **Collaboration Tools:** Team management, project boards, and wikis.

**Key Differences:**
- **Functionality:** Git is the version control tool, while GitHub is a platform for hosting Git repositories with additional collaboration features.
- **Usage:** Git can be used locally without GitHub, but GitHub relies on Git for version control.

# Java Fundamentals
- High level programming language
- Features
    - Highly object oriented
    - Strongly typed: values are not implicitly converted between types
    - Strictly typed: variables are declared with a type that cannot be changed
- Benefits
    - Write once run anywhere
    - Widely used w/ extensive 3rd party libraries + frameworks that are open source
    - Automatic memory management
- Disadvantages
    - Can be inefficient w/ memory management at times
    - All users must have a JRE installed on the computer to run Java programs
    - Forces OOP to be used
    - Takes time to compile code before it can be run
    - Licensing
- JDK v. JRE v. JVM
    - JDK: Java Development Kit
        - Contains a **compiler** that turns source code into byte code
        - DevTools, debugger, documentation tools, etc.
        - JRE
    - JRE: Java Runtime Environment
        - Built-in libraries
            - ex. String class, System class, Math class, collections API, etc.
        - JVM
    - JVM: Java Virtual Machine
        - Runs byte code and issues instructions to the machine on how to run the program
            - Contains a JIT (Just in Time) compiler that reads byte code line by line and issues low-level commands to the computer while program is running
- Primitive Types
    - 8 primitives
        - byte
            - 8 bit integer values
        - short
            - 16 bit integer values
        - char
            - 16 bit unicode characters
        - int
            - 32 bit integer values
        - long
            - 64 bit integer values
            - "L"
        - float
            - 32 bit floating point values
            - "f"
        - double
            - 64 bit floating point values
- Control Flow
    - If statement: used to execute a block of code if true
        - else if: is another if statement chained to a previous that will run if the previous was false
        - else: runs if if, and all else ifs are false
    - While loop
        - runs a block of code repeatedly while a specified condition is true
    - Do-while loop
        - runs a block of code first, and then evaluates a condition to see if it's true before looping again 
        - Do-while loops are guaranteed to run at least once
    - For loop
        - A loop with 3 parts
        - Declaration block: create a "counter" variable
        - Conditional block: runs loop if condition is true
        - Increment block: used to change the counter variable's value (could also be used for decrementing)
    - Switch statements
        - Use a specified variable and match its value against various "cases"
    - Break and continue
        - Used to exit a loop without further evaluation
        - Continue used to stop execution of current block and proceed to the next block
- Boolean expressions
    - `==` Check equality of two values
        - For primitive variables, checks whether values are equal
        - For reference variables, checks whether the two variables are pointing to the same object
    - `>` Greater than
    - `<` Less than
    - `>=` Greater than or equal to
    - `<=` Less than or equal to
    - `!` Reverse value of boolean
    - `&&` AND
        - true && true -> true
        - everything else is false
    - `||` OR
        - false || false -> false
        - everything else is true
- Strings
    - Are objects in Java, not primitives
    - Sequences of characters
    - Immutable (when a String object is created in memory, its characters cannot be changed)
    - Double quotes represent string literals
    - Common string methods
        - `.equals(Object o)`
        - `.charAt(int index)`
        - `.concat(String s)`
        - `.contains(CharSequence cs)`
        - `.toCharArray()`
        - `.substring(x, y)`
        - `.length()`
        - `.split(regex)`
        - `.toLowerCase()`
        - `.toUpperCase()`
        - `.trim()`
- Arrays
    - A contiguous block of memory that stores a group of sequentially stored elements of the same type
    - Fixed in size: once an array is created, its size cannot be changed
    - Arrays can be used for both primitive and object types
    - Ways to create an array
        - `int[] myInts = new int[5];`
            - Creates an array of size 5
            - `[0, 0, 0, 0, 0]`
        - `int[] myInts = new int[] {10, 20, 30, 40, 50};`
            - Creates an array of size 5
            - Pre-populates with values
            - `[10, 20, 30, 40, 50]`
        - `int[] myInts = {10, 20, 30, 40, 50}`
            - Same as above, but without using `new int[]`
- Classes and Objects
    - Classes are blueprints for creating objects in memory
        - Classes are named with CamelCase convention
        - Fields and methods are named with lowercase camelCase
    - Objects represent real world entities / things
        - Objects possess properties (fields) + behaviors (methods)
        - An object is created using a constructor defined in a class
            - `new` keyword is used
        - Objects can be referred to via reference variables
        - `ex. Person p = new Person("Pablo", "Trainer");`
- Object class
    - Ultimate parent class of all objects
    - Every object in Java inherits the fields/methods defined in the Object class
    - If a class does not explicitly inherit another class using the `extends` keyword, it will automatically directly inherit the Object class
    - Important methods
        - toString(): returns a String that represents the object, which will also be printed out if System.out.println is used to print the object
            - `className@hashCode` by default
        - equals(): compares object to another to determine equality
            - `==` by default
            - Often overridden to compare properties of two objects
        - hashCode(): creates a hash identifier for an object that is useful for collections such as HashSet or HashMap
- Classes v. Objects
    - Classes are blueprints for objects
        - A class is declared once
    - Objects are instances of a class
        - Multiple (as many as required) objects can be created from a single class
- Scanner class
    - Used to create Strings from an external source
    - Often used to read from text files or to accept input from the console
    - Console is represented by System.in (standard input)
    - `Scanner sc = new Scanner(System.in)`
- Methods
    - Functions attached to classes/objects that can be called at different times to run as necessary
    - Methods have
        - inputs (parameters/arguments)
        - outputs (returned value)
        - code block (contains logic that should be executed within the method)
    - Method signature
        - `<access modifier> <optional non-access modifier(s)> <return type> methodName(<optional parameter(s)>) { }`
- Packages
    - A way to organize classes
    - Related classes are typically placed together in a package
    - Naming convention of packages is "reverse domain naming"
        - `com.revature`
        - lowercase
    - Subpackages are packages inside of another package
        - `com.revature.model`
        - However, subpackages can be seen/treated as totally different packages
    - Whenever a class in a different package needs to utilize a class in another package, `importing` is needed
        - `import` keyword is used to make another class from a different package accessible inside of the class that needs it
- Variable Scopes
    - Where a variable exists in memory
    - 4 variable scopes in Java
        - Static scope
            - Also known as global scope
            - A field with the static non-access modifier applied to it is a static scope variable
            - A static field belongs to the class itself, not objects of the class
        - Instance scope
            - A variable scoped to individual objects
                - Each object has its own independent value for that variable
            - A non-static field is an instance variable
        - Method scope
            - A variable that is scoped to a method
            - Variables declared in a method are method scoped variables
            - They are destroyed when the method is done executing
        - Block scope
            - A variable that is scoped to a block of code in a method
            - When the block of code is done executing, the variable is destroyed
- Throwables (Exceptions and Errors)
    - Objects that can be thrown and caught
        - Both Exceptions and Errors can be thrown and caught, but it's not recommended to catch Errors
        - Exceptions: something that is reasonably expected to occur and can be recovered from through exception handling (catching the exception)
        - Errors: something that is fatal to the application working properly and cannot be recovered from
            - ex. OutOfMemoryError, StackOverflowError
    - Throwable hierarchy
        - Throwable class
            - Error class
            - Exception class
                - Classes that extend Exception directly (checked exceptions)
                - RuntimeException
                    - Classes that extend RuntimeException (unchecked exceptions)
    - Checked v. Unchecked exceptions
        - Checked exceptions require mandatory handling/declaring
            - Checked exceptions are exceptions that directly extend the Exception class and NOT RuntimeException
            - Declaring an exception: `throws` keyword in the method signature is used to indicate that the method will pass responsibility of handling the checked exception to another method that invokes this method
            - Handling an exception: try-catch
        - Unchecked exceptions are not required to be handled or declared
            - However, it is still recommended to handle unchecked exceptions
            - If you do not, then it will result in the program crashing
        - Handling exceptions
            - try block: risky code is placed in a try block
            - catch block: if an exception occurs in the try block, the corresponding catch block will be executed
                - There can be multiple catch blocks per try block to specify all of the different exceptions that may occur
            - finally block: always runs at the end regardless of whether the try block finishes successfully without an exception OR if an exception occurs and the catch block must run
    - Creating custom exceptions
        - To create a checked exception
            - Create a class that extends Exception
        - To create an unchecked exception
            - Create a class that extends RuntimeException
- Reading the stack trace
    - The stack trace is an error message that appears whenever an exception that is not being handled crashes the program (or exceptionObject.printStackTrace() is used)
    - The structure of the stack trace looks like the call stack at the moment the exception occurred
        - The method at the top of the call stack is the method that threw the exception
        - The exception will propagate down the call stack until it reaches a method that has a try-catch block for that exception
- Access modifiers
    - From most restrictive to least:
        - private: can only be accessible within the same class
        - default: can only be accessible within the same package
        - protected: within the same package + subclasses in any package
        - public: anywhere
- Non-access modifiers
    - static
    - abstract
    - final
        - final method: cannot be overridden
        - final class: cannot be extended
        - final variable: value cannot be changed
- Generics
    - Specify types as parameters
    - Allows us to avoid type-safety issues (ClassCastException)
    - Enforces type checking for methods
    - Commonly used with collections to specify type stored in a collection

# Memory Managemnet
- Call Stack
    - Keeps track of execution in a program
    - When a method is executed, it is placed on top of the call stack
    - When the method is done executing, it is popped off the call stack
    - All methods that are in the call stack are in the process of execution, with the method at the top of the call stack the one that is actively being executed line by line
    - Each method is a "stack frame"
    - Stack frame contains all of the method scoped / block scoped variables currently
- Heap
    - Contains objects
    - Reference variables (such as method/block scoped variables) in the call stack contain the memory address location of an object in the heap
    - A special location in the heap called the **string pool** is used to store string literal objects
        - A string literal may be used multiple times in an application such as `"hello"`, but only one object will exist in the string pool for that
- Garbage Collection
    - De-allocation of memory that objects occupy is performed by the garbage collector
    - When an object no longer has any references, the garbage collector will destroy the object and free up the memory the object was occupying
    - Garbage collector is a piece of software that runs in the background on a periodic basis
    - The JVM schedules when the garbage collector runs
        - We do not have control over this process
        - It is not possible to force garbage collection to run
    - We can request the JVM to run the garbage collector
        - `System.gc()`
        - `System.runFinalize()`
        - `Runtime.getRuntime().gc()`

# Pillars of OOP
- Inheritance
    - Enables a class to gain the features (fields + methods) of another class and expand upon those if necessary
    - Establishes an IS-A relationship
    - Inheritance helps to minimize redundancies in code since related classes can have a parent/super class that contains shared characteristics
    - `extends` keyword is used to indicate the parent class of a class being defined
    - In Java, only a single class can be inherited. However, the parent class can also inherit from a class, creating a hierarchy
    - Terminologies
        - Subclass (child class)
        - Superclass (parent class)
- Polymorphism
    - Means "many forms"
    - Methods can take on different forms via method overloading + overriding
    - Method overriding
        - Where a method in the parent class is redefined in a child class
        - Used to provide a specific implementation of a method already provided in the parent class
        - Overriding is known as runtime polymorphism
            - While the program is running, the JVM figures out which method to use
        - Overriding rules
            - Child class method has the same name as the parent class method
            - Same parameters
            - Same return type (or covariant type)
            - Same access modifiers (or more accessible)
    - Method overloading
        - Where two or more methods in the same class have the same name
            - Overloading helps to simplify the number of method names
            - Ex. in Java
                - System.out.println(...)
                - println(int x)
                - println(double x)
                - println(String str)
                - println(char c)
                - ...
        - Overloading is known as compile-time polymorphism
        - Rules
            - Methods are in same class
            - Same name
            - Different number of parameters and/or type of parameters
- Abstraction
    - "Hiding implementation details"
    - "Taking something concrete and turning it into an idea"
        - Ex. Dog and Cat are both "Animals"
        - Ex. CombustionCar and ElectricCar are both Car
        - Ex. Circle and Triangle are both Shape
    - Abstraction is accomplished through abstract classes and interfaces
    - Abstract class
        - Can contain abstract methods
        - Also can have non-abstract methods
        - Cannot be instantiated
    - Interfaces
        - Can only have abstract methods (until Java 8 introduced the default keyword)
            - default keyword allows for the defining of non-abstract methods in interfaces
        - Cannot instantiate an interface
        - fields in an interface are public static final implicitly
        - methods in an interface are public abstract implicitly
    - A class can extend only one class (whether abstract or concrete)
    - A class can implement as many interfaces as desired
- Encapsulation
    - Enclosing data and methods into a single unit (class)
        - Fields and methods interact with each other within an object created from a class
        - Methods can access and/or modify the value of the fields
    - Data-hiding is a best practice for encapsulation
        - Used to prevent other developers from modifying values in ways that are not intended by the original creator of a class
            - ex. age should only be incremented, not decreased for a Person object
        - Use access modifiers (private, default, protected, public) to restrict access
        - Typically, fields should be restricted as much as reasonably possible
    - Getter/setter methods
        - Getter methods: used to access a property via a method
        - Setter methods: used to set a property via a method

# Maven
- Dependency manager and build tool
    - `pom.xml` file
        - Contains the settings for the Maven project
        - Dependencies utilized as listed in the pom.xml and automatically downloaded
        - Plugins can also be configured when using Maven to build the project
    - Dependency manager: when writing Java applications, many external libraries may be used
        - Maven helps to automatically download + manage those dependencies
    - Build tool: when writing Java applcations, before deploying the application, the project must be built
        - build: the process of creating a build artifact
        - build artifact: a deployable file representing the application such as a .jar file
    - Directory structure:
        - `src/main/java`: where project source code should go
        - `src/main/resources`: where any custom configuration files and other resources go
        - `src/test/java`: where automated test code goes
        - `src/test/resources`: where any resources that support the test code go
    
# JUnit
- A unit testing framework for Java
    - Can be included into our project via Maven (putting the dependencies into the pom.xml file)
    - A way to help us structure test cases
        - `@Test` annotation
            - Method is treated as a test case
        - Annotations
            - @Test
            - @BeforeAll
            - @AfterAll
            - @BeforeEach
            - @AfterEach
        - Assertions
            - Give JUnit the ability to fail a test if expected does not match equal
            - Assertions.assertEquals(expected, actual)
            - Assertions.assertTrue(boolean)
            - Assertions.assertFalse(boolean)
            - Assertions.assertArrayEquals([] expected, [] actual)
            - Assertions.assertNull(...)
            - Assertions.fail()

# Collections API
- Collection hierarchy
    - Iterable interface
        - Collection interface
            - List interface
                - ArrayList class
                - LinkedList class
                - Vector class
            - Queue interface
                - LinkedList class
                - PriorityQueue class
            - Set interface
                - HashSet class
                - TreeSet class
    - Map interface (not part of the Collection interface hierarchy)
        - HashTable class
        - HashMap class
        - TreeMap class
- Collections class
    - A utility class that contains static methods that are useful for operating on collections
    - Collections.sort(myList)
    - Collections.sort(myList, Collections.reverseOrder())
- Java's implementation of common data structures
    - List
        - Data stored in sequential order and accessible by an index
        - Size can be expanded unlike an Array
        - Two common types
            - ArrayList
                - Uses an array behind the scenes
                - When the array runs out of space, a new array is created that is 1.5x the size of the old array
                - Elements from old array are copied over
            - LinkedList
                - Uses node objects linked to each other
                - Each node stores a single value and links to the previous and next node
        - Primary operations
            - .add(E element)
            - .add(int index, E element)
            - .set(int index, E element)
            - .get(int index)
            - .remove(int index)
            - .remove(E element)
            - .size()
            - .clear()
            - .contains(E element)
    - Set
        - Collection of unique values
            - No elements have the same value
        - No indexing (elements cannot be retrieve via an index)
        - Primary operations
            - .add(E element)
            - .contains(E element)
            - .remove(E element)
            - .size()
            - .clear()
        - HashSet
            - No inherent ordering
        - TreeSet
            - Has an inherent ordering based on smallest to largest when iterating over the TreeSet
    - Map
        - Collection of key-value pairs
        - Key should be an immutable datatype such as a String
        - Value can be anything
        - Primary operations
            - .get(K key)
            - .put(K key, E value)
            - .containsKey(K key)
            - .size()
            - .clear()
    - Queue
        - Collection of elements in FIFO order
        - Elements are added to the end of the queue, elements are removed from the front
        - Primary operations
            - .offer(E element)
            - .poll()
            - .peek()
            - .size()
            - .clear()

# HTTP
- HyperText Transfer Protocol
    - Protocol: standard for network communication
    - HTTP is the most common protocol for communication on the internet
- HTTP communication process
    1. Client sends an HTTP request to the server
        - HTTP request is requesting the server to provide some information back to the client or to process information provided by the client in the request
            - ex. A client requests that the server retrieves all reimbursement information
            - ex. A client requests that the server adds a reimbursement
            - ex. A client requests that the server approves/denies an reimbursement
    2. Server process the request and sends an HTTP response to the client
- HTTP Request
    - VERB (GET, POST, PUT, PATCH, DELETE)
    - URI (/reimbursements)
    - Request Body
        - Usually contains JSON data
    - Headers (key-value pairs)
- HTTP Response
    - Response Body
        - Usually contains JSON data
    - Status Code (1XX, 2XX, 3XX, 4XX, 5XX)
        - 1XX informational
        - 2XX success
        - 3XX redirect
        - 4XX client-side error
        - 5XX internal server-side error
    - Response Headers (key-value pairs)
- HTTP verbs
    - GET
        - Retrieve resources from server
    - POST
        - Add resource to server
    - PUT
        - Fully replace a resource
    - PATCH
        - Partially replace a resource
    - DELETE
        - Delete a resource

# Javalin
- Library that allows us to set up a Java-based HTTP server
- Server can listen for HTTP requests, process the requests, and provide an HTTP response
- Jackson Databind is a dependency used alongside Javalin in order to de-serialize JSON into Java objects + serialize Java objects into JSON
- Javalin utilizes an embedded HTTP server (Jetty)
- A Javalin object is used as a handle on the server
    - `Javalin app = Javalin.create()`
    - Endpoints can be mapped using `app.get("/myendpoint", (ctx) -> {})`, or post, put, patch, delete
