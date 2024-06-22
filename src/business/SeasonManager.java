package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public boolean save(Season season) {
        if (season.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }

    public boolean update(Season season) {
        if (this.getById(season.getId()) == null) {

            Helper.showMsg("notFound");
        }
        return this.seasonDao.update(season);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList) {
        ArrayList<Object[]> seasonRowList = new ArrayList<>();
        for (Season season : seasonList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = season.getId();
            rowObject[i++] = season.getHotel().getName();
            rowObject[i++] = season.getStart_date().toString();
            rowObject[i++] = season.getFinish_date().toString();
            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    public ArrayList<Season> getSeasonsByOtelId(int id) {
        return this.seasonDao.getSeasonsByOtelId(id);
    }
}
