package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	//Database connection......................................................................
	private Connection connect(){
		 Connection con = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection( "jdbc:mysql://localhost/paf?useTimezone=true&serverTimezone=UTC", "root", "");
		 }
		 catch (Exception e){
			 e.printStackTrace();
			 }
		 return con;
	 }
	
	
	

	//Read users......................................................................................
	public String readUsers(){
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr><th>User ID</th>"
					+ "<th>User Name</th>" 
					+ "<th>User Mail</th>"
					+ "<th>User Type</th>"+
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from usermanagement";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String id = rs.getString("UserId");
				String name= rs.getString("UserName");
				String mail = rs.getString("UserMail");
				String type = rs.getString("UserType");

				// Add into the html table
				output += "<tr><td>" + id+ "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + mail + "</td>";
				output += "<td>" + type + "</td>";

				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btnUpdate btn btn-secondary' data-userid='" + id + "'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' "
							+ "class='btnRemove btn btn-danger' data-userid='" + id + "'></td></tr>"; 
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the user";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//insert users................................................................................
		public String insertUsers( String UserName, String UserMail,String UserType){
			String output = "";
			try{
				Connection con = connect();
				if (con == null){
					return "Error while connecting to the database for inserting."; 
				}
				// create a prepared statement
				String query = " INSERT INTO usermanagement(`UserID`, `UserName`, `UserMail`, `UserType`) VALUES (?, ?, ?, ?);";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2,UserName);
				preparedStmt.setString(3,UserMail);
				preparedStmt.setString(4,UserType);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e){
				
				System.out.println("fhjdfhdjfh");
				output = "Error while inserting the user";
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			return output;
		}
		
	//Update User details ................................................................................
	public String updateUsers(String id, String UserName, String UserMail, String UserType){
	
		String output = "";
		try{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE usermanagement SET UserName=?,UserMail=?,UserType=? WHERE UserId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, UserName);
			preparedStmt.setString(2, UserMail);
			preparedStmt.setString(3, UserType);
			preparedStmt.setString(4, id);
			System.out.println("no error1");
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the user";
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return output;
	}
	
	//Delete User Details......................................................................................
	public String deleteUsers(String ID){
			String output = "";
			try{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				// create a prepared statement
				String query = "delete from usermanagement where UserID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1,Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the user";
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			return output;
		} 
	
}

