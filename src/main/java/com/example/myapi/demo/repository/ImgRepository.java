package com.example.myapi.demo.repository;

import com.example.myapi.demo.model.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.example.myapi.demo.utility.Constants.*;

public class ImgRepository {
    private static Connection _connection;

    public ImgRepository() {
    }

    public boolean addImg(Image image) {
        try {
            sqlConnection();
            String sql = "INSERT INTO images (img) VALUES (?)";
            PreparedStatement statement = _connection.prepareStatement(sql);
            statement.setBytes(1, image.getImage());
            return (statement.executeUpdate() > 0) ? true : false;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return false;    //duplicate entry
            } else {
                return false;    //other errors
            }
        }
    }

    public static void sqlConnection() {
        try {
            _connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // connection issues
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // handle any other exceptions
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
