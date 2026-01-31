// Step 1: Import JDBC classes from java.sql package
// These classes help Java communicate with the database

import java.sql.Connection; // Represents a live DB connection
import java.sql.DriverManager; // Utility class to get DB connection
import java.sql.ResultSet; // Holds data returned from SELECT query
import java.sql.SQLException; // Handles DB-related errors
import java.sql.Statement; // Used to execute SQL statements

public class InsertdemoWithExplanation {
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String username = "system";
	static String password = "tiger";

	public static void main(String[] args) {

		try {
			// =====================================================
			// STEP 2: LOAD ORACLE JDBC DRIVER
			// =====================================================
			// oracle.jdbc.driver.OracleDriver is the Oracle JDBC driver class
			// Class.forName() loads the driver into JVM memory
			// Driver gets registered with DriverManager automatically

			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver is loaded");

			// =====================================================
			// STEP 3: ESTABLISH DATABASE CONNECTION
			// =====================================================
			// DriverManager.getConnection() creates a connection object
			// URL format:
			// jdbc:oracle:thin:@host:port:SID
			//
			// localhost -> database is on same machine
			// 1521 -> default Oracle port
			// XE -> Oracle Express Edition SID
			Connection con = DriverManager.getConnection(url, username,
					password);
			// Connection con = DriverManager.getConnection(
			// "jdbc:oracle:thin:@localhost:1521:XE",
			// "system", // username
			// "admin" // password
			// );

			System.out.println("Got database connection");

			// =====================================================
			// STEP 4: CREATE STATEMENT OBJECT
			// =====================================================
			// Statement is used to send SQL commands to database
			// Created using Connection object

			Statement st = con.createStatement();

			// =====================================================
			// STEP 5: EXECUTE INSERT STATEMENTS
			// =====================================================
			// execute() is used for INSERT, UPDATE, DELETE
			// Return type: boolean (ignored here)

			 st.execute("INSERT INTO EMP(EMP_ID, NAME, SALARY) VALUES (106, 'KUMAR', 6000)");
			 st.execute("INSERT INTO EMP(EMP_ID, NAME, SALARY) VALUES (107, 'RAMA', 7000)");

			// =====================================================
			// STEP 6: EXECUTE SELECT QUERY
			// =====================================================
			// executeQuery() is used ONLY for SELECT
			// It returns a ResultSet object

			ResultSet rs = st.executeQuery("SELECT * FROM EMP");

			// =====================================================
			// STEP 7: READ DATA FROM RESULTSET
			// =====================================================
			// ResultSet cursor initially points before first row
			// rs.next() moves cursor to next row
			// Returns true if row exists

			while (rs.next()) {

				// Reading data using column index (starts from 1)
				int id = rs.getInt(1); // EMP_ID

				// Reading data using column index
				String name = rs.getString(2); // NAME

				// Reading data using column name
				int sal = rs.getInt("SALARY");

				// Display fetched record
				System.out.println(id + "\t" + name + "\t" + sal);
			}

			// =====================================================
			// STEP 8: CLOSE RESOURCES (VERY IMPORTANT)
			// =====================================================
			// Always close in reverse order of creation

			rs.close(); // closes ResultSet
			st.close(); // closes Statement
			con.close(); // closes Connection

		}
		// =====================================================
		// STEP 9: EXCEPTION HANDLING
		// =====================================================
		catch (ClassNotFoundException e) {
			// Occurs if JDBC driver is not found in classpath
			System.out.println("Driver not found: " + e);
		} catch (SQLException e) {
			// Occurs due to SQL errors (wrong query, DB down, etc.)
			System.out.println("SQL Error: " + e);
		}
	}
}