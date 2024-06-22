package view;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;
import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.User;
import entity.Pension;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class PensionTypeAddView extends Layout {
    private JPanel container;
    private JLabel lbl_pension;
    private JLabel lbl_welcome;
    private JComboBox cmb_pension;
    private JButton btn_save_pension;
    private JComboBox<ComboItem> cmb_select_hotel;
    private JPanel pnl_select_hotel;
    private JPanel pnl_pension_type;
    private JLabel fld_selecthotel;
    private PensionManager pensionManager;
    private Pension pension;
    private Object[] col_pension;
    private User user;
    private Hotel hotel;
    private HotelManager hotelManager;

    public PensionTypeAddView() {
        this.pensionManager = new PensionManager();
        this.pension = new Pension();
        this.user = user;
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        guiInitilaze(500, 500);
        this.cmb_pension.setModel(new DefaultComboBoxModel<>(Pension.PensionType.values()));

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_select_hotel.addItem(hotel.getComboItem());
        }
        this.btn_save_pension.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedHotel = (ComboItem) cmb_select_hotel.getSelectedItem();
            this.pension.setHotel_id(selectedHotel.getKey());
            //this.pension.setHotel_id(cmb_select_hotel.getSelectedIndex());
            this.pension.setPension_type(cmb_pension.getSelectedItem().toString());

            if (this.pension.getId() != 0) {
                result = this.pensionManager.update(this.pension);
            } else {
                result = this.pensionManager.save(this.pension);
            }
            if (result) {
                Helper.showMsg("Pension Add Successfull.");
                dispose();
            } else {
                Helper.showMsg("error. SOMETHING HAPPENED");
            }
        });
    }
}

