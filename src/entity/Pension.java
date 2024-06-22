package entity;

import core.ComboItem;

public class Pension {

    private int id;
    private int hotel_id;
    private String pension_type;
    private Hotel hotel;

    public Pension(int id, int hotel_id, String pension_type) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.pension_type = pension_type;
        this.hotel = hotel;
    }

    public Pension() {
    }

    public enum PensionType {
        UltraExclusive,
        Exclusive,
        Breakfast,
        FullPension,
        HalfPension,
        OnlyBed,
        FullCreditWithoutAlcohol,
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getPension_type());
    }

    @Override
    public String toString() {
        return "Pension{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", pension_type='" + pension_type + '\'' +
                ", hotel=" + hotel +
                '}';
    }

}
