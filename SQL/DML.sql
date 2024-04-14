INSERT INTO Members (member_id, username, password, email, name, address, date_of_birth, fitness_goal, height, weight)
VALUES 
(1, 'john_doe', 'password123', 'john@example.com', 'John Doe', '123 Main St', '1990-01-01', 'Lose weight', 180.0, 75.0),
(2, 'jane_smith', 'password456', 'jane@example.com', 'Jane Smith', '456 Elm St', '1992-05-15', 'Build muscle', 170.0, 65.0),
(3, 'mike_jackson', 'password789', 'mike@example.com', 'Mike Jackson', '789 Oak St', '1985-09-22', 'Improve endurance', 175.0, 80.0),
(4, 'emily_wilson', 'password123', 'emily@example.com', 'Emily Wilson', '101 Maple St', '1988-03-10', 'Increase flexibility', 160.0, 60.0),
(5, 'chris_adams', 'password456', 'chris@example.com', 'Chris Adams', '202 Pine St', '1995-11-28', 'Maintain weight', 185.0, 70.0);

INSERT INTO Trainers (trainer_id, name, email, phone_number, specialization)
VALUES 
(1, 'John Trainer', 'john_trainer@example.com', '1234567890', 'Weightlifting'),
(2, 'Jane Trainer', 'jane_trainer@example.com', '9876543210', 'Yoga'),
(3, 'Mike Trainer', 'mike_trainer@example.com', '4561237890', 'Cardio'),
(4, 'Emily Trainer', 'emily_trainer@example.com', '7894561230', 'Pilates'),
(5, 'Chris Trainer', 'chris_trainer@example.com', '3216549870', 'CrossFit');

INSERT INTO TrainerAvailability (trainer_id, start_time, end_time)
VALUES
(1, '08:00:00', '12:00:00'),
(2, '10:00:00', '14:00:00'),
(3, '13:00:00', '17:00:00'),
(4, '11:00:00', '15:00:00'),
(5, '09:00:00', '13:00:00');

INSERT INTO Rooms (room_id, room_name, capacity)
VALUES
(1, 'Yoga Room', 50),
(2, 'Weight Room', 100),
(3, 'Cardio Room', 25),
(4, 'Pilates Room', 25),
(5, 'CrossFit Room', 25);

INSERT INTO Classes (class_id, class_name, instructor_id, class_type, class_description, class_capacity, start_time, end_time, class_date)
VALUES
(1, 'Yoga Class', 2, 'Yoga', 'Relaxing yoga session', 20, '08:00:00', '09:00:00', '2024-04-12'),
(2, 'Weightlifting Class', 1, 'Weightlifting', 'Strength training with weights', 15, '10:00:00', '11:00:00', '2024-04-12'),
(3, 'Cardio Class', 3, 'Cardio', 'High-intensity cardio workout', 25, '13:00:00', '14:00:00', '2024-04-12'),
(4, 'Pilates Class', 4, 'Pilates', 'Core-strengthening exercises', 15, '11:00:00', '12:00:00', '2024-04-12'),
(5, 'CrossFit Class', 5, 'CrossFit', 'Functional fitness training', 20, '09:00:00', '10:00:00', '2024-04-12');

INSERT INTO Billing (bill_id, member_id, amount, bill_date, payment_status)
VALUES
(1, 1, 50.00, '2024-04-01', 'Unpaid'),
(2, 2, 75.00, '2024-04-05', 'Unpaid'),
(3, 3, 100.00, '2024-04-10', 'Unpaid'),
(4, 4, 60.00, '2024-04-15', 'Unpaid'),
(5, 5, 80.00, '2024-04-20', 'Unpaid');

INSERT INTO Equipment (equipment_id, equipment_name, status)
VALUES
(1, 'Treadmill', 'Available'),
(2, 'Dumbbells', 'In use'),
(3, 'Yoga Mats', 'Available'),
(4, 'Pilates Reformer', 'Available'),
(5, 'CrossFit Racks', 'In use');

INSERT INTO EquipmentMaintenance (maintenance_id, equipment_id, maintenance_date, description)
VALUES
(1, 1, '2024-03-01', 'Treadmill belt replacement'),
(2, 3, '2024-03-05', 'Yoga mat cleaning'),
(3, 4, '2024-03-10', 'Pilates reformer maintenance'),
(4, 5, '2024-03-15', 'CrossFit rack repairs'),
(5, 2, '2024-03-20', 'Dumbbell handle replacement');

INSERT INTO RoomBookings (booking_id, room_id, activity, start_time, end_time)
VALUES
(1, 1, 'Yoga Class', '08:00:00', '09:00:00'),
(2, 2, 'Weightlifting Class', '10:00:00', '11:00:00'),
(3, 3, 'Cardio Class', '13:00:00', '14:00:00'),
(4, 4, 'Pilates Class', '11:00:00', '12:00:00'),
(5, 5, 'CrossFit Class', '09:00:00', '10:00:00');
