package dao;

import core.Db;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";

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


    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel" +
                "(" +
                "name, " +
                "adress, " +
                "mail, " +
                "phone_number, " +
                "star, " +
                "park, " +
                "wifi, " +
                "pool, " +
                "fitness, " +
                "concierge, " +
                "spa, " +
                "room_service " +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAdress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone_number());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isPark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoom_service());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setAdress(rs.getString("adress"));
        obj.setMail(rs.getString("mail"));
        obj.setPhone_number(rs.getString("phone_number"));
        obj.setStar(rs.getString("star"));
        obj.setPark(rs.getBoolean("park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoom_service(rs.getBoolean("room_service"));

        return obj;
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> userList = new ArrayList<>();
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

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ? ";
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

}
