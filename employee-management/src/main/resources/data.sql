-- Insert sample departments
INSERT INTO departments (name, description) VALUES
('Human Resources', 'Handles recruitment, employee relations, and benefits'),
('Information Technology', 'Manages technology infrastructure and support'),
('Finance', 'Handles budgeting, accounting, and financial reporting'),
('Marketing', 'Responsible for promoting company products and services'),
('Operations', 'Manages day-to-day business operations');

-- Insert sample employees
INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, salary, department_id) VALUES
('John', 'Doe', 'john.doe@example.com', '+1 (555) 123-4567', '2022-01-15', 75000.00, 2),
('Jane', 'Smith', 'jane.smith@example.com', '+1 (555) 987-6543', '2021-03-22', 82000.00, 3),
('Robert', 'Johnson', 'robert.j@example.com', '+1 (555) 456-7890', '2020-11-05', 68000.00, 1),
('Sarah', 'Williams', 'sarah.w@example.com', '+1 (555) 234-5678', '2022-06-18', 71000.00, 4),
('Michael', 'Brown', 'michael.b@example.com', '+1 (555) 876-5432', '2019-09-12', 89000.00, 2);