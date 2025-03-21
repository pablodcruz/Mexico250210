import { Role } from "./Role";

  
  export interface User {
    email: string;
    password: string;
    role?: Role;
  }
  