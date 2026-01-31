package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.User;
import com.revplay.util.DBConnection;

public class UserDao {

    // 🔹 REGISTER USER
    public boolean registerUser(User user) {
        String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔹 LOGIN
    public User login(String input, String password) {
        String sql = "SELECT * FROM USERS WHERE (EMAIL=? OR USERNAME=?) AND PASSWORD=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, input);
            ps.setString(2, input);
            ps.setString(3, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("USER_ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setRole(rs.getString("ROLE"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🆕 🔹 VERIFY USER FOR FORGOT PASSWORD
    public boolean verifyUser(String email, String username) {
        String sql = "SELECT USER_ID FROM USERS WHERE EMAIL=? AND USERNAME=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, username);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // user exists

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🆕 🔹 UPDATE PASSWORD
    public boolean updatePassword(String email, String newPassword) {
        String sql = "UPDATE USERS SET PASSWORD=? WHERE EMAIL=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}