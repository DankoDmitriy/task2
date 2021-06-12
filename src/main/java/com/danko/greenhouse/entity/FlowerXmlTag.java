package com.danko.greenhouse.entity;

public enum FlowerXmlTag {
    FLOWERS,
    CUT_FLOWER,
    VENDOR_CODE,
    PLANTING_DATE,
    CUT_DATE,
    POISONOUS,
    TITLE,
    ORIGIN,
    LEAF_COLOR,
    STEM_LENGTH,
    BUD_COLOR,
    TEMPERATURE_CUT,
    EXOTIC_FLOWER,
    SOIL,
    MULTIPLYING,
    WATERING,
    TEMPERATURE_EXOTIC_FLOWER,
    PHOTOPHILOUS;

    public String tagName() {
        return name().replace("_", "-").toLowerCase();
    }
}
