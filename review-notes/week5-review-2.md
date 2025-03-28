# Node.js
* It is a free and open-source JavaScript interpreter that allows us to run JS outside of a web browser
* Platform independent
    * It can run on any OS you utilize
* Uses Google's chrome V8 engine

## Node Package Manager (NPM)
* It is similar to Maven, it can manage your dependencies and also grab them from the internet
* But unlike using pom.xml, it uses package.json file
* It will also store all of our dependencies in a folder called **node_modules**

# TypeScript (TS)
* It is an open-source language developed by Microsoft
* It suppose object-oriented programming (a bit better)
* It is a **superset** of JavaScript
    * All this means that Typescript has everything JavaScript has but it has more methods/tools (Ex: you have access modifiers, you have interfaces, etc.)
    ```ts
    interface User {
      email: string;
      password: string;
      role?: Role;
    }
    ```
* It is strictly typed
    * Probably one of the best things about TS

## Transpilation process
* It is the process of converting our TS file into a JS
* All browsers (so far) only support JavaScript files and have no plans to change it to supporting TS any time soon. So to make TypeScript readily available for use for any web browsers, they created this transpilation process

## TS Datatypes
* Everything from JS
* Any
* Tuple
* Enums
* Never - Used for error handling
    * If you are interested, essentially if you place this with a function/method, it means that this function will never return anything. Void still returns "no value" while Never will never return anything.
    * So mainly used for error exceptions since that will end the program and not "complete" the function completely or in more rare cases, you want a function that will never end
* Void - Used for function/methods to indicate it will return nothing

## Access modifiers in TS
* public - access everywhere
* protected - access within the class and its subclasses
* private - access only within the class 

# Introduction to React
* It is a frontend Javascript library to develop websites
    * Notice it is just a library, hence we will need to add more and more dependencies to utilize other functionalities provided by other libraries
* It is **Single Page Application**
    * Allows us to navigate through different web pages in our website without reloading the entire page
        * (When you did servlets, you noticed how everytime you enter a new page, it is completely blank and takes a while until you finally see something)
    * You only have one HTML page and you utilize JS heavily to dynamically change your website to **appear** like you went to a different page
    * Advantages:
        * Quick load time since your local computer/phone will do the majority of the leg work and doesn't need to talk to the server again
        * Provides pleasant user experience with mobile due to unstable network connections
    * Disadvantages:
        * Web crawlers will have a hard time finding specific section of your website (Mostly cause JS is the one that is giving HTML docs)
        * Uses a lot of resources, your computer is doing the majority of the "thinking" to render the website to your browser
* It is component-based
    * Don't with having one HTML document that will specify one web page in your website.
    * Instead, they modularize a website a bit more further
    * A components are the building blocks that makes your entire website
    * This was made so you can reuse multiple components in different places but displays things a little bit different
* They embraced a philosophy in which you should have everything in one place and not in different files

## Library vs. a Framework
* Easy distinction is a library is a lot less functions/features compared to a framework
* Actual technical distinction is Frameworks have a lot more control over the flow of your code and how it runs
    * Spring controls most of your object creation and also have specific pipelines it goes through ever time you run a spring framework project
    * In React, you will see that we still have to manually setup a lot of things just to use it
        * Heck, React by default doesn't have routing, we need to get another dependency just to do that

## Different important files in React
* Package.json - used to find dependencies for the React app
* tsconfig.json - Used to configure our transpilation process
* node_modules - Used to store dependencies for your React app.
* Index.tsx - Your main entry point for your components. Interacts with your Index.html to append your components to

## What is a component?
* It is a single UI/graphic that is independent and reusable.
* You can think of puzzle pieces, by itself it has a piece of a picture, together with other puzzle pieces you get an image
* TLDR: you make components to reuse them in your website and they are a tiny piece of your app that makes up a whole page
* Class Component - They are components made by creating a class and extending React.Component
* Function Component - They are components made by creating a function

## Controlled and Uncontrolled Components
* Controlled components are tightly coupled with React's state management.
* Any changes to the component's state are reflected immediately in the component's UI.
* Uncontrolled components maintain their state independently of React's state management.
* They do not rely on React to synchronize state changes with the UI.

## High Order Components
* High order components are functions that take a component and return a new component.
* The traditional component model transforms props into a portion of the UI.
* High order components take a component as a parameter and transforms it, returning a new component with added functionality.
* The idea is based on the idea of a high-order function in JavaScript, where a function is taken as an argument, and another function is returned.

## Nested Components
* Nested components refer to components that are used within other components, enabling a modular and structured approach to building React applications.

