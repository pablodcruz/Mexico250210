import { Task } from "../models/Task";

const API_BASE_URL = 'http://107.23.178.208:8080';

export const getTasks = async () => {
  return fetch(`${API_BASE_URL}/api/tasks`, {
    credentials: 'include'
  });
};

export const addTask = async (task: Task) => {
  return fetch(`${API_BASE_URL}/api/tasks`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(task),
    credentials: 'include'
  });
};
