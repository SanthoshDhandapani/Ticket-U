package com.ticketu.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("Ticket")
public class Ticket extends ParseObject {

    public String getName() {
        return getString("name");
    }
    public void setName(String value) {
        put("name", value);
    }

    public String getCityName() {
        return getString("cityName");
    }
    public void setCityName(String value) {
        put("cityName", value);
    }

    public String getCategory() {
        return getString("category");
    }
    public void setCategory(String value) {
        put("category", value);
    }

    public String getPrice() {
        return getString("price");
    }
    public void setPrice(String value) {
        put("price", value);
    }

    public String getAdditionalMsg() {
        return getString("additionalMsg");
    }
    public void setAdditionalMsg(String value) {
        put("additionalMsg", value);
    }

    public String getPickupType() {
        return getString("pickupType");
    }
    public void setPickupType(String value) {
        put("pickupType", value);
    }

    public String getScreenName() {
        return getString("screenName");
    }
    public void setScreenName(String value) {
        put("screenName", value);
    }

    public Date getShowDateTime() {
        return getDate("showTimeAndDate");
    }
    public void setShowDateTime(Date value) {
        put("showTimeAndDate", value);
    }

    public String getVenueName() {
        return getString("venueName");
    }
    public void setVenueName(String value) {
        put("venueName", value);
    }

    public String getTicketRows() {
        return getString("ticketRows");
    }
    public void setTicketRows(String value) {
        put("ticketRows", value);
    }

    public int getTicketsCount() {
        return getInt("ticketsCount");
    }
    public void setTicketsCount(int value) {
        put("ticketsCount", value);
    }

    public String getTicketsGenre() {
        return getString("ticketGenre");
    }
    public void setTicketsGenre(String value) {
        put("ticketGenre", value);
    }

    public String getImageURL() {
        return getString("imageURL");
    }
    public void setTicketImageURL(String value) {
        put("imageURL", value);
    }
}