package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;
    private final HotelDao hotelDao = new HotelDao();

    public PensionDao() {
        this.con = Db.getInstance();
    }


    public ArrayList<Pension> findAll() {
        return this.selectByQuery("SELECT * FROM public.hotel_pension ORDER BY id ASC");
    }

    public ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return pensionList;
    }

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.hotel_pension WHERE id = ? ";
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

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.hotel_pension" +
                "(" +
                "hotel_id," +
                "pension_type" +
                ")" +
                " VALUES (?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getHotel_id());
            pr.setString(2, pension.getPension_type());


            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public boolean update(Pension pension) {
        String query = "UPDATE public.hotel_pension SET" +
                " hotel_id = ?," +
                " pension_type = ?," +
                " WHERE id = ?";

        try {
            try (PreparedStatement pr = this.con.prepareStatement(query)) {
                pr.setInt(1, pension.getHotel_id());
                pr.setString(2, pension.getPension_type());
                return pr.executeUpdate() != -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pension> getByListPensionId(int pensionId) {
        return this.selectByQuery("SELECT * FROM public.hotel_pension WHERE id = " + pensionId);
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setId(rs.getInt("id"));
        pension.setHotel_id(rs.getInt("hotel_id"));
        pension.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        pension.setPension_type(String.valueOf(Pension.PensionType.valueOf(rs.getString("pension_type"))));
        return pension;
    }

    public ArrayList<Pension> getPensionsByOtelId(int otelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_pension WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }

}

