package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;


    //Constructor UserDao
    public UserDao() {
        this.con = Db.getInstance();
    }


    //veritabanındaki tüm User verilerini çekmek
    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";

        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    //Sorgu Seçmek
    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return userList;
    }


    //Giriş kontrol
    public User findByLogin(String userName, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement preparedStatement = this.con.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    //İD ye göre..
    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) obj = this.match(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }


    //Kaydet
    public boolean save(User user) {
        String query = "INSERT INTO public.user" +
                "(" +
                "user_name," +
                "user_password," +
                "user_role" +
                ")" +
                "VALUES (?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    //Güncelle
    public boolean update(User user) {
        String query = "UPDATE public.user SET" +
                " user_name = ?," +
                " user_password = ?," +
                " user_role = ?" +
                " WHERE user_id = ?";

        try {
            try (PreparedStatement pr = this.con.prepareStatement(query)) {
                pr.setString(1, user.getUsername());
                pr.setString(2, user.getPassword());
                pr.setString(3, user.getRole());
                pr.setInt(4, user.getId());
                return pr.executeUpdate() != -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    //Silme
    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    //tüm verileri kaşılaştırır
    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(rs.getString("user_role"));
        return obj;
    }
}
