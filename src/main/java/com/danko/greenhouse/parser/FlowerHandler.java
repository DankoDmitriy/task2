package com.danko.greenhouse.parser;

import com.danko.greenhouse.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.Set;

import static com.danko.greenhouse.entity.FlowerXmlTag.*;

public class FlowerHandler extends DefaultHandler {
    public static Logger logger = LogManager.getLogger();
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";

    private Set<AbstractFlower> flowers;
    private CutFlower.Builder cutBuilder;
    private ExoticFlower.Builder exoticBuilder;

    private final static boolean POISONOUS_DEFAULT = false;
    private FlowerXmlTag flowerMakeTag;
    private FlowerXmlTag nextTag;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        logger.log(Level.INFO, "SaxXmlParser has been started parsing Flowers.");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        logger.log(Level.INFO, "SaxXmlParser has been finished parsing Flowers.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        FlowerXmlTag enumTag = FlowerXmlTag.valueOf(tagToEnum(qName));
        switch (enumTag) {
            case CUT_FLOWER:
                flowerMakeTag = CUT_FLOWER;
                cutBuilder = CutFlower.builder();
                attributesSet(attributes);
                attributesOptionalSet(attributes, cutBuilder);
                break;
            case EXOTIC_FLOWER:
                flowerMakeTag = EXOTIC_FLOWER;
                exoticBuilder = ExoticFlower.builder();
                attributesSet(attributes);
                break;
            default:
                nextTag = enumTag;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        FlowerXmlTag enumTag = FlowerXmlTag.valueOf(tagToEnum(qName));
        switch (enumTag) {
            case CUT_FLOWER:
                flowers.add(cutBuilder.build());
                logger.log(Level.DEBUG, "Has been made CutFlower");
                break;
            case EXOTIC_FLOWER:
                flowers.add(exoticBuilder.build());
                logger.log(Level.DEBUG, "Has been made ExoticFlower");
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String data = new String(ch, start, length).trim();
        if (data != null && data.length() > 0) {
            if (flowerMakeTag == CUT_FLOWER) {
                switch (nextTag) {
                    case TEMPERATURE_CUT:
                        cutBuilder.temperatureSet(Double.valueOf(data));
                        break;
                    case STEM_LENGTH:
                        cutBuilder.stemLengthSet(Double.valueOf(data));
                        break;
                    case BUD_COLOR:
                        cutBuilder.budColorSet(Color.valueOf(tagToEnum(data)));
                        break;
                    case TITLE:
                        cutBuilder.titleSet(data);
                        break;
                    case ORIGIN:
                        cutBuilder.originSet(Origin.valueOf(tagToEnum(data)));
                        break;
                    case LEAF_COLOR:
                        cutBuilder.leafColorSet(Color.valueOf(tagToEnum(data)));
                        break;
                    default:
                        throw new SAXException("Enum constant not present " + nextTag.getDeclaringClass() + " " + nextTag.tagName());
                }
            } else {
                switch (nextTag) {
                    case TITLE:
                        exoticBuilder.titleSet(data);
                        break;
                    case ORIGIN:
                        exoticBuilder.originSet(Origin.valueOf(tagToEnum(data)));
                        break;
                    case LEAF_COLOR:
                        exoticBuilder.leafColorSet(Color.valueOf(tagToEnum(data)));
                        break;
                    case SOIL:
                        exoticBuilder.soilSet(Soil.valueOf(tagToEnum(data)));
                        break;
                    case MULTIPLYING:
                        exoticBuilder.multiplyingSet(Multiplying.valueOf(tagToEnum(data)));
                        break;
                    case WATERING:
                        exoticBuilder.wateringSet(Integer.valueOf(data));
                        break;
                    case TEMPERATURE_EXOTIC_FLOWER:
                        exoticBuilder.temperatureSet(Double.valueOf(data));
                        break;
                    case PHOTOPHILOUS:
                        exoticBuilder.photophilousSet(Boolean.parseBoolean(data));
                        break;
                    default:
                        throw new SAXException("Enum constant not present " + nextTag.getDeclaringClass() + " " + nextTag.tagName());
                }
            }
        }
    }

    public Set<AbstractFlower> getFlowers() {
        return flowers;
    }

    public void setFlowers(Set<AbstractFlower> flowers) {
        this.flowers = flowers;
    }

    private void attributesSet(Attributes attributes) {
        switch (flowerMakeTag) {
            case CUT_FLOWER:
                cutBuilder.vendorCodeSet(attributes.getValue(VENDOR_CODE.tagName()))
                        .plantingDateSet(LocalDate.parse(attributes.getValue(PLANTING_DATE.tagName())))
                        .cutDateSet(LocalDate.parse(attributes.getValue(CUT_DATE.tagName())));
                break;
            case EXOTIC_FLOWER:
                exoticBuilder.vendorCodeSet(attributes.getValue(VENDOR_CODE.tagName()))
                        .plantingDateSet(LocalDate.parse(attributes.getValue(PLANTING_DATE.tagName())));
                break;
        }
    }

    private void attributesOptionalSet(Attributes attributes, CutFlower.Builder cutBuilder) {
        String poisonous = attributes.getValue(POISONOUS.tagName());
        if (poisonous == null) {
            cutBuilder.poisonousSet(POISONOUS_DEFAULT);
        } else {
            cutBuilder.poisonousSet(!POISONOUS_DEFAULT);
        }
    }

    private String tagToEnum(String qName) {
        return qName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}
