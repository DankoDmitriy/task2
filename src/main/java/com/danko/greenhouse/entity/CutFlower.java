package com.danko.greenhouse.entity;

import java.time.LocalDate;

public class CutFlower extends AbstractFlower {
    private double temperature;
    private LocalDate cutDate;
    private double stemLength;
    private Color budColor;
    private boolean poisonous;

    private CutFlower() {
    }

    private CutFlower(String title, String vendorCode, Origin origin, Color leafColor, LocalDate plantingDate, double temperature, LocalDate cutDate, double stemLength, Color budColor, boolean poisonous) {
        super(title, vendorCode, origin, leafColor, plantingDate);
        this.temperature = temperature;
        this.cutDate = cutDate;
        this.stemLength = stemLength;
        this.budColor = budColor;
        this.poisonous = poisonous;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public LocalDate getCutDate() {
        return cutDate;
    }

    public void setCutDate(LocalDate cutDate) {
        this.cutDate = cutDate;
    }

    public double getStemLength() {
        return stemLength;
    }

    public void setStemLength(double stemLength) {
        this.stemLength = stemLength;
    }

    public Color getBudColor() {
        return budColor;
    }

    public void setBudColor(Color budColor) {
        this.budColor = budColor;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CutFlower cutFlower = (CutFlower) o;

        if (Double.compare(cutFlower.temperature, temperature) != 0) return false;
        if (Double.compare(cutFlower.stemLength, stemLength) != 0) return false;
        if (poisonous != cutFlower.poisonous) return false;
        if (cutDate != null ? !cutDate.equals(cutFlower.cutDate) : cutFlower.cutDate != null) return false;
        return budColor == cutFlower.budColor;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(temperature);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cutDate != null ? cutDate.hashCode() : 0);
        temp = Double.doubleToLongBits(stemLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (budColor != null ? budColor.hashCode() : 0);
        result = 31 * result + (poisonous ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CutFlower{");
        sb.append("temperature=").append(temperature);
        sb.append(", cutDate=").append(cutDate);
        sb.append(", stemLength=").append(stemLength);
        sb.append(", budColor=").append(budColor);
        sb.append(", poisonous=").append(poisonous);
        sb.append('}');
        sb.append(" ").append(super.toString());
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder { //FIXME DELETE public - this only for test
        private CutFlower cutFlower;

        private Builder() {
            cutFlower = new CutFlower();
        }

        public Builder titleSet(String title) {
            cutFlower.setTitle(title);
            return this;
        }

        public Builder vendorCodeSet(String vendorCode) {
            cutFlower.setVendorCode(vendorCode);
            return this;
        }

        public Builder originSet(Origin origin) {
            cutFlower.setOrigin(origin);
            return this;
        }

        public Builder leafColorSet(Color leafColor) {
            cutFlower.setLeafColor(leafColor);
            return this;
        }

        public Builder plantingDateSet(LocalDate plantingDate) {
            cutFlower.setPlantingDate(plantingDate);
            return this;
        }

        public Builder temperatureSet(double temperature) {
            cutFlower.setTemperature(temperature);
            return this;
        }

        public Builder cutDateSet(LocalDate cutDate) {
            cutFlower.setCutDate(cutDate);
            return this;
        }

        public Builder stemLengthSet(double stemLength) {
            cutFlower.setStemLength(stemLength);
            return this;
        }

        public Builder budColorSet(Color budColor) {
            cutFlower.setBudColor(budColor);
            return this;
        }

        public Builder poisonousSet(boolean poisonous) {
            cutFlower.setPoisonous(poisonous);
            return this;
        }

        public CutFlower build() {
            return cutFlower;
        }
    }
}
