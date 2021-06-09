package com.danko.greenhouse.entity;

import java.time.LocalDate;

public abstract class Flower {
    private String title;
    private String vendorCode;
    private Origin origin;
    private Color leafColor;
    private LocalDate plantingDate;

    public Flower() {
    }

    public Flower(String title, String vendorCode, Origin origin, Color leafColor, LocalDate plantingDate) {
        this.title = title;
        this.vendorCode = vendorCode;
        this.origin = origin;
        this.leafColor = leafColor;
        this.plantingDate = plantingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(Color leafColor) {
        this.leafColor = leafColor;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Flower objFlower = (Flower) obj;
        if (title == null) {
            if (objFlower.title != null) {
                return false;
            }
        } else if (!title.equals(objFlower.title)) {
            return false;
        }
        if (vendorCode == null) {
            if (objFlower.vendorCode != null) {
                return false;
            }
        } else if (!vendorCode.equals(objFlower.vendorCode)) {
            return false;
        }
        if (origin == null) {
            if (objFlower.origin != null) {
                return false;
            }
        } else if (!origin.equals(objFlower.origin)) {
            return false;
        }
        if (leafColor == null) {
            if (objFlower.leafColor != null) {
                return false;
            }
        } else if (!leafColor.equals(objFlower.leafColor)) {
            return false;
        }
        return plantingDate.equals(objFlower.plantingDate);
    }

    @Override
    public String toString() {
        return "Flower{" +
                "title='" + title + '\'' +
                ", vendorCode='" + vendorCode + '\'' +
                ", origin=" + origin +
                ", leafColor=" + leafColor +
                ", plantingDate=" + plantingDate +
                '}';
    }
    //todo hashCode
}
