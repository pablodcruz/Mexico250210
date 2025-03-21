import React, { useEffect, useState } from 'react';
import { Task } from '../models/Task';
import { addTask, getTasks } from '../services/taskService';
import { logoutUser } from '../services/authService';
import { useNavigate } from 'react-router-dom';


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
