package entity;

import core.ComboItem;

public class Hotel {

    private int id;
    private String name;
    private String adress;
    private String mail;
    private String phone_number;
    private String star;
    private boolean park;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;

    private boolean spa;

    private boolean room_service;

    public Hotel(int id, String name, String adress, String mail, String phone_number, String star, boolean park, boolean wifi, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean room_service) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.mail = mail;
        this.phone_number = phone_number;
        this.star = star;
        this.park = park;
        this.wifi = wifi;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.room_service = room_service;
    }

    public Hotel() {

    }


    public enum HotelStar {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isPark() {
        return park;
    }

    public void setPark(boolean park) {
        this.park = park;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoom_service() {
        return room_service;
    }

    public void setRoom_service(boolean room_service) {
        this.room_service = room_service;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getId() + " - " + this.getName() + " - " + this.getAdress());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", mail='" + mail + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", star='" + star + '\'' +
                ", park=" + park +
                ", wifi=" + wifi +
                ", pool=" + pool +
                ", fitness=" + fitness +
                ", concierge=" + concierge +
                ", spa=" + spa +
                ", room_service=" + room_service +
                '}';
    }
}
