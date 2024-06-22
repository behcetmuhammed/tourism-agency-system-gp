package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class SeasonDao {
    private final Connection con;

    private final HotelDao hotelDao = new HotelDao();

    public SeasonDao() {
        this.con = Db.getInstance();
    }


    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.hotel_season ORDER BY id ASC");
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> pensionList = new ArrayList<>();
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

    public boolean save(Season season) {
        String query = "INSERT INTO public.hotel_season" +
                "(" +
                "hotel_id," +
                "start_date," +
                "finish_date" +

                ")" +
                " VALUES (?,?,?)";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean update(Season season) {
        String query = "UPDATE public.hotel_season SET" +
                " hotel_id = ?," +
                " start_date = ?," +
                " finish_date = ?," +
                " WHERE id = ?";

        try {
            try (PreparedStatement pr = this.con.prepareStatement(query)) {
                pr.setInt(1, season.getHotel_id());
                pr.setDate(2, Date.valueOf(season.getStart_date()));
                pr.setDate(3, Date.valueOf(season.getFinish_date()));
                return pr.executeUpdate() != -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setId(rs.getInt("id"));
        season.setHotel_id(rs.getInt("hotel_id"));
        season.setHotel(this.hotelDao.getById(rs.getInt("hotel_id")));
        season.setStart_date(LocalDate.parse(rs.getString("start_date")));
        season.setFinish_date(LocalDate.parse(rs.getString("finish_date")));
        return season;
    }

    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE id = ? ";
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

    public ArrayList<Season> getSeasonsByOtelId(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }
}
