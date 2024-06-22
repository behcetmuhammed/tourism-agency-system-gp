package business;

import core.Helper;
import dao.RoomDao;
import entity.Hotel;
import entity.Pension;
import entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class RoomManager {
    private final RoomDao roomDao;

    private HotelManager hotelManager;
    private PensionManager pensionManager;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public boolean save(Room room) {
        if (room.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {

            Helper.showMsg("notFound");
        }
        return this.roomDao.update(room);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) {
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for (Room room : roomList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            Hotel hotel = this.hotelManager.getById(room.getHotel_id());
            Pension pension = this.pensionManager.getById(room.getPension_id());
            rowObject[i++] = room.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = pension.getPension_type();
            rowObject[i++] = room.getSeason_id();
            rowObject[i++] = room.getType();
            rowObject[i++] = room.getStock();
            rowObject[i++] = room.getAdult_price();
            rowObject[i++] = room.getChild_price();
            rowObject[i++] = room.getBed_capacity();
            rowObject[i++] = room.getSquare_meter();
            rowObject[i++] = room.isTelevision();
            rowObject[i++] = room.isMinibar();
            rowObject[i++] = room.isGame_console();
            rowObject[i++] = room.isSafe_box();
            rowObject[i++] = room.isProjection();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> searchForTable(String hotelName, String cityAdress, String checkinDate, String checkoutDate, String adultNum, String childNum) {
        String query = "SELECT * from public.room r " +
                "LEFT JOIN public.hotel h ON r.hotel_id = h.id " +
                "LEFT JOIN public.hotel_season s ON r.season_id = s.id WHERE";
        //"LEFT JOIN public.hotel_pension p ON r.pension_id = p.id ";

        ArrayList<String> whereList = new ArrayList<>();

        whereList.add(" r.stock > " + 0);

        checkinDate = LocalDate.parse(checkinDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        checkoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        whereList.add(" AND s.start_date <= '" + checkinDate + "'");
        whereList.add(" AND s.finish_date >= '" + checkoutDate + "'");

        if (hotelName != null) {
            whereList.add(" AND h.name ILIKE '%" + hotelName + "%'");
        }
        if (cityAdress != null) {
            whereList.add(" AND h.adress ILIKE '%" + cityAdress + "%'");
        }

        query += String.join("", whereList);

        if (adultNum != null && !adultNum.isEmpty() && childNum != null && !childNum.isEmpty()) {
            try {
                int adultNumber = Integer.parseInt(adultNum);
                int childNumber = Integer.parseInt(childNum);
                int totalNumber = adultNumber + childNumber;
                whereList.add(" AND r.bed_capacity >= '" + (totalNumber) + "'");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Room> queryResult = this.roomDao.selectByQuery(query);
        return queryResult;
    }
}
