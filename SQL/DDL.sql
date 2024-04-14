CREATE TABLE Members (
    member_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    name TEXT NOT NULL,
    address TEXT,
    date_of_birth DATE,
    fitness_goal TEXT,
    height REAL,
    weight REAL
);

CREATE TABLE Trainers (
    trainer_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone_number TEXT,
    specialization TEXT
);

CREATE TABLE TrainerAvailability (
    availability_id SERIAL PRIMARY KEY,
    trainer_id INTEGER REFERENCES Trainers(trainer_id),
    start_time TIME,
    end_time TIME
);

CREATE TABLE Rooms (
    room_id SERIAL PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL,
    capacity INTEGER
);

CREATE TABLE Classes (
    class_id SERIAL PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    instructor_id INTEGER REFERENCES Trainers(trainer_id),
    class_type VARCHAR(100),
    class_description TEXT,
    class_capacity INTEGER,
    start_time TIME,
    end_time TIME,
    class_date DATE
);

CREATE TABLE RoomBookings (
    booking_id SERIAL PRIMARY KEY,
    room_id INTEGER REFERENCES Rooms(room_id),
    activity VARCHAR(100),
    start_time TIME,
    end_time TIME
);

CREATE TABLE Equipment (
    equipment_id SERIAL PRIMARY KEY,
    equipment_name VARCHAR(100) NOT NULL,
    status VARCHAR(50)
);

CREATE TABLE EquipmentMaintenance (
    maintenance_id SERIAL PRIMARY KEY,
    equipment_id INTEGER REFERENCES Equipment(equipment_id),
    maintenance_date DATE,
    description TEXT
);

CREATE TABLE Billing (
    bill_id SERIAL PRIMARY KEY,
    member_id INTEGER REFERENCES Members(member_id),
    amount REAL,
    bill_date DATE,
    payment_status VARCHAR(50)
);