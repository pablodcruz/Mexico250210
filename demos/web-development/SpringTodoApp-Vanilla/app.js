// Base URL of the backend API
const API_BASE_URL = 'http://localhost:8080';

// Function to register a new user
function registerUser() {
  // Retrieve values from the registration form
  const email = document.getElementById('regEmail').value;
  const password = document.getElementById('regPassword').value;
  const roleId = document.getElementById('regRoleId').value;

  // Create a user object according to the expected JSON format
  const user = {
    email: email,
    password: password,
    role: { roleId: roleId }
  };

  // Send a POST request to the register endpoint
  fetch(`${API_BASE_URL}/api/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(user)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Registration failed');
      }
      return response.json();
    })
    .then(data => {
      // Inform the user of a successful registration
      document.getElementById('registerMessage').textContent = 'Registration successful! You can now log in.';
    })
    .catch(error => {
      document.getElementById('registerMessage').textContent = error.message;
    });
}

// Function to login a user
function loginUser() {
  // Retrieve login form values
  const email = document.getElementById('loginEmail').value;
  const password = document.getElementById('loginPassword').value;

  // Create a login object; note we only need email and password here
  const user = { email: email, password: password };

  // Send a POST request to the login endpoint
  fetch(`${API_BASE_URL}/api/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(user),
    credentials: 'include' // Include session cookies for session-based authentication
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Login failed');
      }
      // Assuming a successful login returns a simple text message
      return response.text();
    })
    .then(data => {
      // Redirect the user to the dashboard upon successful login
      window.location.href = 'dashboard.html';
    })
    .catch(error => {
      document.getElementById('loginMessage').textContent = error.message;
    });
}

// Function to load tasks from the backend and display them on the dashboard
function loadTasks() {
  fetch(`${API_BASE_URL}/api/tasks`, {
    method: 'GET',
    credentials: 'include'
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to load tasks');
      }
      return response.json();
    })
    .then(data => {
      const taskList = document.getElementById('taskList');
      taskList.innerHTML = ''; // Clear any existing tasks

      // Check if the data returned is an array or an object (if a new task returns a TaskResponseDto)
      if (Array.isArray(data)) {
        data.forEach(task => {
          const li = document.createElement('li');
          li.textContent = `${task.title} - ${task.description}`;
          taskList.appendChild(li);
        });
      } else {
        // When adding a new task, the response may be a TaskResponseDto
        const li = document.createElement('li');
        li.textContent = `${data.task.title} - ${data.task.description}`;
        taskList.appendChild(li);
        // Display the motivational quote if provided
        if (data.motivationalQuote) {
          document.getElementById('quote').textContent = data.motivationalQuote;
        }
      }
    })
    .catch(error => {
      console.error('Error loading tasks:', error);
    });
}

// Function to add a new task
function addTask() {
  // Retrieve values from the task form
  const title = document.getElementById('taskTitle').value;
  const description = document.getElementById('taskDescription').value;
  const dueDate = document.getElementById('taskDueDate').value;
  
  // Create a task object according to the expected JSON format
  // Here, we assume categoryId=1 and taskStatusId=1 for simplicity
  const task = {
    category: { categoryId: 1 },
    taskStatus: { taskStatusId: 1 },
    title: title,
    description: description,
    dueDate: dueDate,
    creationDate: new Date().toISOString()
  };

  // Send a POST request to the create task endpoint
  fetch(`${API_BASE_URL}/api/tasks`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(task),
    credentials: 'include' // Include session cookies for authentication
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to add task');
      }
      return response.json();
    })
    .then(data => {
      // Clear the task form after a successful submission
      document.getElementById('taskForm').reset();
      // Reload tasks to include the new one and display the motivational quote (if any)
      loadTasks();
    })
    .catch(error => {
      console.error('Error adding task:', error);
    });
}

// Function to logout the user by calling the logout endpoint
function logout() {
  fetch(`${API_BASE_URL}/api/auth/logout`, {
    method: 'POST',
    credentials: 'include'
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Logout failed');
      }
      // Redirect the user to the login page after logging out
      window.location.href = 'login.html';
    })
    .catch(error => {
      console.error('Error during logout:', error);
    });
}
