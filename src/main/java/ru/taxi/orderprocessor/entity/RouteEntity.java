package ru.taxi.orderprocessor.entity;

public class RouteEntity extends BaseEntity {

    private Point from;
    private Point to;


    public static class Point {

        private double lat;
        private double lng;
    }
}
