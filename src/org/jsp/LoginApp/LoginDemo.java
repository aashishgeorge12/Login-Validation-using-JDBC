package org.jsp.LoginApp;

import java.sql.*;
import java.util.Scanner;

public class LoginDemo {

	public static void main(String[] args) 
	{
		System.out.println("enter the name");
		Scanner sc=new Scanner(System.in);
		String nm=sc.nextLine();
		System.out.println("enter the password");
		String pw=sc.nextLine();
		sc.close();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String qry="select * from btm.user where name=? and password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt=con.prepareStatement(qry);
			
			pstmt.setString(1, nm); //set values to placeholder
			pstmt.setString(2,pw);
			
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				String un=rs.getString(1);//or use ("username")
				System.out.println("welcome "+un);
			}
			else
			{
				System.out.println("invalid user/password");
			}
			
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
