package business;

import dao.UserDao;
import core.Helper;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public boolean save(User user) {
        if (user.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {

            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + "ID kayıtlı marka bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : userList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    public ArrayList<User> searchForTable(User.Role role) {
        String select = "SELECT * FROM public.user ";
        ArrayList<String> whereList = new ArrayList<>();

        if (role != null) {
            whereList.add("user_role = '" + role.toString() + "'");
        }

        String whereClause = String.join(" AND ", whereList);
        String query = select + (whereList.isEmpty() ? "" : "WHERE " + whereClause);

        return this.userDao.selectByQuery(query);


    }
}