## JSX
* An extension of JavaScript.
* It gives us all the power to combine both HTMl and JS and have them pass/use data pretty easily
* This goes back to the philosophy React believes in, which everything should be at one place
* Less known reasons why use JSX:
    * We heavily use JavaScript to render our webpages and that comes with some major risks with JS injection attacks (Also known as XSS). Using JSX prevents such attacks.
* Ex: Storing an HTML element into a JS variable, using JS variable to display on a HTML element

## Class vs Functional components
* Class components are just component made by using a class
* Function Components are components that are made using functions
    * Currently the most supported way you should go about making components in React
* Depending on which you pick, the syntax will look different on how you apply certain features in React
    * Ex: Doing props in function component looks very different class component

## Component Lifecycle
* Like with anything, a component have a lifespan. It will live and it will die. In coding, this means we have the power to control a component life (messed up I know). 
* All seriousness this means, if a component dies, do this behavior or run a function or do whatever you want
* If a component lives/reborns, do this behavior or run a function or do whatever you want
* Now why does this matter a lot in frontend?
    * There are scenarios where getting data from a database takes time. If we tell our component to render the page regardless if we got any data, we will get blank information
    * However, since we have control over its life, we can tell the component hold up man display AFTER you got the data
    * For you guys, you will use props, have perfect code, but you get blank component and most likely or not because of the lifecycle of a component
* Lifecycle is a class component exclusive, meaning you only use it with class component
* https://reactjs.org/docs/state-and-lifecycle.html

## Hooks
* A new way of messing with states rather than doing the old way
* https://www.youtube.com/watch?v=dpw9EHDh2bM (if you really have time to watch why they decided to make this move)
* Essentially no more dealing with lifecycle since Hooks will deal with them for you
* New information just came in and I have to re-render the page cause of it? Sure I can do that
    * With class components it took more boilerplate code to do it
* TLDR, if you have a dynamically changing information in your webpage use **HOOKS** or else they will **NOT** display the changes

## React Hooks examples

### 🔹 `useState`
Manages local state in a functional component.

```tsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>Clicked {count}</button>;
}
```

---

### 🔹 `useEffect`
Performs side effects like fetching data or syncing with external systems.

```tsx
import { useEffect, useState } from 'react';

function Quote() {
  const [quote, setQuote] = useState('');

  useEffect(() => {
    fetch('/api/quote')
      .then(res => res.json())
      .then(data => setQuote(data.text));
  }, []); // Run once on mount, provide state to run on state change

  return <p>{quote}</p>;
}
```

---

### 🔹 `useContext`
Lets you read and subscribe to the value of a React Context.

It’s used when you want to share data globally (like user info, theme, or language) across many components without manually passing props through every component layer.

---

## Managing Logged-in User Data (Authentication Context)**

### Why?
When a user logs in, you often want to:
- Show their **name or avatar** in the navbar
- Use their **auth token** to make API calls
- Conditionally render pages/components based on their **role or login status**
- Allow them to **log out from anywhere**

### 📦 `useContext` lets you:
- Store the user data in **one place**
- Access it from **any component**, without prop-drilling

---

### 🧪 Example: AuthContext

```tsx
// AuthContext.tsx
import { createContext, useContext, useState } from 'react';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = (userData) => setUser(userData);
  const logout = () => setUser(null);

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook for easy access
export const useAuth = () => useContext(AuthContext);
```

```tsx
// App.tsx
<AuthProvider>
  <Navbar />
  <Dashboard />
</AuthProvider>
```

```tsx
// Navbar.tsx
import { useAuth } from './AuthContext';

function Navbar() {
  const { user, logout } = useAuth();

  return (
    <div>
      {user ? (
        <>
          <span>Welcome, {user.name}</span>
          <button onClick={logout}>Logout</button>
        </>
      ) : (
        <span>Not logged in</span>
      )}
    </div>
  );
}
```

---

## Other Common Use Cases for `useContext`

| Use Case         | Example Description                         |
|------------------|---------------------------------------------|
| ✅ Theme toggling | Share a light/dark mode value app-wide      |
| ✅ Language switcher | Provide selected language (i18n) globally |
| ✅ Shopping cart | Store cart items in context for a store app |
| ✅ Modal or toast visibility | Toggle global UI components        |

---

### 🔹 `useRef`
In React.js, a ref is essentially a reference to a DOM element or an instance of a component. Refs provide us with an API to access and modify DOM elements without using props, states, etc. 

The useRef is used for creating a ref in React Functional Components (RFCs). Its usage is similar to that of any other hook in React, in that we simply call the hook and it returns the ref.

```tsx
const ref = useRef();
```
 
## Lifting State (with Example)

Lifting state **up** means moving shared state to the **common parent**.

