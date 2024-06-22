package dao;

import core.Db;
import entity.Reservation;
import entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection con;

    public ReservationDao() {
        this.con = Db.getInstance();
    }


    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY id ASC");
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return reservationList;
    }

    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ? ";
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

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation" +
                "(" +
                "room_id," +
                "check_in_date," +
                "check_out_date," +
                "total_price," +
                "guest_count," +
                "guest_name," +
                "guest_citizen_id," +
                "guest_mail," +
                "guest_phone" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheck_in_date()));
            pr.setDate(3, Date.valueOf(reservation.getCheck_out_date()));
            pr.setDouble(4, reservation.getTotal_price());
            pr.setInt(5, reservation.getGuest_count());
            pr.setString(6, reservation.getGuest_name());
            pr.setString(7, reservation.getGuest_citizen_id());
            pr.setString(8, reservation.getGuest_mail());
            pr.setString(9, reservation.getGuest_phone());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET" +
                " room_id = ?," +
                "check_in_date = ?," +
                "check_out_date = ?," +
                "total_price = ?," +
                "guest_count = ?," +
                "guest_name = ?," +
                "guest_citizen_id = ?," +
                "guest_mail = ?," +
                "guest_phone = ?" +
                " WHERE id = ?";

        try {
            try (PreparedStatement pr = this.con.prepareStatement(query)) {
                pr.setInt(1, reservation.getRoom_id());
                pr.setDate(2, Date.valueOf(reservation.getCheck_in_date()));
                pr.setDate(3, Date.valueOf(reservation.getCheck_out_date()));
                pr.setDouble(4, reservation.getTotal_price());
                pr.setInt(5, reservation.getGuest_count());
                pr.setString(6, reservation.getGuest_name());
                pr.setString(7, reservation.getGuest_citizen_id());
                pr.setString(8, reservation.getGuest_mail());
                pr.setString(9, reservation.getGuest_phone());
                pr.setInt(10, reservation.getId());

                return pr.executeUpdate() != -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Reservation> getByListReservationId(int reservationId) {
        return this.selectByQuery("SELECT * FROM public.room WHERE id = " + reservationId);
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("id"));
        reservation.setRoom_id(rs.getInt("room_id"));
        reservation.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        reservation.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        reservation.setTotal_price(Double.parseDouble(rs.getString("total_price")));
        reservation.setGuest_count(rs.getInt("guest_count"));
        reservation.setGuest_name(rs.getString("guest_name"));
        reservation.setGuest_citizen_id(rs.getString("guest_citizen_id"));
        reservation.setGuest_mail(rs.getString("guest_mail"));
        reservation.setGuest_phone(rs.getString("guest_phone"));
        return reservation;
    }
}
