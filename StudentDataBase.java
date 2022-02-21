import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDataBase {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		try {
			
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// create a connection
			String url = "jdbc:mysql://localhost:3306/student_db";
			String username = "root";
			String password = "Ganesh@123";
			Connection con = DriverManager.getConnection(url, username, password);
			
			 
			// create a table using sql query String
			/*String q = "CREATE TABLE STUDENT(STUDENT_NO INT (3),STUDENT_NAME TEXT (30),STUDENT_DOB DATE,STUDENT_DOJ DATE)";
			java.sql.Statement stmt = con.createStatement();
			stmt.executeUpdate(q);
			System.out.println(" Table  created....");
			con.close();*/
			

			// Select your choice
			System.out.println("Select Your Choice To Perform The Operation.....\n");
			System.out.println("  1 . Insert student data into Student table.");
			System.out.println("  2 . Update student data into Student table.");
			System.out.println("  3 . Delete student data from Student table.");
			System.out.println("  4 . Get a list of all students.");
			System.out.println("  5 . Get one student information depending on the student id filter.");

			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {
				//Insert student data into Student table
				String q = "INSERT INTO STUDENT VALUES (?,?,?,?)";

				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter Student Number");
				int no = sc.nextInt();
				System.out.println("Enter Student Name ");
				String name = br.readLine();
				System.out.println("Enter the DOB");
				String DOB = br.readLine();
				System.out.println("Enter the DOJ");
				String DOJ = br.readLine();
				
				// get the prepared statement object
				PreparedStatement pstmt = con.prepareStatement(q);
				pstmt.setInt(1, no);
				pstmt.setString(2, name);
				pstmt.setString(3, DOB);
				pstmt.setString(4, DOJ);

				pstmt.executeUpdate();
				System.out.println(" Your Data Inserted Succesfully......");
				con.close();
				break;

			}
			case 2: {
				//Update student data into Student table
				String q = "UPDATE STUDENT SET STUDENT_NAME=?,STUDENT_DOB=?,STUDENT_DOJ=? WHERE STUDENT_NO=?";
				
			     //Acces the user input for update data
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter New Student Name ");
				String name = br.readLine();
				System.out.println("Enter the New DOB");
				String DOB = br.readLine();
				System.out.println("Enter the New  DOJ");
				String DOJ = br.readLine();
				System.out.println("Enter Student Number where We want to update detail");
				int no = sc.nextInt();
				
				//get the prepared statement object
				PreparedStatement pstmt = con.prepareStatement(q);
				pstmt.setString(1, name);
				pstmt.setString(2, DOB);
				pstmt.setString(3, DOJ);
				pstmt.setInt(4, no);
				pstmt.executeUpdate();
				System.out.println("Your Uata Updated suceesfully....");
				con.close();
				break;
			}
			
			
			case 3: {
				//Delete student data from Student table
				String q = "DELETE FROM STUDENT WHERE STUDENT_NO = ?";

				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter Student Number");
				int no = sc.nextInt();
				
				
				// get the prepared statement object
				PreparedStatement pstmt = con.prepareStatement(q);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
				System.out.println("Your Data  Deleted Succesfully......");
				con.close();
				break;

			}
			
			
				case 4: {
					//Get a list of all students
					String q = "SELECT * FROM STUDENT";
					Statement pstmt = con.createStatement();
					ResultSet set = pstmt.executeQuery(q);
	
					// Printing Student number , student name , student Dob ,student Doj  
					System.out.println("STUDENT_NO\tSTUDENT_NAME\t\t STUDENT_DOB \t\t STUDENT_DOJ");
	
					// Condition check
					while (set.next()) {
						int STUDENT_NO = set.getInt("STUDENT_NO");
						String STUDENT_NAME = set.getString("STUDENT_NAME");
						String STUDENT_DOB = set.getString("STUDENT_DOB");
						String STUDENT_DOJ = set.getString("STUDENT_DOJ");
						System.out.println(STUDENT_NO + "\t\t" + STUDENT_NAME + "\t\t" + STUDENT_DOB + "\t\t" + STUDENT_DOJ);
					}
					
					break;
				}

			
			
			case 5: {
				//Get one student information depending on the student id filter.
				System.out.println("Enter Student Number");
				int no = sc.nextInt();
				String q = "SELECT * FROM STUDENT WHERE STUDENT_NO = " + no;

				Statement pstmt = con.createStatement();
				ResultSet set = pstmt.executeQuery(q);

				System.out.println("STUDENT_NO\tSTUDENT_NAME\t STUDENT_DOB \t STUDENT_DOJ");

				// Condition check
				while (set.next()) {

					if (set.getInt("STUDENT_NO") == no) {
						int STUDENT_NO = set.getInt("STUDENT_NO");
						String STUDENT_NAME = set.getString("STUDENT_NAME");
						String STUDENT_DOB = set.getString("STUDENT_DOB");
						String STUDENT_DOJ = set.getString("STUDENT_DOJ");
						System.out.println(
								STUDENT_NO + "\t\t" + STUDENT_NAME + "\t\t " + STUDENT_DOB + "\t\t " + STUDENT_DOJ);
					}
				}
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			

		} catch (SQLException ex) {
			System.out.println("An error occurred while connecting MySQL databse");
			ex.printStackTrace();
		}

	}

}
