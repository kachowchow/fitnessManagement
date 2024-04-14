package project;
import java.sql.*;

public class FitnessManagement {
    static String url = "jdbc:postgresql://localhost:5432/project";
    static String user = "postgres";
    static String password = "admin";

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public static Connection Connect() throws Exception{
        return DriverManager.getConnection(url, user, password);
    }

    //disconnect function
    public static void Disconnect(Connection connection){
        try{
            if (connection != null)
                connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void close() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (conn != null) conn.close();
    }

    //TODO: DISPLAY FUNCTIONS
    public void viewMember(String name) throws SQLException {
        String query = "SELECT * FROM Members WHERE name LIKE ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, "%" + name + "%");
        resultSet = preparedStatement.executeQuery();
    
        // Print header
        System.out.println("Member Details:");
        System.out.println("======================================================================================================");
        System.out.println("ID\tUsername\tEmail\t\tName\t\tAddress\t\tBirth Date");
        System.out.println("------------------------------------------------------------------------------------------------------");
    
        while (resultSet.next()) {
            int memberID = resultSet.getInt("member_id");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String memberName = resultSet.getString("name");
            String address = resultSet.getString("address");
            String birthDate = resultSet.getString("date_of_birth");
    
            System.out.println(memberID + "\t" + username + "\t" + email + "\t" + memberName + "\t" + address + "\t" + birthDate);
        }
        System.out.println("======================================================================================================");
    }

    public void healthStats(int memberID) throws SQLException {
        String query = "SELECT height, weight FROM Members WHERE member_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next() == false)
            System.out.println("Member ID not Found");
        
        double height = resultSet.getDouble("height");
        double weight = resultSet.getDouble("weight");
        
        System.out.println("Member ID: " + memberID);
        System.out.println("Height: " + height + " cm");
        System.out.println("Weight: " + weight + " kg");
    }

    public void viewClass() throws SQLException {
        String query = "SELECT * FROM Classes";
        preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        System.out.println("Classes:");
        while (resultSet.next()) {
            int classID = resultSet.getInt("class_id");
            String className = resultSet.getString("class_name");
            int instructorID = resultSet.getInt("instructor_id");
            String classType = resultSet.getString("class_type");
            String classDescription = resultSet.getString("class_description");
            int classCapacity = resultSet.getInt("class_capacity");
            Time startTime = resultSet.getTime("start_time");
            Time endTime = resultSet.getTime("end_time");
            Date classDate = resultSet.getDate("class_date");
            
            System.out.println("Class ID: " + classID);
            System.out.println("Class Name: " + className);
            System.out.println("Instructor ID: " + instructorID);
            System.out.println("Class Type: " + classType);
            System.out.println("Class Description: " + classDescription);
            System.out.println("Class Capacity: " + classCapacity);
            System.out.println("Start Time: " + startTime);
            System.out.println("End Time: " + endTime);
            System.out.println("Class Date: " + classDate);
            System.out.println("--------------------------------------");
        }
    }

    public void viewTrainers() throws SQLException {
        String query = "SELECT * FROM Trainers";
        preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        System.out.println("Trainers:");
        while (resultSet.next()) {
            int trainerID = resultSet.getInt("trainer_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String specialization = resultSet.getString("specialization");
            
            System.out.println("Trainer ID: " + trainerID);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Specialization: " + specialization);
            System.out.println("--------------------------------------");
        }
    }

    public void viewEquipment() throws SQLException {
        String query = "SELECT * FROM Equipment";
        preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        System.out.println("Equipment:");
        while (resultSet.next()) {
            int equipmentID = resultSet.getInt("equipment_id");
            String equipmentName = resultSet.getString("equipment_name");
            String status = resultSet.getString("status");
            
            System.out.println("Equipment ID: " + equipmentID);
            System.out.println("Equipment Name: " + equipmentName);
            System.out.println("Status: " + status);
            System.out.println("--------------------------------------");
        }
    }

    public void viewRooms() throws SQLException {
        String query = "SELECT * FROM Rooms";
        preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        System.out.println("Rooms:");
        while (resultSet.next()) {
            int roomID = resultSet.getInt("room_id");
            String roomName = resultSet.getString("room_name");
            int capacity = resultSet.getInt("capacity");
            
            System.out.println("Room ID: " + roomID);
            System.out.println("Room Name: " + roomName);
            System.out.println("Capacity: " + capacity);
            System.out.println("--------------------------------------");
        }
    }

    //TODO: ADD FUNCTIONS
    public void registerMember(int memberID, String username, String password, String email, String name, String address, String birthDate, String fitnessGoal, double height, double weight) throws SQLException{
        String query = "INSERT INTO Members (member_id, username, password, email, name, address, date_of_birth, fitness_goal, height, weight) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            java.sql.Date sqlDate = java.sql.Date.valueOf(birthDate);

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, memberID);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, name);
            preparedStatement.setString(6, address);
            preparedStatement.setDate(7, sqlDate);
            preparedStatement.setString(8, fitnessGoal);
            preparedStatement.setDouble(9, height);
            preparedStatement.setDouble(10, weight);
            preparedStatement.executeUpdate();

        } catch (Exception e){
            System.out.println(e);
        } finally {
            if (preparedStatement != null) 
                preparedStatement.close();
        }
    }

    public void addEquipment(String equipmentName) throws SQLException {
        String query = "INSERT INTO Equipment (equipment_name, status) VALUES (?, 'Available')";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, equipmentName);
        preparedStatement.executeUpdate();
        System.out.println("Equipment added");
    }

    public void addTrainer(int trainerID, String name, String email, String phoneNumber, String specialization) throws SQLException {
        String query = "INSERT INTO Trainers (trainer_id, name, email, phone_number, specialization) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, trainerID);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, phoneNumber);
        preparedStatement.setString(5, specialization);
        preparedStatement.executeUpdate();
        System.out.println("Trainer added");
    }

    public void addRoom(int roomID, String roomName, int capacity) throws SQLException {
        String query = "INSERT INTO Rooms (room_id, room_name, capacity) VALUES (?, ?, ?)";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, roomID);
        preparedStatement.setString(2, roomName);
        preparedStatement.setInt(3, capacity);
        preparedStatement.executeUpdate();
        System.out.println("Room added");
    }

    public void addClass(String className, int instructorID, String classType, String classDescription, int classCapacity, String startTime, String endTime, String classDate) throws SQLException {
        String query = "INSERT INTO Classes (class_name, instructor_id, class_type, class_description, class_capacity, start_time, end_time, class_date) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.Date sqlDate = java.sql.Date.valueOf(classDate);
        java.sql.Time sqlStartTime = java.sql.Time.valueOf(startTime);
        java.sql.Time sqlEndTime = java.sql.Time.valueOf(endTime);

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, className);
        preparedStatement.setInt(2, instructorID);
        preparedStatement.setString(3, classType);
        preparedStatement.setString(4, classDescription);
        preparedStatement.setInt(5, classCapacity);
        preparedStatement.setTime(6, sqlStartTime);
        preparedStatement.setTime(7, sqlEndTime);
        preparedStatement.setDate(8, sqlDate);
        preparedStatement.executeUpdate();
        System.out.println("Class added");
    }

    public void bookRoom(int roomID, String activity, String startTime, String endTime) throws SQLException {
        //check if roomID is in database
        String query = "SELECT room_id FROM ROOMS WHERE room_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, roomID);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            query = "INSERT INTO RoomBookings (room_id, activity, start_time, end_time) VALUES (?, ?, ?, ?)";

            java.sql.Time sqlStartTime = java.sql.Time.valueOf(startTime);
            java.sql.Time sqlEndTime = java.sql.Time.valueOf(endTime);

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, roomID);
            preparedStatement.setString(2, activity);
            preparedStatement.setTime(3, sqlStartTime);
            preparedStatement.setTime(4, sqlEndTime);
            preparedStatement.executeUpdate();
            System.out.println("Room booked");
        } else {
            System.out.println("RoomID doesn't exist");
        }
    }

    //TODO: UPDATE/SET/DELETE FUNCTIONS
    public void updateMember(int memberID, String email, String address) throws SQLException{
        String query = "UPDATE Members SET email = ?, address = ? WHERE member_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, address);
        preparedStatement.setInt(3, memberID);
        preparedStatement.executeUpdate();
    }

    public void deleteMember(int memberID) throws SQLException {
        String query = "DELETE FROM Members WHERE member_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("MemberID: " + memberID + " deleted");
        } else {
            System.out.println("MemberID: " + memberID + " not found");
        }
    }

    public void updateClass(int classID, String className, int instructorID, String classType, String classDescription, int classCapacity, String startTime, String endTime, String classDate) throws SQLException {
        String query = "UPDATE Classes SET class_name = ?, instructor_id = ?, class_type = ?, class_description = ?, " + "class_capacity = ?, start_time = ?, end_time = ?, class_date = ? WHERE class_id = ?";

        java.sql.Date sqlDate = java.sql.Date.valueOf(classDate);
        java.sql.Time sqlStartTime = java.sql.Time.valueOf(startTime);
        java.sql.Time sqlEndTime = java.sql.Time.valueOf(endTime);

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, className);
        preparedStatement.setInt(2, instructorID);
        preparedStatement.setString(3, classType);
        preparedStatement.setString(4, classDescription);
        preparedStatement.setInt(5, classCapacity);
        preparedStatement.setTime(6, sqlStartTime);
        preparedStatement.setTime(7, sqlEndTime);
        preparedStatement.setDate(8, sqlDate);
        preparedStatement.setInt(9, classID);
        preparedStatement.executeUpdate();
        System.out.println("Class updated");
    }

    public void setFitnessGoal(int memberID, String fitnessGoal) throws SQLException {
        String query = "UPDATE Members SET fitness_goal = ? WHERE member_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, fitnessGoal);
        preparedStatement.setInt(2, memberID);
        preparedStatement.executeUpdate();
    }

    public void setHealthMetrics (int memberID, double height, double weight) throws SQLException{
        String query = "UPDATE Members SET height = ?, weight = ? WHERE member_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setDouble(1, height);
        preparedStatement.setDouble(2, weight);
        preparedStatement.setInt(3, memberID);
        preparedStatement.executeUpdate();
    }

    public void setAvailability (int trainerID, String startTime, String endTime) throws SQLException{
        String query = "INSERT INTO TrainerAvailability (trainer_id, start_time, end_time) VALUES (?, ?, ?)";

        java.sql.Time sqlStartTime = java.sql.Time.valueOf(startTime);
        java.sql.Time sqlEndTime = java.sql.Time.valueOf(endTime);
        
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, trainerID);
        preparedStatement.setTime(2, sqlStartTime);
        preparedStatement.setTime(3, sqlEndTime);
        preparedStatement.executeUpdate();
    }

    public void setEquipmentStatus(int equipmentID, String status) throws SQLException {
        String query = "UPDATE Equipment SET status = ? WHERE equipment_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, equipmentID);
        preparedStatement.executeUpdate();
        System.out.println("Equipment status updated");
    }

    //TODO: BILLING FUNCTIONS
    public void generateBill(int memberID, double amount, String billDate) throws SQLException {
        String query = "INSERT INTO Billing (member_id, amount, bill_date, payment_status) VALUES (?, ?, ?, 'Unpaid')";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, memberID);
        preparedStatement.setDouble(2, amount);
        preparedStatement.setString(3, billDate);
        preparedStatement.executeUpdate();
        System.out.println("Bill for member ID: " + memberID);
    }

    public void updatePaymentStatus(int billID, String paymentStatus) throws SQLException {
        String query = "UPDATE Billing SET payment_status = ? WHERE bill_id = ?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, paymentStatus);
        preparedStatement.setInt(2, billID);
        preparedStatement.executeUpdate();
        System.out.println("Payment status updated for bill ID: " + billID);
    }

    public static void main(String[] args) {
        FitnessManagement FitnessManagement = new FitnessManagement();

        try {
            FitnessManagement.conn = Connect();
            
            // FitnessManagement.registerMember(1, "john_doe", "password123", "john@example.com", "John Doe", "123 Main St", "1990-01-01", "Lose weight", 180.0, 75.0);
            // FitnessManagement.registerMember(2, "jane_smith", "password456", "jane@example.com", "Jane Smith", "456 Elm St", "1992-05-15", "Build muscle", 170.0, 65.0);
            // FitnessManagement.registerMember(3, "mike_jackson", "password789", "mike@example.com", "Mike Jackson", "789 Oak St", "1985-09-22", "Improve endurance", 175.0, 80.0);
            // FitnessManagement.registerMember(4, "emily_wilson", "password123", "emily@example.com", "Emily Wilson", "101 Maple St", "1988-03-10", "Increase flexibility", 160.0, 60.0);
            // FitnessManagement.registerMember(5, "chris_adams", "password456", "chris@example.com", "Chris Adams", "202 Pine St", "1995-11-28", "Maintain weight", 185.0, 70.0); 

            // FitnessManagement.updateMember(1, "john.doe@example.com", "456 Elm St");

            // FitnessManagement.setFitnessGoal(1, "Gain muscle mass");

            // FitnessManagement.setHealthMetrics(1, 182.0, 80.0);

            // FitnessManagement.addTrainer(1, "John Trainer", "john_trainer@example.com", "1234567890", "Weightlifting");
            // FitnessManagement.addTrainer(2, "Jane Trainer", "jane_trainer@example.com", "9876543210", "Yoga");
            // FitnessManagement.addTrainer(3, "Mike Trainer", "mike_trainer@example.com", "4561237890", "Cardio");
            // FitnessManagement.addTrainer(4, "Emily Trainer", "emily_trainer@example.com", "7894561230", "Pilates");
            // FitnessManagement.addTrainer(5, "Chris Trainer", "chris_trainer@example.com", "3216549870", "CrossFit");

            // FitnessManagement.addRoom(1, "Yoga Room", 50);
            // FitnessManagement.addRoom(2, "Weight Room", 100);
            // FitnessManagement.addRoom(3, "Cardio Room", 25);
            // FitnessManagement.addRoom(4, "Pilates Room", 25);
            // FitnessManagement.addRoom(5, "CrossFit Room", 25);

            // FitnessManagement.addClass("Yoga Class", 1, "Yoga", "Beginner Level", 20, "10:00", "11:00", "1990-02-02");

            // FitnessManagement.addEquipment("Treadmill");

            // FitnessManagement.healthStats(1);

            // FitnessManagement.viewMember("John");
            // FitnessManagement.viewMember("Jane");
            // FitnessManagement.viewMember("Mike");
            // FitnessManagement.viewMember("Emily");
            // FitnessManagement.viewMember("Chris");

            // FitnessManagement.viewClass();

            // FitnessManagement.viewTrainers();

            // FitnessManagement.viewEquipment();

            // FitnessManagement.viewRooms();

            // FitnessManagement.bookRoom(1, "Yoga Class", "10:00", "11:00");

            // FitnessManagement.setEquipmentStatus(1, "In Use");

            // FitnessManagement.updateClass(1, "Yoga Class Updated", 1, "Yoga", "Intermediate Level", 30, "09:00", "10:00", "2024-04-13");

            // FitnessManagement.setAvailability(1, "08:00", "12:00");

            // FitnessManagement.generateBill(1, 50.00, "2024-04-12");

            // FitnessManagement.updatePaymentStatus(1, "Paid");

            // FitnessManagement.deleteMember(1);
            // FitnessManagement.deleteMember(2);
            // FitnessManagement.deleteMember(3);
            // FitnessManagement.deleteMember(4);
            // FitnessManagement.deleteMember(5);
        } catch (Exception e){
            System.out.println(e);
        } finally {
            try {
                FitnessManagement.close();
            } catch (Exception e ){
                System.out.println(e);
            }
        }
    }
}