```tsx
function Parent() {
  const [text, setText] = useState('');

  return (
    <>
      <InputComponent onInputChange={setText} />
      <DisplayComponent text={text} />
    </>
  );
}

function InputComponent({ onInputChange }) {
  return <input onChange={e => onInputChange(e.target.value)} />;
}

function DisplayComponent({ text }) {
  return <p>{text}</p>;
}
```

Now `text` is shared between both components, controlled by the parent.

## States
* While props are used to transfer information into the component to use
* States are used within the component to re-render your web page with dynamically changing variables/information
* States are **immutable**, you must use the `useState` method to change the information and have it reflect
  - **Immutability** - mutating state directly can lead to components which are buggy or difficult to optimize. Always use the lifecycle hooks like `useState`

## Props
* Props are a great way to pass information into a component to be used/displayed
    * Essentially passing from **parent to child** component
    * You can pass from child to parent using callback functions
        * https://www.pluralsight.com/guides/react-communicating-between-components
* You specify props within the parameter of the function component
* Whenever you use that component, you must provide the necesary information as well (UNLESS IT IS OPTIONAL!)
* A very big part of React to make components reusable

## State vs Props
* State enables components to hold and update their data.(`useState`)
* Props facilitate communication between parent and child components
* This allows for the creation of reusable and composable UI elements.

### Using State
* Refer to hooks `useState`

### Passing Props
To pass props to a child component, you simply include them as attributes when rendering the child component. Here’s an example:

  ```jsx
  function ParentComponent() {
  const greeting = “Hello, React!”;
  return <ChildComponent message={greeting} />;
  }

  function ChildComponent(props) {
  return <p>{props.message}</p>;
  }
  ```

## Rendering Elements on the DOM
* With traditional JavaScript and HTML, we need a base HTML file and we may have elements we can dynamically create using JavaScript. 
* This rendering process still holds true for React.

## Virtual DOM
* Whenever we interact with the "DOM" in React using React specific things such as hooks or jsx, we are actually messing with a virtual DOM
* Yes there is two DOMs in React
* React will looking into the virtual DOM and compare it with the real HTML DOM, based on the difference between the two, it will only change the real DOM on the differences
* This is to optimize the process of changing your real DOM by specifically targetting certain sections to it and changing it
* This syncing process is called **reconciliation**
* TLDR: this is why we don't do document.querySelector() anymore, React will take care of all the real DOM manipulation

## Conditional Rendering
* When you want to render/display certain things in React based on well a condition

## Lists and Keys
* Whenever you display list of items, React requires you to add a key
* It is important for React so it can uniquely identify that specific HTML element in the event that it needs to dynamically change it
* It is massive optimization hit if you don't add keys when you map things in React
* Just another point as to why we add unique identifiers in pretty much everything in our database

## Events in React
* Handling events is syntactically similar to handling events with inline HTML. 
* When using JSX, however, event names use camelCase, rather than lowercase, and the event handler is passed in as a JavaScript reference rather than just a string.

  ```tsx
  const MyButton = () => {
    const doSomething = () => {
      // event functionality
    };

    return <button onClick={doSomething}>Click me to do the thing!</button>;
  };
  ```

* In HTML, the equivalent would be:

  ```html
  <button onclick="doSomething()">
    Click me to do the thing!
  </button>
  ```

## React Routing
* A React library that allows us to switch between components as if we were switching pages in your traditional routing in HTML
* Good idea to establish the routing component in index.tsx, our main entry point for our components
* It is an extra dependency, we have to mess with our NPM once again
    * `npm install react-router-dom@6`
* Documentation: https://reactrouter.com/en/main

## Axios
* A way to communcate with an external API or your very own
* It is more abstracted form of using your normal AJAX in vanilla JS
    * It uses XMLHTTPRequest at the back
* Can by added by using `npm install axios`

## Why use Axios over Fetch?
* It can automatically convert your JSOn into JS object to start using it
* Protection over XSRF (Cross Site Request Forgery)
* Easy way to make a progress bar

## useEffect Hook (cont)
* To fully utilize grabbing information through the network (via API) you need to use this hook
* Essentially, it will run that function whenever an "update" gets detected by React
* It will also run when you first initializes your component (probably the most use case o fit)
* One of those "updates", whenever the DOM changes

## React State Management
* React will pass state information from one component to another.
* The two component must be related to each other (child to parent relationship)
* You can bubble up the state information using a bunch of callback functions
* You can drilling down the state information using a bunch of props

## Lifting State
* This is when you "lifted" a state to a higher component so that all of the child component can share a universal value
    * You can think of it as having singleton variable for all the child components
* Child to Parent communication is needed for lifting states
* **You must put the state to the common ancestry of all the child components (Their parent basically)**