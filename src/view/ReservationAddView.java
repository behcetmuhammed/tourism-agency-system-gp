package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationAddView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bot;
    private JPanel pnl_hotelinfo;
    private JPanel pnl_roominfo;
    private JLabel lbl_customerinfo;
    private JLabel lbl_hotelinfo;
    private JLabel lbl_roominfo;
    private JPanel pnl_customerinfo;
    private JFormattedTextField fld_hotelname;
    private JFormattedTextField fld_city;
    private JFormattedTextField fld_star;
    private JRadioButton parkRadioButton;
    private JRadioButton conciergeRadioButton;
    private JRadioButton wifiRadioButton;
    private JRadioButton spaRadioButton;
    private JRadioButton poolRadioButton;
    private JRadioButton roomServiceRadioButton;
    private JRadioButton fitnessRadioButton;
    private JFormattedTextField fld_roomtype;
    private JFormattedTextField fld_bedcapacity;
    private JFormattedTextField fld_pensiontype;
    private JFormattedTextField fld_m2;
    private JFormattedTextField fld_checkin;
    private JFormattedTextField fld_checkout;
    private JFormattedTextField fld_totalprice;
    private JRadioButton tvRadioButton;
    private JRadioButton minibarRadioButton;
    private JRadioButton gameConsolRadioButton;
    private JRadioButton projectionRadioButton;
    private JRadioButton safeBoxRadioButton;
    private JFormattedTextField fld_customertotal;
    private JFormattedTextField fld_customername;
    private JFormattedTextField fld_customerid;
    private JFormattedTextField fld_customermail;
    private JFormattedTextField fld_customerphone;
    private JLabel lbl_hotelname;
    private JLabel lbl_city;
    private JLabel lbl_star;
    private JLabel lbl_roomtype;
    private JLabel lbl_bedcapacity;
    private JLabel lbl_pensiontype;
    private JLabel lbl_m2;
    private JLabel lbl_checkin;
    private JLabel lbl_checkout;
    private JLabel lbl_totalprice;
    private JLabel lbl_totalcnumber;
    private JLabel lbl_cmail;
    private JLabel lbl_cname;
    private JLabel lbl_pnumber;
    private JLabel lbl_cid;
    private JButton btn_save;
    private final Room room;
    private Reservation reservation;
    private ReservationManager reservationManager;
    private final RoomManager roomManager;

    private double totalPrice;


    public ReservationAddView(Reservation reservation, Room selectedRoom, String entry_date, String exit_date, double totalPrice, int customerTotalNumber) {
        this.room = selectedRoom;
        if (reservation == null) {
            this.reservation = new Reservation();
        } else {
            this.reservation = reservation;
        }
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(1100, 700);
        this.totalPrice = totalPrice;
        this.fld_hotelname.setText(this.room.getHotel().getName());
        this.fld_roomtype.setText(this.room.getType());
        this.fld_city.setText(this.room.getHotel().getAdress());
        this.fld_star.setText(this.room.getHotel().getStar());
        this.parkRadioButton.setSelected(this.room.getHotel().isPark());
        this.wifiRadioButton.setSelected(this.room.getHotel().isWifi());
        this.poolRadioButton.setSelected(this.room.getHotel().isPool());
        this.conciergeRadioButton.setSelected(this.room.getHotel().isConcierge());
        this.spaRadioButton.setSelected(this.room.getHotel().isSpa());
        this.roomServiceRadioButton.setSelected(this.room.getHotel().isRoom_service());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fld_checkin.setText(entry_date);
        this.fld_checkout.setText(exit_date);
        this.fld_totalprice.setText(String.valueOf(totalPrice));

        /*if (this.reservation.getId() != 0) {
            this.fld_customertotal.setText(String.valueOf(this.reservation.getGuest_count()));
            this.fld_customername.setText(this.reservation.getGuest_name());
            this.fld_customermail.setText(this.reservation.getGuest_mail());
            this.fld_customerphone.setText(this.reservation.getGuest_phone());
            this.fld_customerid.setText(this.reservation.getGuest_citizen_id());
            this.btn_save.setText("Update Reservation");
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate entryDate = LocalDate.parse(entry_date, formatter);
        LocalDate exitDate = LocalDate.parse(exit_date, formatter);
        long dayCount = ChronoUnit.DAYS.between(entryDate, exitDate);*/


        //double adultNumber = Double.parseDouble(adult_number.getText());
        //double childNumber = Double.parseDouble(child_number.getText());

       /* int adultNumber = 0;
        int childNumber = 0;
        try {
            adultNumber = Integer.parseInt(adult_number.getText());
            childNumber = Integer.parseInt(child_number.getText());
        } catch (Exception exp) {
            Helper.showMsg("Please enter a child or adult number");
            dispose();
        }*/

        //double totalPrice = (adultPrice * adultNumber + childPrice * childNumber) * dayCount;
        //setTotalPrice(((adultPrice * adult_number) + (childPrice * child_number)) * dayCount);

        this.fld_totalprice.setText(String.valueOf(this.getTotalPrice()));
        this.tvRadioButton.setSelected(this.room.isTelevision());
        this.minibarRadioButton.setSelected(this.room.isMinibar());
        this.gameConsolRadioButton.setSelected(this.room.isGame_console());
        this.safeBoxRadioButton.setSelected(this.room.isSafe_box());
        this.projectionRadioButton.setSelected(this.room.isProjection());
        this.fld_bedcapacity.setText(String.valueOf(this.room.getBed_capacity()));
        this.fld_pensiontype.setText(this.room.getPension().getPension_type());
        this.fld_m2.setText(String.valueOf(this.room.getSquare_meter()));
        this.fld_customertotal.setText(String.valueOf(customerTotalNumber));
        btn_save.addActionListener(e -> {
            JTextField[] checkFieldList = {
                    this.fld_customername,
                    this.fld_customermail,
                    this.fld_customerphone,
                    this.fld_customerid,
                    this.fld_customertotal,
            };
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("Please fill empty areas");
            } else {
                boolean result;
                this.reservation.setRoom_id(this.room.getId());
                this.reservation.setGuest_count(Integer.parseInt(this.fld_customertotal.getText()));
                this.reservation.setGuest_name(this.fld_customername.getText());
                this.reservation.setGuest_mail(this.fld_customermail.getText());
                this.reservation.setGuest_phone(this.fld_customerphone.getText());
                this.reservation.setGuest_citizen_id(this.fld_customerid.getText());
                this.reservation.setCheck_in_date(LocalDate.parse(entry_date, formatter));
                this.reservation.setCheck_out_date(LocalDate.parse(exit_date, formatter));
                this.reservation.setTotal_price(totalPrice);

                if (this.reservation.getId() != 0) {
                    result = this.reservationManager.update(this.reservation);
                } else {
                    result = this.reservationManager.save(this.reservation);
                }
                if (result) {
                    Helper.showMsg("done");
                    this.room.setStock(this.room.getStock() - 1);
                    this.roomManager.update(this.room);
                    dispose();
                } else {
                    Helper.showMsg("Error");
                }

            }

        });
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private LocalDate parseDate(String dateText) {
        // Kullanıcıdan alınan metin verisini LocalDate türüne dönüştürme
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateText, formatter);
    }
}
