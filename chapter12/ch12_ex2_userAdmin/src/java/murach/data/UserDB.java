package murach.data;

import java.sql.*;
import java.util.ArrayList;

import murach.business.User;

public class UserDB {

    public static int insert(User user) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
//      
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://ec2-44-210-228-110.compute-1.amazonaws.com:5432/d15h2pv37puhtc", "minpzmhtesgbqu", "dc8f60be953023ef2d21150b0b76ad126add6ebe62195440288a51cc92690e1c");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//

        String query
                = "INSERT INTO User1 (Email, FirstName, LastName) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://ec2-44-210-228-110.compute-1.amazonaws.com:5432/d15h2pv37puhtc", "minpzmhtesgbqu", "dc8f60be953023ef2d21150b0b76ad126add6ebe62195440288a51cc92690e1c");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User1 SET "
                + "FirstName = ?, "
                + "LastName = ? "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
        }
    }

    public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM User1 "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean emailExists(String email) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://ec2-44-210-228-110.compute-1.amazonaws.com:5432/d15h2pv37puhtc", "minpzmhtesgbqu", "dc8f60be953023ef2d21150b0b76ad126add6ebe62195440288a51cc92690e1c");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User1 "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
        }
    }

    public static User selectUser(String email) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://ec2-44-210-228-110.compute-1.amazonaws.com:5432/d15h2pv37puhtc", "minpzmhtesgbqu", "dc8f60be953023ef2d21150b0b76ad126add6ebe62195440288a51cc92690e1c");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User1 "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
        }
    }

    public static ArrayList<User> selectUsers() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://ec2-44-210-228-110.compute-1.amazonaws.com:5432/d15h2pv37puhtc", "minpzmhtesgbqu", "dc8f60be953023ef2d21150b0b76ad126add6ebe62195440288a51cc92690e1c");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User1";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
        }
    }
}
