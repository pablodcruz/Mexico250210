"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const API_BASE_URL = 'http://localhost:8080';
// Helper to safely get input values
function getInputValue(id) {
    var _a;
    const input = document.getElementById(id);
    return (_a = input === null || input === void 0 ? void 0 : input.value) !== null && _a !== void 0 ? _a : '';
}
// Register user
function registerUser() {
    return __awaiter(this, void 0, void 0, function* () {
        const email = getInputValue('regEmail');
        const password = getInputValue('regPassword');
        const roleId = parseInt(getInputValue('regRoleId'));
        const user = {
            email,
            password,
            role: { roleId }
        };
        try {
            const response = yield fetch(`${API_BASE_URL}/api/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(user)
            });
            if (!response.ok)
                throw new Error('Registration failed');
            const msg = document.getElementById('registerMessage');
            if (msg)
                msg.textContent = 'Registration successful! You can now log in.';
        }
        catch (error) {
            const msg = document.getElementById('registerMessage');
            if (msg)
                msg.textContent = error.message;
        }
    });
}
// Login user
function loginUser() {
    return __awaiter(this, void 0, void 0, function* () {
        const email = getInputValue('loginEmail');
        const password = getInputValue('loginPassword');
        const user = { email, password };
        try {
            const response = yield fetch(`${API_BASE_URL}/api/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(user),
                credentials: 'include'
            });
            if (!response.ok)
                throw new Error('Login failed');
            yield response.text();
            window.location.href = 'dashboard.html';
        }
        catch (error) {
            const msg = document.getElementById('loginMessage');
            if (msg)
                msg.textContent = error.message;
        }
    });
}
// Load tasks
function loadTasks() {
    return __awaiter(this, void 0, void 0, function* () {
        try {
            const response = yield fetch(`${API_BASE_URL}/api/tasks`, {
                method: 'GET',
                credentials: 'include'
            });
            if (!response.ok)
                throw new Error('Failed to load tasks');
            const data = yield response.json();
            const taskList = document.getElementById('taskList');
            if (!taskList)
                return;
            taskList.innerHTML = '';
            if (Array.isArray(data)) {
                for (const task of data) {
                    const li = document.createElement('li');
                    li.textContent = `${task.title} - ${task.description}`;
                    taskList.appendChild(li);
                }
            }
            else {
                const li = document.createElement('li');
                li.textContent = `${data.task.title} - ${data.task.description}`;
                taskList.appendChild(li);
                if (data.motivationalQuote) {
                    const quoteEl = document.getElementById('quote');
                    if (quoteEl)
                        quoteEl.textContent = data.motivationalQuote;
                }
            }
        }
        catch (error) {
            console.error('Error loading tasks:', error);
        }
    });
}
// Add a new task
function addTask() {
    return __awaiter(this, void 0, void 0, function* () {
        const title = getInputValue('taskTitle');
        const description = getInputValue('taskDescription');
        const dueDate = getInputValue('taskDueDate');
        const task = {
            category: { categoryId: 1 },
            taskStatus: { taskStatusId: 1 },
            title,
            description,
            dueDate,
            creationDate: new Date().toISOString()
        };
        try {
            const response = yield fetch(`${API_BASE_URL}/api/tasks`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(task),
                credentials: 'include'
            });
            if (!response.ok)
                throw new Error('Failed to add task');
            const form = document.getElementById('taskForm');
            if (form)
                form.reset();
            yield loadTasks();
        }
        catch (error) {
            console.error('Error adding task:', error);
        }
    });
}
// Logout
function logout() {
    return __awaiter(this, void 0, void 0, function* () {
        try {
            const response = yield fetch(`${API_BASE_URL}/api/auth/logout`, {
                method: 'POST',
                credentials: 'include'
            });
            if (!response.ok)
                throw new Error('Logout failed');
            window.location.href = 'login.html';
        }
        catch (error) {
            console.error('Error during logout:', error);
        }
    });
}
