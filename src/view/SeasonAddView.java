package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.User;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SeasonAddView extends Layout {
    private JPanel container;
    private JLabel lbl_seasonadd;
    private JPanel pnl_select_hotel;
    private JLabel fld_select_hotel;
    private JComboBox cmb_select_hotel;
    private JButton btn_save_season;
    private JTextField fld_strt_date;
    private JTextField fld_fnsh_date;
    private JLabel lbl_season_start;
    private JLabel lbl_season_finish;
    private JPanel pnl_season_end;
    private JPanel pnl_season_start;
    private JFormattedTextField fld_end_date;

    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private Pension pension;
    private Object[] col_pension;
    private User user;
    private Hotel hotel;
    private HotelManager hotelManager;

    private Season season;

    public SeasonAddView() {
        this.seasonManager = new SeasonManager();
        this.pension = new Pension();
        this.season = new Season();
        this.user = user;
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.add(container);
        guiInitilaze(500, 500);
        //this.cmb_select_hotel.setModel(new DefaultComboBoxModel<>(Pension.PensionType.values()));

        //0 geldiği yer ekrana yazdırma kısmı
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_select_hotel.addItem(hotel.getComboItem());
        }
        this.btn_save_season.addActionListener(e -> {
            boolean result = false;
            ComboItem selectedHotel = (ComboItem) cmb_select_hotel.getSelectedItem();
            this.season.setHotel_id(selectedHotel.getKey());

            // Tarih alanlarını doldur
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate startDate = LocalDate.parse(fld_strt_date.getText(), formatter);
                LocalDate finishDate = LocalDate.parse(fld_end_date.getText(), formatter);
                this.season.setStart_date(startDate);
                this.season.setFinish_date(finishDate);
            } catch (DateTimeParseException ex) {
                Helper.showMsg("Wrong date format -> dd-mm-yyyy");
                return;
            }

            result = this.seasonManager.save(this.season);
            if (result) {
                Helper.showMsg("Season Add Successfull");
                dispose();
            } else {
                Helper.showMsg("error. SOMETHING HAPPENED");
            }
        });
    }

    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("13/01/2021 ");
        this.fld_end_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_end_date.setText("16/05/2021");
    }
}