package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JTabbedPane tab_menu;
    private JTable tbl_users;
    private JPanel pnl_top;
    private JLabel lbl_top;
    private JScrollPane scrl_user;
    private JTable tbl_user;
    private JButton btn_logout;
    private JPanel pnl_user;
    private JComboBox<User.Role> cmb_user_role;
    private JButton btn_srch;
    private JButton btn_reset;
    private JLabel lbl_welcome;
    private User user;
    private UserManager userManager;

    private Object[] col_user;
    private final DefaultTableModel tmbdl_user = new DefaultTableModel();


    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;

        this.lbl_welcome.setText("Welcome : " + this.user.getUsername());
        logOut();


        if (user == null) {
            dispose();
        }
        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();

    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_user);
        JPopupMenu user_menu = new JPopupMenu();
        user_menu.add("Yeni").addActionListener(e -> {
            UserAddView userView = new UserAddView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        user_menu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user, 0);
            UserAddView userView = new UserAddView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });

        });
        user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_user.setComponentPopupMenu(user_menu);

        this.btn_srch.addActionListener(e -> {

            User.Role selectedRole = (User.Role) cmb_user_role.getSelectedItem();
            ArrayList<User> userListBySearch = this.userManager.searchForTable(selectedRole);

            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_user.length, userListBySearch);
            loadUserTable(userRowListBySearch);
        });
        this.btn_reset.addActionListener(e -> {
            this.cmb_user_role.setSelectedItem(null);
            loadUserTable(null);
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

    public void loadUserTable(ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"User ID", "User Name", "User Password", "User Role"};
        if (userList == null) {
            userList = userManager.getForTable(col_user.length, this.userManager.findAll());
        }
        this.createTable(this.tmbdl_user, this.tbl_user, col_user, userList);
    }

    public void loadUserFilter() {
        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_user_role.setSelectedItem(null);
    }

}
