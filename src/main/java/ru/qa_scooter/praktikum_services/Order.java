package ru.qa_scooter.praktikum_services;
import com.github.javafaker.Faker;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private Date deliveryDate;
    private String comment;
    private List<String> color;
    public static Faker faker = new Faker();

    public Order(){

    }

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, Date deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getRandomOrder(List<String> colors){
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();
        final String address = faker.address().streetAddress();
        final String metroStation = faker.address().streetName();
        final String phone = faker.phoneNumber().phoneNumber();
        final int rentTime = faker.number().numberBetween(1, 2);
        final Date deliveryDate = faker.date().future(7, 4, TimeUnit.DAYS);
        final String comment = faker.pokemon().name();
        final List<String> color = colors;
        return new Order(firstName, lastName, address, metroStation, phone, rentTime,
                deliveryDate, comment, color);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
