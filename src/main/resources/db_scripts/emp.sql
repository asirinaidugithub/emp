-- emp.hibernate_sequence definition

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- emp.t_employee definition

CREATE TABLE `t_employee` (
  `id` bigint NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `doj` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emp_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- emp.t_phone definition

CREATE TABLE `t_phone` (
  `id` bigint NOT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `emp_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1cmujorhrfv6laemeenwvghju` (`emp_id`),
  CONSTRAINT `FK1cmujorhrfv6laemeenwvghju` FOREIGN KEY (`emp_id`) REFERENCES `t_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- insert scripts
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(2, 'system', '2023-11-27 07:57:54.607000', '1988-05-31', 'asiri@gmail.com', 'EMP_01', 'asiri', 'patnana', 70000.0, 'system', '2023-11-27 07:57:54.607000');
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(5, 'system', '2023-11-27 07:58:32.641000', '1989-05-31', 'patnana@gmail.com', 'EMP_02', 'six', 'six', 75000.0, 'system', '2023-11-27 07:58:32.641000');
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(6, 'system', '2023-11-27 07:58:32.641000', '1989-05-31', 'patnana@gmail.com', 'EMP_03', 'one', 'one', 15000.0, 'system', '2023-11-27 07:58:32.641000');
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(7, 'system', '2023-11-27 07:58:32.641000', '1989-05-31', 'patnana@gmail.com', 'EMP_04', 'two', 'two', 20000.0, 'system', '2023-11-27 07:58:32.641000');
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(8, 'system', '2023-11-27 07:58:32.641000', '1989-05-31', 'patnana@gmail.com', 'EMP_05', 'three', 'three', 50000.0, 'system', '2023-11-27 07:58:32.641000');
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(9, 'system', '2023-11-27 07:58:32.641000', '1989-05-31', 'patnana@gmail.com', 'EMP_06', 'four', 'four', 100000.0, 'system', '2023-11-27 07:58:32.641000');
INSERT INTO emp.t_employee
(id, created_by, created_date, doj, email, emp_id, first_name, last_name, salary, updated_by, updated_date)
VALUES(10, 'system', '2023-11-27 07:58:32.641000', '1989-05-31', 'patnana@gmail.com', 'EMP_07', 'five', 'five', 270000.0, 'system', '2023-11-27 07:58:32.641000');

INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(3, '1234567', 2);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(4, '98765434567', 2);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(6, '1234567', 5);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(7, '98765434567', 6);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(8, '98765434567', 7);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(9, '98765434567', 8);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(10, '98765434567', 9);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(11, '98765434567', 10);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(12, '98765434567', 6);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(13, '98765434567', 7);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(14, '98765434567', 8);
INSERT INTO emp.t_phone
(id, phone_no, emp_id)
VALUES(15, '98765434567', 9);
