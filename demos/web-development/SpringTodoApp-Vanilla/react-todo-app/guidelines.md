## 1. Initialize the Project with Vite
Vite will make our dev process faster and easier to manage. But feel free to use `npm create-react-app`.
```bash
npm create vite@latest loan-management-app -- --template react-ts
cd loan-management-app
npm install
```

---

## 2. Install Additional Packages

```bash
npm install --save-dev react-router-dom
```

---

## 3. Project Structure

```
src/
├── api/                # API calls
├── models/             # Interfaces for User/Task
├── pages/              # Login, Register, Dashboard
├── components/         # Reusable UI
├── App.tsx
├── main.tsx
```

---

## 4. Define Models

### `src/models/User.ts`
```ts
export interface Role {
  roleId: number;
}

export interface User {
  email: string;
  password: string;
  role?: Role;
}
```

### `src/models/Task.ts`
```ts
export interface Task {
  title: string;
  description: string;
  dueDate: string;
  creationDate: string;
  category: { categoryId: number };
  taskStatus: { taskStatusId: number };
}

export interface TaskResponseDto {
  task: Task;
  motivationalQuote?: string;
}
```

---

## 5. Create API Layer

### `src/api/api.ts`
```ts
const API_BASE_URL = 'http://localhost:8080';

export const registerUser = async (user: any) =>
  fetch(`${API_BASE_URL}/api/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(user)
  });

export const loginUser = async (user: any) =>
  fetch(`${API_BASE_URL}/api/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(user),
    credentials: 'include'
  });

export const logoutUser = async () =>
  fetch(`${API_BASE_URL}/api/auth/logout`, {
    method: 'POST',
    credentials: 'include'
  });

export const getTasks = async () =>
  fetch(`${API_BASE_URL}/api/tasks`, {
    credentials: 'include'
  });

export const addTask = async (task: any) =>
  fetch(`${API_BASE_URL}/api/tasks`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(task),
    credentials: 'include'
  });
```

---

## 6. Pages

### `src/pages/RegisterPage.tsx`
```tsx
import React, { useState } from 'react';
import { registerUser } from '../api/api';

export const RegisterPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [roleId, setRoleId] = useState('');
  const [message, setMessage] = useState('');

  const handleRegister = async () => {
    try {
      const response = await registerUser({
        email,
        password,
        role: { roleId: parseInt(roleId) }
      });
      if (!response.ok) throw new Error('Registration failed');
      setMessage('Registration successful!');
    } catch (err: any) {
      setMessage(err.message);
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
      <input value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" type="password" />
      <input value={roleId} onChange={e => setRoleId(e.target.value)} placeholder="Role ID" />
      <button onClick={handleRegister}>Register</button>
      <p>{message}</p>
    </div>
  );
};
```

---

### `src/pages/LoginPage.tsx`
```tsx
import React, { useState } from 'react';
import { loginUser } from '../api/api';
import { useNavigate } from 'react-router-dom';

export const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await loginUser({ email, password });
      if (!response.ok) throw new Error('Login failed');
      navigate('/dashboard');
    } catch (err: any) {
      setMessage(err.message);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
      <input value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" type="password" />
      <button onClick={handleLogin}>Login</button>
      <p>{message}</p>
    </div>
  );
};
```

---

### `src/pages/DashboardPage.tsx`
```tsx
import React, { useEffect, useState } from 'react';
import { getTasks, addTask, logoutUser } from '../api/api';
import { useNavigate } from 'react-router-dom';
import { Task, TaskResponseDto } from '../models/Task';

export const DashboardPage = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [quote, setQuote] = useState('');
  const navigate = useNavigate();

  const fetchTasks = async () => {
    try {
      const res = await getTasks();
      const data = await res.json();
      if (Array.isArray(data)) {
        setTasks(data);
      } else {
        setTasks([data.task]);
        if (data.motivationalQuote) setQuote(data.motivationalQuote);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const handleAddTask = async () => {
    const task = {
      title,
      description,
      dueDate,
      creationDate: new Date().toISOString(),
      category: { categoryId: 1 },
      taskStatus: { taskStatusId: 1 }
    };
    await addTask(task);
    setTitle('');
    setDescription('');
    setDueDate('');
    fetchTasks();
  };

  const handleLogout = async () => {
    await logoutUser();
    navigate('/');
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <div>
      <h2>Dashboard</h2>
      <button onClick={handleLogout}>Logout</button>

      <h3>Add Task</h3>
      <input value={title} onChange={e => setTitle(e.target.value)} placeholder="Title" />
      <input value={description} onChange={e => setDescription(e.target.value)} placeholder="Description" />
      <input type="date" value={dueDate} onChange={e => setDueDate(e.target.value)} />
      <button onClick={handleAddTask}>Add Task</button>

      {quote && <p><strong>Motivational Quote:</strong> {quote}</p>}

      <h3>Tasks</h3>
      <ul>
        {tasks.map((task, i) => (
          <li key={i}>{task.title} - {task.description}</li>
        ))}
      </ul>
    </div>
  );
};
```

---

## 7. Set Up Routing

### `src/App.tsx`
```tsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { LoginPage } from './pages/LoginPage';
import { RegisterPage } from './pages/RegisterPage';
import { DashboardPage } from './pages/DashboardPage';

const App = () => (
  <Router>
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/dashboard" element={<DashboardPage />} />
    </Routes>
  </Router>
);

export default App;
```

---

## 8. Start the App

```bash
npm run dev
```

---

## 9. Bonus Ideas
- Add validation using `react-hook-form` or `Yup`
- Use `localStorage` or `Context` for logged-in state
- Add a loading spinner with `isLoading` state

---