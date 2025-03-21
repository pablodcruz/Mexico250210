export interface Task {
    title: string;
    description: string;
    dueDate: string;
    creationDate: string;
    category: { categoryId: number };
    taskStatus: { taskStatusId: number };
  }
  
