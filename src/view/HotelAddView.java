package view;

import business.HotelManager;
import business.PensionManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.User;

import javax.swing.*;
import java.util.Objects;

public class HotelAddView extends Layout {
    private JPanel container;
    private JPanel pnl_addhotel;
    private JLabel lbl_addhotel;
    private JTextField fld_hotel_name;
    private JTextField fld_mail;
    private JTextField fld_phonenumber;
    private JTextField fld_adress;
    private JCheckBox chck_park;
    private JCheckBox chck_wifi;
    private JCheckBox chck_pool;
    private JCheckBox chck_fitness;
    private JCheckBox chck_concierge;
    private JCheckBox chck_spa;
    private JCheckBox chck_roomservice;
    private JButton btn_save;
    private JPanel pnl_top;
    private JLabel lbl_hotel_name;
    private JLabel lbl_mail;
    private JLabel lbl_phonenumber;
    private JLabel lbl_Adress;
    private JLabel lbl_star;
    private JComboBox cmb_star;
    private JPanel pnl_check1;
    private JPanel pnl_check2;
    private JPanel pnl_check3;
    private PensionManager pensionManager;
    private Pension pension;
    private Object[] col_pension;
    private User user;
    private Hotel hotel;
    private HotelManager hotelManager;

    private UserManager userManager;

    public HotelAddView() {
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.user = user;
        this.hotel = new Hotel();
        this.add(container);
        guiInitilaze(300, 500);

            /*for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_star.addItem(hotel.getComboItem());
        }*/

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_name, this.fld_mail, this.fld_phonenumber, this.fld_adress})) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;

                this.hotel.setName(fld_hotel_name.getText());
                this.hotel.setAdress(fld_adress.getText());
                this.hotel.setMail(fld_mail.getText());
                this.hotel.setPhone_number(fld_phonenumber.getText());
                this.hotel.setPark(chck_park.isSelected());
                this.hotel.setWifi(chck_wifi.isSelected());
                this.hotel.setPool(chck_pool.isSelected());
                this.hotel.setFitness(chck_fitness.isSelected());
                this.hotel.setConcierge(chck_concierge.isSelected());
                this.hotel.setSpa(chck_spa.isSelected());
                this.hotel.setRoom_service(chck_roomservice.isSelected());
                this.hotel.setStar((String) this.cmb_star.getSelectedItem());

                result = this.hotelManager.save(this.hotel);
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}

