-- Insert Roles
INSERT INTO roles (role_name) VALUES ('ADMIN'), ('USER');

-- Insert Users (make sure role_id values match the ones inserted above)
INSERT INTO users (email, password, role_id)
VALUES
  ('admin@example.com', 'adminpassword', 1),
  ('user@example.com', 'userpassword', 2);

-- Insert User Profiles (user_id should match the users above)
INSERT INTO user_profiles (user_id, first_name, last_name, phone_number, dob)
VALUES
  (1, 'Admin', 'User', '1234567890', '1990-01-01'),
  (2, 'John', 'Doe', '0987654321', '1995-05-15');

-- Insert Task Statuses
INSERT INTO task_statuses (status) VALUES ('Pending'), ('Completed');

-- Insert Task Categories
INSERT INTO task_categories (name) VALUES ('Work'), ('Personal');

-- Insert Tasks (ensure that user_profile_id, category_id, and task_status_id match the data above)
INSERT INTO tasks (user_profile_id, category_id, task_status_id, title, description, due_date, creation_date)
VALUES
  (1, 1, 1, 'Setup Project', 'Initialize project structure and dependencies', '2025-04-15', '2025-04-01 10:00:00'),
  (2, 2, 2, 'Write Documentation', 'Document all endpoints and business logic', '2025-04-20', '2025-04-05 14:30:00');
