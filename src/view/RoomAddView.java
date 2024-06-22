package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomAddView extends Layout {
    private JPanel container;
    private JComboBox cmb_otel;
    private JTextField fld_bedcapacity;
    private JComboBox cmb_pension;
    private JComboBox cmb_season;
    private JComboBox cmb_roomtype;
    private JTextField fld_m2;
    private JTextField fld_stock;
    private JTextField fld_adultprice;
    private JTextField fld_childprice;
    private JRadioButton btn_tv;
    private JRadioButton btn_minibar;
    private JRadioButton btn_consol;
    private JRadioButton btn_case;
    private JRadioButton btn_projection;
    private JButton btn_save;
    private JLabel lbl_hotel;
    private JLabel lbl_bedcapacity;
    private JLabel lbl_pension;
    private JLabel lbl_season;
    private JLabel lbl_roomtype;
    private JLabel lbl_m2;
    private JLabel lb_stock;
    private JLabel lbl_adultprice;
    private JLabel lbl_chilprice;

    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private Pension pension;
    private Object[] col_pension;
    private User user;
    private Hotel hotel;

    private Room room;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private Season season;

    public RoomAddView() {
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.pensionManager = new PensionManager();
        this.room = new Room();
        this.hotelManager = new HotelManager();
        this.add(container);
        guiInitilaze(700, 700);

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_otel.addItem(hotel.getComboItem());
        }

        this.cmb_otel.addActionListener(e -> {
            //Session comboBox'ı güncellenir.

            ComboItem selectedHotel = (ComboItem) cmb_otel.getSelectedItem();

            ArrayList<Season> seasons = this.seasonManager.getSeasonsByOtelId(selectedHotel.getKey());
            cmb_season.removeAllItems();

            for (Season season : seasons) {
                cmb_season.addItem(season.getComboItem());
            }

            //Pension comboBox'ı güncellenir.
            ArrayList<Pension> pensions = pensionManager.getPensionsByOtelId(selectedHotel.getKey());
            cmb_pension.removeAllItems(); // Var olan öğeleri temizle

            // Yeni pansiyonları combobox'a ekle

            for (Pension pension : pensions) {
                cmb_pension.addItem(pension.getComboItem());
            }
        });

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_stock, this.fld_adultprice, this.fld_childprice, this.fld_bedcapacity, this.fld_m2})) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;


                ComboItem selectedHotel = (ComboItem) cmb_otel.getSelectedItem();
                this.room.setHotel_id(selectedHotel.getKey());

                ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
                this.room.setPension_id(selectedPension.getKey());

                ComboItem selectedSeason = (ComboItem) cmb_season.getSelectedItem();
                this.room.setSeason_id(selectedSeason.getKey());

                // PENSION VE SEASON SETLEYEMEMİŞSİN KONTROL ET AKŞAM GELİNCE
                this.room.setType((String) this.cmb_roomtype.getSelectedItem());
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setAdult_price(Integer.parseInt(fld_adultprice.getText()));
                this.room.setChild_price(Double.parseDouble(fld_childprice.getText()));
                this.room.setBed_capacity(Integer.parseInt(fld_bedcapacity.getText()));
                this.room.setSquare_meter(Integer.parseInt(fld_m2.getText()));
                this.room.setTelevision(btn_tv.isSelected());
                this.room.setMinibar(btn_minibar.isSelected());
                this.room.setGame_console(btn_consol.isSelected());
                this.room.setSafe_box(btn_case.isSelected());
                this.room.setProjection(btn_projection.isSelected());
                result = this.roomManager.save(this.room);

                if (result) {
                    Helper.showMsg("Room Add Succcessful.");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
