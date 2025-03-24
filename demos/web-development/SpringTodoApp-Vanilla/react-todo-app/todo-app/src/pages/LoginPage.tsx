import { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';


export const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(AuthContext)!; // ! is the non-null assertion operator, basically telling typescript we know this wont be null, so dont worry about it. 
  const navigate = useNavigate();

  const handleLogin = async () => {
    await login(email, password);
    navigate('/dashboard');  
  };

  return (
    <div>
      <h2>Login</h2>
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Email" />
      <input value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" type="password" />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};
