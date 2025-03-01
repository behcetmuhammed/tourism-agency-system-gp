package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;


import java.util.ArrayList;

public class PensionManager {

    private final PensionDao pensionDao;

    //Constructor PensionManager
    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    //bileşenleri getir
    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    //Kaydet
    public boolean save(Pension pension) {
        if (pension.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    //Güncelle
    public boolean update(Pension pension) {
        if (this.getById(pension.getId()) == null) {

            Helper.showMsg("notFound");
        }
        return this.pensionDao.update(pension);
    }

    //tablodan getir
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensionList) {
        ArrayList<Object[]> pensionRowList = new ArrayList<>();
        for (Pension pension : pensionList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = pension.getId();
            rowObject[i++] = pension.getHotel().getName();
            rowObject[i++] = pension.getPension_type();
            pensionRowList.add(rowObject);
        }
        return pensionRowList;
    }

    //Tabloda arama metodu
    public ArrayList<Pension> searchForTable(Pension.PensionType pensionType) {
        String select = "SELECT * FROM public.hotel_pension ";
        ArrayList<String> whereList = new ArrayList<>();

        if (pensionType != null) {
            whereList.add("pension_type = '" + pensionType.toString() + "'");
        }

        String whereClause = String.join(" AND ", whereList);
        String query = select + (whereList.isEmpty() ? "" : "WHERE " + whereClause);

        return this.pensionDao.selectByQuery(query);
    }

    //id ye göre getir
    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    //Otel id ye göre pers. getir
    public ArrayList<Pension> getPensionsByOtelId(int otelId) {
        return pensionDao.getPensionsByOtelId(otelId);
    }
}
