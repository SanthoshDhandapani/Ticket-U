package com.ticketu.model;

import java.util.Date;

public class Ticket {

    private String name, cityName, category, price, additionalMsg, pickupType, screenName,
                   venueName, ticketRows, ticketGenre, imageURL;
    private Date showTimeAndDate;
    private int ticketsCount;

    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String value) {
        cityName = value;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String value) {
        category = value;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String value) {
        price = value;
    }

    public String getAdditionalMsg() {
        return additionalMsg;
    }
    public void setAdditionalMsg(String value) {
        additionalMsg = value;
    }

    public String getPickupType() {
        return pickupType;
    }
    public void setPickupType(String value) {
        pickupType = value;
    }

    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String value) {
        screenName = value;
    }

    public Date getShowDateTime() {
        return showTimeAndDate;
    }
    public void setShowDateTime(Date value) {
        showTimeAndDate = value;
    }

    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String value) {
        venueName = value;
    }

    public String getTicketRows() {
        return ticketRows;
    }
    public void setTicketRows(String value) {
        ticketRows = value;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }
    public void setTicketsCount(int value) {
        ticketsCount = value;
    }

    public String getTicketsGenre() {
        return ticketGenre;
    }
    public void setTicketsGenre(String value) {
        ticketGenre = value;
    }

    public String getImageURL() {
        return imageURL;
    }
    public void setTicketImageURL(String value) {
        imageURL = value;
    }
}