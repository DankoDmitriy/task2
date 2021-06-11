package com.danko.greenhouse.entity;

import java.time.LocalDate;

public class ExoticFlower extends Flower {
    private Soil soil;
    private Multiplying multiplying;
    private int watering;
    private double temperature;
    private boolean photophilous;

    private ExoticFlower() {
    }

    private ExoticFlower(Soil soil, Multiplying multiplying, int watering, double temperature, boolean photophilous) {
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
    public String toString() {
        return "ExoticFlower{" +
                "soil=" + soil +
                ", multiplying=" + multiplying +
                ", watering=" + watering +
                ", temperature=" + temperature +
                ", photophilous=" + photophilous +
                "} " + super.toString();
    }

    public static ExoticFlowerBuilder builder() {
        return new ExoticFlowerBuilder();
    }

    public static class ExoticFlowerBuilder {
        private ExoticFlower exoticFlower;

        public ExoticFlowerBuilder() {
            exoticFlower = new ExoticFlower();
        }

        public ExoticFlowerBuilder titleSet(String title) {
            exoticFlower.setTitle(title);
            return this;
        }

        public ExoticFlowerBuilder vendorCodeSet(String vendorCode) {
            exoticFlower.setVendorCode(vendorCode);
            return this;
        }

        public ExoticFlowerBuilder originSet(Origin origin) {
            exoticFlower.setOrigin(origin);
            return this;
        }

        public ExoticFlowerBuilder leafColorSet(Color leafColor) {
            exoticFlower.setLeafColor(leafColor);
            return this;
        }

        public ExoticFlowerBuilder plantingDateSet(LocalDate plantingDate) {
            exoticFlower.setPlantingDate(plantingDate);
            return this;
        }

        public ExoticFlowerBuilder soilSet(Soil soil) {
            exoticFlower.setSoil(soil);
            return this;
        }

        public ExoticFlowerBuilder multiplyingSet(Multiplying multiplying) {
            exoticFlower.setMultiplying(multiplying);
            return this;
        }

        public ExoticFlowerBuilder wateringSet(int watering) {
            exoticFlower.setWatering(watering);
            return this;
        }

        public ExoticFlowerBuilder temperatureSet(double temperature) {
            exoticFlower.setTemperature(temperature);
            return this;
        }

        public ExoticFlowerBuilder photophilousSet(boolean photophilous) {
            exoticFlower.setPhotophilous(photophilous);
            return this;
        }

        public ExoticFlower build() {
            return exoticFlower;
        }
    }
}
