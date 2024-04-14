# FitnessManagement COMP 3005 Final Project
Aidan Chow 
101237753
Final Project Report ↓

# ER-Diagram
![image](https://github.com/kachowchow/fitnessManagement/assets/112474998/a4e3eece-de41-44e8-9a74-429963d378cf)

# Reduction to Relation Schemas
Members:  
member_id (Primary Key)  
username  
password  
email  
name  
address  
date_of_birth  
fitness_goal  
height  
weight  

Trainers:  
trainer_id (Primary Key)  
name  
email  
phone_number  
specialization  

TrainerAvailability:  
availability_id (Primary Key)  
trainer_id (Foreign Key - references Trainers(trainer_id))  
start_time  
end_time  

Rooms:  
room_id (Primary Key)  
room_name  
capacity  

Classes:  
class_id (Primary Key)  
class_name  
instructor_id (Foreign Key - references Trainers(trainer_id))  
class_type  
class_description  
class_capacity  
start_time  
end_time  
class_date  

Billing:  
bill_id (Primary Key)  
member_id (Foreign Key - references Members(member_id))  
amount  
bill_date  
payment_status  

Equipment:  
equipment_id (Primary Key)  
equipment_name  
status  

EquipmentMaintenance:  
maintenance_id (Primary Key)  
equipment_id (Foreign Key - references Equipment(equipment_id))  
maintenance_date  
description  

RoomBookings:  
booking_id (Primary Key)  
room_id (Foreign Key - references Rooms(room_id))  
activity  
start_time  
end_time  

# DDL File

[SQL/DDL.sql](https://github.com/kachowchow/fitnessManagement/blob/23005997e2bed901ff3e18e08e87a83e021eb356/SQL/DDL.sql)

# DML File

https://github.com/kachowchow/fitnessManagement/blob/c25b321495ab871c6f14cfdaa813cad7701fab41/SQL/DML.sql
