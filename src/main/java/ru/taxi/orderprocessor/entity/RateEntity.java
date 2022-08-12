package ru.taxi.orderprocessor.entity;

public class RateEntity extends BaseEntity {

    private Integer rate;
    private RateDetails rateDetails;
    private String extFeedBack;


    public enum RateDetails {
        POLITE_DRIVER,
        BEST_DRIVER,
        TRIP_COMFORT,
        COMFORT_MUSIC
    }
}
