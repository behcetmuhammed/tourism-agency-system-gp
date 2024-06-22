package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserAddView extends Layout {
    private JPanel container;
    private JTextField fld_user_name;
    private JTextField fld_user_password;
    private JTextField fld_user_role;
    private JButton btn_save_user;
    private JComboBox<User.Role> cmb_role;
    private final UserManager userManager;
    private final User user;

    public UserAddView(User user) {
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        guiInitilaze(300, 500);

        if (this.user.getId() != 0) {
            this.fld_user_name.setText(this.user.getUsername());
            this.fld_user_password.setText(this.user.getPassword());
            this.fld_user_role.setText(this.user.getRole());
        }
        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        this.btn_save_user.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_user_name, this.fld_user_password})) {
                Helper.showMsg("fill");
            } else {
                boolean result = false;

                this.user.setUsername(fld_user_name.getText());
                this.user.setPassword(fld_user_password.getText());
                this.user.setRole(cmb_role.getSelectedItem().toString());

                if (this.user.getId() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }
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
