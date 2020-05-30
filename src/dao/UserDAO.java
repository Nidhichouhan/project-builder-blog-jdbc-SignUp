package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface{

	@Override
	public int signUp(User user) {
		try {
		Connection con=null;
		con=ConnectionManager.getConnection();
		int result=0;
		
		String sql="insert into users(email,password)values(?,?)";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, user.getEmail());
		pst.setString(2, user.getPassword());
		result=pst.executeUpdate();
		return result;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	@Override
	public boolean loginUser(User user) {
		
		try {
			Connection con=ConnectionManager.getConnection();
			boolean result=false;
			PreparedStatement st=con.prepareStatement("select * from users where email=? and password=?");
			st.setString(1, user.getEmail());
			st.setString(2, user.getPassword());
			
			ResultSet rs=st.executeQuery();
			result=rs.next();
			
			return result;
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
}