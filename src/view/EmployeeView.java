package view;

import business.*;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JButton btn_logout;
    private JTabbedPane pnl_menu;
    private JLabel lbl_welcome;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JScrollPane scrl_pension;
    private JScrollPane scrl_room;
    private JButton btn_add;
    private JPanel pnl_button;
    private JTextField fld_hotelname;
    private JTextField fld_city;
    private JTextField fld_adult;
    private JTextField fld_child;
    private JButton btn_searchhotel;
    private JButton btn_reset;
    private JButton btn_addroom;
    private JTable tbl_room;
    private JPanel pnl_hotelname;
    private JLabel lbl_hotelname;
    private JLabel lbl_city;
    private JLabel lbl_checkin;
    private JLabel lbl_checkout;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JPanel pnl_reservation;
    private JPanel pnl_room;
    private JPanel pnl_room_filter;
    private JFormattedTextField fld_checkin;
    private JFormattedTextField fld_checkout;
    private JTable tbl_reservation;
    private Hotel hotel;
    private User user;
    private Object[] col_pension;
    private Object[] col_season;
    private Object[] col_hotel;
    private Object[] col_hotel_pension;
    private Object[] col_room;

    private Object[] col_reservation;

    private HotelManager hotelManager;
    private UserManager userManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;

    private ReservationManager reservationManager;
    private final DefaultTableModel tmbdl_pension = new DefaultTableModel();

    private final DefaultTableModel tmbdl_season = new DefaultTableModel();

    private final DefaultTableModel tmbdl_room = new DefaultTableModel();

    private final DefaultTableModel tmbdl_hotel = new DefaultTableModel();

    private final DefaultTableModel tmbdl_reservation = new DefaultTableModel();

    public EmployeeView(User user) {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitilaze(1200, 700);
        this.user = user;
        this.lbl_welcome.setText("Welcome : " + this.user.getUsername());

        logOut();

        if (user == null) {
            dispose();
        }

        // Hotel
        loadHotelTable();
        loadHotelComponent();


        // Pension
        loadPensionTable();
        addHotelButton();

        //Season
        loadSeasonTable();

        //Room
        loadRoomTable(null);
        addRoomButton();
        loadRoomComponent();


        // Reservation
        loadReservationTable();
        loadReservationComponent();
    }

    // EMPOLEEVIEW OTEL ADD SCREEN BUTTON
    private void addHotelButton() {
        this.btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotelAddViewScreen();
            }
        });
    }

    private void logOut() {
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

    // EMPOLEEVIEW OTEL ADD SCREEN VIEW
    private void hotelAddViewScreen() {
        HotelAddView hotelAddView = new HotelAddView();
        hotelAddView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadHotelTable();
            }
        });
        hotelAddView.setVisible(true);
    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "Mail", "Phone Number", "Hotel Star", "Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Service"};
        ArrayList<Object[]> hotelList = HotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        this.createTable(this.tmbdl_hotel, this.tbl_hotel, col_hotel, hotelList);

    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);
        JPopupMenu hotel_menu = new JPopupMenu();
        hotel_menu.add("Add Pension Type").addActionListener(e -> {
            PensionTypeAddView pensionTypeAddView = new PensionTypeAddView();
            pensionTypeAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                }
            });
        });

        hotel_menu.add("Add Season").addActionListener(e -> {
            SeasonAddView seasonAddView = new SeasonAddView();
            seasonAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    }

    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel Name", "Pension Type"};
        ArrayList<Object[]> pensionList = pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.tmbdl_pension, this.tbl_pension, col_pension, pensionList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel Name", "Start Date", "Finish Date"};
        ArrayList<Object[]> seasonList = seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.tmbdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadReservationTable() {
        Object[] col_reservation = {"ID", "Oda ID", "Check in Date", "Check out Date", "Total Price", "Number of Guest", "Guest Name", "Guest Citizen ID", "Guest Mail", "Guest Phone No"};
        ArrayList<Object[]> reservationList = reservationManager.getForTable(col_reservation.length, reservationManager.findAll());
        this.createTable(this.tmbdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }

    private void loadReservationComponent() {

        tableRowSelect(tbl_reservation);
        JPopupMenu reservation_menu = new JPopupMenu();
        reservation_menu.add("Update Reservation").addActionListener(e -> {

            int selectId = this.getTableSelectedRow(this.tbl_reservation, 0);
            Reservation selectReservation = this.reservationManager.getById(selectId);
            //selectReservation.getRoom_id();
            int selectRoomId = selectReservation.getRoom_id();
            int selectedAdult = 0;
            int selectedChild = 0;
            Room selectedRoom = this.roomManager.getById(selectRoomId);


            ReservationAddView reservationAddView = new ReservationAddView(
                    selectReservation,
                    selectedRoom,
                    selectReservation.getCheck_in_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    selectReservation.getCheck_out_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), selectReservation.getTotal_price(), selectReservation.getGuest_count()
            );
            reservationAddView.setTotalPrice(100);
            //reservationAddView.setTotalPrice(100);
            reservationAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();

                }
            });
        });
        reservation_menu.add("Delete Reservation").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            Reservation selectReservation = this.reservationManager.getById(selectReservationId);
            if (Helper.confirm("sure")) {
                if (this.reservationManager.delete(selectReservationId)) {
                    Helper.showMsg("done");
                    int selectRoomId = selectReservation.getRoom_id();
                    Room selectedRoom = this.roomManager.getById(selectRoomId);
                    selectedRoom.setStock(selectedRoom.getStock() + 1);
                    this.roomManager.update(selectedRoom);
                    loadReservationTable();
                    loadRoomTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
            // ılgılı ıd e aıt room  stok + 1
        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
    }

    private void addRoomButton() {
        btn_addroom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomAddViewScreen();
            }
        });
    }

    private void roomAddViewScreen() {
        RoomAddView roomAddView = new RoomAddView();
        roomAddView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadRoomTable(null);
            }
        });
        roomAddView.setVisible(true);
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel Name", "Pension Type", "Season", "Room Type", "Stok", "Adult Price", "Child Price", "Bed Capacity", "m2", "TV", "Minibar", "Console", "Cash Box", "Projection"};
        if (roomList == null) {
            roomList = roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmbdl_room, this.tbl_room, col_room, roomList);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room);
        JPopupMenu room_menu = new JPopupMenu();
        room_menu.add("Create Reservation").addActionListener(e -> {


            int selectId = this.getTableSelectedRow(this.tbl_room, 0);

            if (fld_adult.getText().isEmpty() || fld_child.getText().isEmpty()) {
                Helper.showMsg("Please enter a valid child or adult number");
                return;
            }

            int adultNumber = 0;
            int childNumber = 0;
            try {
                adultNumber = Integer.parseInt(fld_adult.getText());
                childNumber = Integer.parseInt(fld_child.getText());
            } catch (Exception exp) {
                Helper.showMsg("Please enter a valid  child or adult number");
                return;
            }

            if (adultNumber < 0 && childNumber < 0) {
                Helper.showMsg("Please enter a positive child or adult number");
                return;
            }


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate entryDate = LocalDate.parse(this.fld_checkin.getText(), formatter);
            LocalDate exitDate = LocalDate.parse(this.fld_checkout.getText(), formatter);
            long dayCount = ChronoUnit.DAYS.between(entryDate, exitDate);
            double totalPrice = (this.roomManager.getById(selectId).getAdult_price() * adultNumber + this.roomManager.getById(selectId).getChild_price() * childNumber) * dayCount; // adultprice ve childprice cek
            ReservationAddView reservationAddView = new ReservationAddView(null,
                    this.roomManager.getById(selectId),
                    this.fld_checkin.getText(),
                    this.fld_checkout.getText(),
                    totalPrice,
                    adultNumber + childNumber
            );


            reservationAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadHotelTable();
                    loadPensionTable();
                    loadReservationTable();
                }
            });

        });

        this.tbl_room.setComponentPopupMenu(room_menu);

        btn_searchhotel.addActionListener(e -> {
            int selectedAdult = 0;
            int selectedChild = 0;
            try {
                selectedAdult = Integer.parseInt(fld_adult.getText());
                selectedChild = Integer.parseInt(fld_child.getText());
            } catch (Exception exp) {
                Helper.showMsg("Please enter a child or adult number");
            }


            if (selectedAdult < 0 || selectedChild < 0) {
                Helper.showMsg("Please enter a child or adult number");
            }
            ArrayList<Room> roomList = roomManager.searchForTable(
                    fld_hotelname.getText(),
                    fld_city.getText(),
                    fld_checkin.getText(),
                    fld_checkout.getText(),
                    fld_adult.getText(),
                    fld_child.getText()
            );

            ArrayList<Object[]> searchResult = roomManager.getForTable(col_room.length, roomList);
            loadRoomTable(searchResult);
        });
        this.btn_reset.addActionListener(e -> {
            loadRoomTable(null);
        });
    }


    private void createUIComponents() throws ParseException {
        this.fld_checkin = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkin.setText("13/01/2021");
        this.fld_checkout = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_checkout.setText("16/05/2021");
    }

}