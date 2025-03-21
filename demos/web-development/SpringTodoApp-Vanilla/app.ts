const API_BASE_URL = 'http://localhost:8080';

interface Role {
  roleId: number;
}

interface User {
  email: string;
  password: string;
  role?: Role;
}

interface Task {
  title: string;
  description: string;
  dueDate: string;
  creationDate: string;
  category: { categoryId: number };
  taskStatus: { taskStatusId: number };
}

interface TaskResponseDto {
  task: Task;
  motivationalQuote?: string;
}

// Helper to safely get input values
function getInputValue(id: string): string {
  const input = document.getElementById(id) as HTMLInputElement | null;
  return input?.value ?? '';
}

// Register user
async function registerUser(): Promise<void> {
  const email = getInputValue('regEmail');
  const password = getInputValue('regPassword');
  const roleId = parseInt(getInputValue('regRoleId'));

  const user: User = {
    email,
    password,
    role: { roleId }
  };

  try {
    const response = await fetch(`${API_BASE_URL}/api/auth/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user)
    });

    if (!response.ok) throw new Error('Registration failed');

    const msg = document.getElementById('registerMessage');
    if (msg) msg.textContent = 'Registration successful! You can now log in.';
  } catch (error: any) {
    const msg = document.getElementById('registerMessage');
    if (msg) msg.textContent = error.message;
  }
}

// Login user
async function loginUser(): Promise<void> {
  const email = getInputValue('loginEmail');
  const password = getInputValue('loginPassword');

  const user: User = { email, password };

  try {
    const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user),
      credentials: 'include'
    });

    if (!response.ok) throw new Error('Login failed');

    await response.text();
    window.location.href = 'dashboard.html';
  } catch (error: any) {
    const msg = document.getElementById('loginMessage');
    if (msg) msg.textContent = error.message;
  }
}

// Load tasks
async function loadTasks(): Promise<void> {
  try {
    const response = await fetch(`${API_BASE_URL}/api/tasks`, {
      method: 'GET',
      credentials: 'include'
    });

    if (!response.ok) throw new Error('Failed to load tasks');

    const data: Task[] | TaskResponseDto = await response.json();
    const taskList = document.getElementById('taskList');
    if (!taskList) return;

    taskList.innerHTML = '';

    if (Array.isArray(data)) {
      for (const task of data) {
        const li = document.createElement('li');
        li.textContent = `${task.title} - ${task.description}`;
        taskList.appendChild(li);
      }
    } else {
      const li = document.createElement('li');
      li.textContent = `${data.task.title} - ${data.task.description}`;
      taskList.appendChild(li);

      if (data.motivationalQuote) {
        const quoteEl = document.getElementById('quote');
        if (quoteEl) quoteEl.textContent = data.motivationalQuote;
      }
    }
  } catch (error) {
    console.error('Error loading tasks:', error);
  }
}

// Add a new task
async function addTask(): Promise<void> {
  const title = getInputValue('taskTitle');
  const description = getInputValue('taskDescription');
  const dueDate = getInputValue('taskDueDate');

  const task: Task = {
    category: { categoryId: 1 },
    taskStatus: { taskStatusId: 1 },
    title,
    description,
    dueDate,
    creationDate: new Date().toISOString()
  };

  try {
    const response = await fetch(`${API_BASE_URL}/api/tasks`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(task),
      credentials: 'include'
    });

    if (!response.ok) throw new Error('Failed to add task');

    const form = document.getElementById('taskForm') as HTMLFormElement;
    if (form) form.reset();
    await loadTasks();
  } catch (error) {
    console.error('Error adding task:', error);
  }
}

// Logout
async function logout(): Promise<void> {
  try {
    const response = await fetch(`${API_BASE_URL}/api/auth/logout`, {
      method: 'POST',
      credentials: 'include'
    });

    if (!response.ok) throw new Error('Logout failed');

    window.location.href = 'login.html';
  } catch (error) {
    console.error('Error during logout:', error);
  }
}
