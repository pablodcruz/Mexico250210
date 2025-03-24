import { useState } from 'react';
import { registerUser } from '../services/authService';

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
    } catch (error: unknown) {
        if (error instanceof Error) {
          console.error(error.message);
        } else {
          console.error('Unknown error occurred');
        }
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
