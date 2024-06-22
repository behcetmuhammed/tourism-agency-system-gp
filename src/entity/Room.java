package entity;

import business.HotelManager;
import business.PensionManager;
import core.ComboItem;

import java.io.PrintWriter;

public class Room {
    private int id;
    private int hotel_id;
    private int pension_id;
    private int season_id;
    private String type;
    private int stock;
    private double adult_price;
    private double child_price;
    private int bed_capacity;
    private int square_meter;
    private boolean television;
    private boolean minibar;
    private boolean game_console;
    private boolean safe_box;
    private boolean projection;
    private Hotel hotel;
    private Season season;
    private Pension pension;

   /* public Room(int id, int hotel_id, int pension_id, int season_id, String type, int stock, double adult_price, double child_price, int bed_capacity, int square_meter, boolean television, boolean minibar, boolean game_console, boolean safe_box, boolean projection) {
        this.id = id;
        this.hotel = hotel;
        this.hotel_id = hotel_id;
        this.pension_id = pension_id;
        this.season_id = season_id;
        this.type = type;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.bed_capacity = bed_capacity;
        this.square_meter = square_meter;
        this.television = television;
        this.minibar = minibar;
        this.game_console = game_console;
        this.safe_box = safe_box;
        this.projection = projection;
    }*/

    public Room() {

    }

    public enum RoomType {
        SingleRoom,
        DoubleRoom,
        JuniorSuitRoom,
        SuitRoom

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

    public int getPension_id() {
        return pension_id;
    }

    public void setPension_id(int pension_id) {
        this.pension_id = pension_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(double adult_price) {
        this.adult_price = adult_price;
    }

    public double getChild_price() {
        return child_price;
    }

    public void setChild_price(double child_price) {
        this.child_price = child_price;
    }

    public int getBed_capacity() {
        return bed_capacity;
    }

    public void setBed_capacity(int bed_capacity) {
        this.bed_capacity = bed_capacity;
    }

    public int getSquare_meter() {
        return square_meter;
    }

    public void setSquare_meter(int square_meter) {
        this.square_meter = square_meter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGame_console() {
        return game_console;
    }

    public void setGame_console(boolean game_console) {
        this.game_console = game_console;
    }

    public boolean isSafe_box() {
        return safe_box;
    }

    public void setSafe_box(boolean safe_box) {
        this.safe_box = safe_box;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getType());
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", pension_id=" + pension_id +
                ", season_id=" + season_id +
                ", type='" + type + '\'' +
                ", stock=" + stock +
                ", adult_price=" + adult_price +
                ", child_price=" + child_price +
                ", bed_capacity=" + bed_capacity +
                ", square_meter=" + square_meter +
                ", television=" + television +
                ", minibar=" + minibar +
                ", game_console=" + game_console +
                ", safe_box=" + safe_box +
                ", projection=" + projection +
                '}';
    }
}
