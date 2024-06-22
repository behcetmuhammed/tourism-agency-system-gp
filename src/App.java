import core.Db;
import core.Helper;
import view.EmployeeView;
import view.LoginView;
import business.UserManager;
import javax.swing.*;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        UserManager userManager = new UserManager();
        LoginView loginView = new LoginView();
//EmployeeView employeeView = new EmployeeView(userManager.findByLogin("Employee","1234"));

}
    }
