package business;

import core.Helper;
import dao.ReservationDao;
import dao.SeasonDao;
import entity.Reservation;
import entity.Season;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public boolean save(Reservation reservation) {
        if (reservation.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getId()) == null) {

            Helper.showMsg("notFound");
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + "ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.reservationDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservationList) {
        ArrayList<Object[]> reservationRowList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getRoom_id();
            rowObject[i++] = reservation.getCheck_in_date().toString();
            rowObject[i++] = reservation.getCheck_out_date().toString();
            rowObject[i++] = reservation.getTotal_price();
            rowObject[i++] = reservation.getGuest_count();
            rowObject[i++] = reservation.getGuest_name();
            rowObject[i++] = reservation.getGuest_citizen_id();
            rowObject[i++] = reservation.getGuest_mail();
            rowObject[i++] = reservation.getGuest_phone();
            reservationRowList.add(rowObject);
        }
        return reservationRowList;
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> getByListReservationId(int id) {
        return this.reservationDao.getByListReservationId(id);
    }
}
