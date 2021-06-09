package com.danko.greenhouse.entity;

import java.time.LocalDate;

public class ExoticFlower extends Flower {
    private Soil soil;
    private Multiplying multiplying;
    private int watering;
    private double temperature;
    private boolean photophilous;

    public ExoticFlower() {
    }

    public ExoticFlower(Soil soil, Multiplying multiplying, int watering, double temperature, boolean photophilous) {
        this.soil = soil;
        this.multiplying = multiplying;
        this.watering = watering;
        this.temperature = temperature;
        this.photophilous = photophilous;
    }

    public ExoticFlower(String title, String vendorCode, Origin origin, Color leafColor, LocalDate plantingDate, Soil soil, Multiplying multiplying, int watering, double temperature, boolean photophilous) {
        super(title, vendorCode, origin, leafColor, plantingDate);
        this.soil = soil;
        this.multiplying = multiplying;
        this.watering = watering;
        this.temperature = temperature;
        this.photophilous = photophilous;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isPhotophilous() {
        return photophilous;
    }

    public void setPhotophilous(boolean photophilous) {
        this.photophilous = photophilous;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!super.equals(obj)) {
//            return false;
//        }
//        ExoticFlower objExoticFlower = (ExoticFlower) obj;
//        if (soil == null) {
//            if (objExoticFlower.soil != null) {
//                return false;
//            }
//        } else if (!soil.equals(objExoticFlower.soil)) {
//            return false;
//        }
//
//        if (multiplying == null) {
//            if (objExoticFlower.multiplying != null) {
//                return false;
//            }
//        } else if (!multiplying.equals(objExoticFlower.multiplying)) {
//            return false;
//        }
//
//        if (watering != objExoticFlower.watering) {
//            return false;
//        }
//
//        if (temperature != objExoticFlower.temperature) {
//            return false;
//        }
//
//        if (photophilous != objExoticFlower.photophilous) {
//            return false;
//        }
//        return true;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExoticFlower that = (ExoticFlower) o;

        if (watering != that.watering) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (photophilous != that.photophilous) return false;
        if (soil != that.soil) return false;
        return multiplying == that.multiplying;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = soil != null ? soil.hashCode() : 0;
        result = 31 * result + (multiplying != null ? multiplying.hashCode() : 0);
        result = 31 * result + watering;
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (photophilous ? 1 : 0);
        return result;
    }

    @Override
    public String toString() { //fixme: make other version
        return "ExoticFlower{" +
                "soil=" + soil +
                ", multiplying=" + multiplying +
                ", watering=" + watering +
                ", temperature=" + temperature +
                ", photophilous=" + photophilous +
                '}';
    }
}
