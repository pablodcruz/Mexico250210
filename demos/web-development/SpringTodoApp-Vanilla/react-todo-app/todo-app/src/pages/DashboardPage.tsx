import { useEffect, useState, useContext } from 'react';
import { Task } from '../models/Task';
import { addTask, getTasks } from '../services/taskService';
import { logoutUser } from '../services/authService';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';

export const DashboardPage = () => {
  // Initial state is an empty array of type Task[]
  const [tasks, setTasks] = useState<Task[]>([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [quote, setQuote] = useState('');
  const { checkSession } = useContext(AuthContext)!; // ! is the non-null assertion operator, basically telling typescript we know this wont be null, so dont worry about it. 

  const navigate = useNavigate();

  // Access user from our AuthContext
  const auth = useContext(AuthContext);
  const user = auth?.user; // ? marks it as optional since user may be null if not logged in

  const fetchTasks = async () => {
    try {
      const res = await getTasks();
      const data = await res.json();
      if (Array.isArray(data)) {
        setTasks(data);
      } else {
        setTasks([data.task]);
        if (data.motivationalQuote) setQuote(data.motivationalQuote); // not yet implemented fully
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
      taskStatus: { taskStatusId: 1 },
    };
    await addTask(task);
    // clean the ui after adding the task:
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
    const validateSession = async () => {
      const isValid = await checkSession();
      if (isValid) {
        console.log("Session valid");
      } else {
        navigate("/");
      }
    };
 
    validateSession(); 
  }, []);

  return (
    <div>
      <h2>Dashboard</h2>
      {/* Display user info if user is logged in */}
      {user && (
        <p>
          Logged in as: <strong>{user.email}</strong>
          {user.role && <span> | Role: {user.role.roleName}</span>}
        </p>
      )}
      <button onClick={handleLogout}>Logout</button>

      <h3>Add Task</h3>
      <input
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Title"
      />
      <input
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        placeholder="Description"
      />
      <input
        type="date"
        value={dueDate}
        onChange={(e) => setDueDate(e.target.value)}
      />
      <button onClick={handleAddTask}>Add Task</button>

      {quote && (
        <p>
          <strong>Motivational Quote:</strong> {quote}
        </p>
      )}

      <h3>Tasks</h3>
      <ul>
        {/* React uses keys to identify elements that have changed, so it can optimally re-render only updated elements, we should use the items id we have stored in the db */}
        {tasks.map((task, i) => (
          <li key={i}>
            {task.title} - {task.description}
          </li>
        ))}
         </ul>
    </div>
  );
};
