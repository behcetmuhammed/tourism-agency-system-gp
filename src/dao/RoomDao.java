package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;

    private PensionDao pensionDao;
    private HotelDao hotelDao;

    public RoomDao() {
        this.con = Db.getInstance();
        this.pensionDao = new PensionDao();
        this.hotelDao = new HotelDao();
    }


    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room ORDER BY id ASC");
    }

    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return roomList;
    }

    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ? ";
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

    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square_meter," +
                "television," +
                "minibar," +
                "game_console," +
                "safe_box," +
                "projection" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isSafe_box());
            pr.setBoolean(14, room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean update(Room room) {
        String query = "UPDATE public.room SET" +
                " hotel_id = ?," +
                "pension_id = ?," +
                "season_id = ?," +
                "type = ?," +
                "stock = ?," +
                "adult_price = ?," +
                "child_price = ?," +
                "bed_capacity = ?," +
                "square_meter = ?," +
                "television = ?," +
                "minibar = ?," +
                "game_console = ?," +
                "safe_box = ?," +
                "projection = ?" +
                " WHERE id = ?";

        try {
            try (PreparedStatement pr = this.con.prepareStatement(query)) {
                pr.setInt(1, room.getHotel_id());
                pr.setInt(2, room.getPension_id());
                pr.setInt(3, room.getSeason_id());
                pr.setString(4, room.getType());
                pr.setInt(5, room.getStock());
                pr.setDouble(6, room.getAdult_price());
                pr.setDouble(7, room.getChild_price());
                pr.setInt(8, room.getBed_capacity());
                pr.setInt(9, room.getSquare_meter());
                pr.setBoolean(10, room.isTelevision());
                pr.setBoolean(11, room.isMinibar());
                pr.setBoolean(12, room.isGame_console());
                pr.setBoolean(13, room.isSafe_box());
                pr.setBoolean(14, room.isProjection());
                pr.setInt(15, room.getId());
                return pr.executeUpdate() != -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Room> getByListRoomId(int roomId) {
        return this.selectByQuery("SELECT * FROM public.room WHERE id = " + roomId);
    }

    public Room match(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setHotel_id(rs.getInt("hotel_id"));
        room.setPension_id(rs.getInt("pension_id"));
        room.setSeason_id(rs.getInt("season_id"));
        room.setType(rs.getString("type"));
        room.setStock(rs.getInt("stock"));
        room.setAdult_price(rs.getDouble("adult_price"));
        room.setChild_price(rs.getDouble("child_price"));
        room.setBed_capacity(rs.getInt("bed_capacity"));
        room.setSquare_meter(rs.getInt("square_meter"));
        room.setTelevision(rs.getBoolean("television"));
        room.setMinibar(rs.getBoolean("minibar"));
        room.setGame_console(rs.getBoolean("game_console"));
        room.setSafe_box(rs.getBoolean("safe_box"));
        room.setProjection(rs.getBoolean("projection"));
        room.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        room.setPension(this.pensionDao.getById(rs.getInt("pension_id")));
        return room;
    }
}
