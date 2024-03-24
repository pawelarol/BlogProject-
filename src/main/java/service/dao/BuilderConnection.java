package service.dao;

import service.config.ConfigDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BuilderConnection {
    public static Connection getConnection() throws SQLException{
       Connection con = null;
       try{
           con = DriverManager.getConnection(
                   ConfigDao.getProperties(ConfigDao.url),
                   ConfigDao.getProperties(ConfigDao.userName),
                   ConfigDao.getProperties(ConfigDao.password));

           return con;
           } catch (SQLException e){
           throw new RuntimeException(e);
       }
    }
}
