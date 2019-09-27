package com.tavisca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer {

    @JsonProperty("offer_key")
    private long id;
    @JsonProperty("started_on")
    private OffsetDateTime startDate;
    @JsonProperty("expires_on")
    private OffsetDateTime endDate;
    private String title;
    private String status;



    public Offer(){}

    public Offer(long id, OffsetDateTime startDate, OffsetDateTime endDate, String title, String status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }
}
