package com.danko.greenhouse.parser;

import com.danko.greenhouse.entity.*;
import com.danko.greenhouse.exception.FlowerException;
import com.danko.greenhouse.validator.CustomFileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.danko.greenhouse.parser.FlowerXmlTag.*;

public class StaxXmlParser {
    public static Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private final static boolean POISONOUS_DEFAULT = false;

    private Set<AbstractFlower> flowers;
    private XMLInputFactory factory;
    private XMLEventReader reader;
    private Map<String, String> elemetsMap;
    private FlowerXmlTag makeFlover;
    private FlowerXmlTag element;

    public StaxXmlParser() {
        factory = XMLInputFactory.newFactory();
        elemetsMap = new HashMap<>();
        flowers = new HashSet<>();
    }

    public Set<AbstractFlower> parserXml(String xmlFile) throws FlowerException {
        if (!CustomFileValidator.isFileValidation(xmlFile)) {
            logger.log(Level.ERROR, "File can not valid...");
            throw new FlowerException("File can not valid ...");
        }

        try {
            reader = factory.createXMLEventReader(new FileInputStream(xmlFile));
            logger.log(Level.INFO, "StAX parser has been started...");
            while (reader.hasNext()) {
                XMLEvent event = null;
                try {
                    event = reader.nextEvent();
                } catch (XMLStreamException e) {
                    logger.log(Level.ERROR, "XMLStreamException...");
                    throw new FlowerException("XMLStreamException ...");
                }
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    FlowerXmlTag flowerType = FlowerXmlTag.valueOf(tagToEnum(startElement.getName().getLocalPart()));
                    switch (flowerType) {
                        case CUT_FLOWER:
                            makeFlover = flowerType;
                            getAttribute(startElement);
                            break;
                        case EXOTIC_FLOWER:
                            makeFlover = flowerType;
                            getAttribute(startElement);
                            break;
                        default:
                            element = flowerType;
                            event = reader.nextEvent();
                            eventHandler(event);
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    FlowerXmlTag endFlower = FlowerXmlTag.valueOf(tagToEnum(endElement.getName().getLocalPart()));
                    switch (endFlower) {
                        case CUT_FLOWER:
                            flowerMaker();
                            break;
                        case EXOTIC_FLOWER:
                            flowerMaker();
                            break;
                    }
                }
            }
            logger.log(Level.INFO, "StAX parser has been finished. Flowers has been made.");
            return flowers;
        } catch (XMLStreamException | IOException e) {
            logger.log(Level.ERROR, "File can not parsing ...");
            throw new FlowerException("File can not parsing ...");
        }
    }

    private void flowerMaker() {
        switch (makeFlover) {
            case EXOTIC_FLOWER: {
                ExoticFlower.Builder builder = ExoticFlower.builder();
                builder.vendorCodeSet(elemetsMap.get(VENDOR_CODE.tagName()))
                        .plantingDateSet(LocalDate.parse(elemetsMap.get(PLANTING_DATE.tagName())))
                        .titleSet(elemetsMap.get(TITLE.tagName()))
                        .originSet(Origin.valueOf(tagToEnum(elemetsMap.get(ORIGIN.tagName()))))
                        .leafColorSet(Color.valueOf(tagToEnum(elemetsMap.get(LEAF_COLOR.tagName()))))
                        .soilSet(Soil.valueOf(tagToEnum(elemetsMap.get(SOIL.tagName()))))
                        .multiplyingSet(Multiplying.valueOf(tagToEnum(elemetsMap.get(MULTIPLYING.tagName()))))
                        .wateringSet(Integer.valueOf(elemetsMap.get(WATERING.tagName())))
                        .temperatureSet(Double.valueOf(elemetsMap.get(TEMPERATURE_EXOTIC_FLOWER.tagName())))
                        .photophilousSet(Boolean.parseBoolean(elemetsMap.get(PHOTOPHILOUS.tagName())));
                flowers.add(builder.build());
                logger.log(Level.DEBUG, "Has been made ExoticFlower");
            }
            break;
            case CUT_FLOWER: {
                CutFlower.Builder builder = CutFlower.builder();
                builder.vendorCodeSet(elemetsMap.get(VENDOR_CODE.tagName()))
                        .plantingDateSet(LocalDate.parse(elemetsMap.get(PLANTING_DATE.tagName())))
                        .cutDateSet(LocalDate.parse(elemetsMap.get(CUT_DATE.tagName())))
                        .poisonousSet(Boolean.parseBoolean(elemetsMap.get(POISONOUS.tagName())))
                        .titleSet(elemetsMap.get(TITLE.tagName()))
                        .originSet(Origin.valueOf(tagToEnum(elemetsMap.get(ORIGIN.tagName()))))
                        .leafColorSet(Color.valueOf(tagToEnum(elemetsMap.get(LEAF_COLOR.tagName()))))
                        .stemLengthSet(Double.valueOf(elemetsMap.get(STEM_LENGTH.tagName())))
                        .budColorSet(Color.valueOf(tagToEnum(elemetsMap.get(BUD_COLOR.tagName()))))
                        .temperatureSet(Double.valueOf(elemetsMap.get(TEMPERATURE_CUT.tagName())));
                flowers.add(builder.build());
                logger.log(Level.DEBUG, "Has been made CutFlower");
            }
            break;
        }
    }

    private void eventHandler(XMLEvent event) {
        switch (element) {
            case TITLE:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case ORIGIN:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case LEAF_COLOR:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case STEM_LENGTH:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case BUD_COLOR:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case TEMPERATURE_CUT:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case SOIL:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case MULTIPLYING:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case WATERING:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case TEMPERATURE_EXOTIC_FLOWER:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
            case PHOTOPHILOUS:
                elemetsMap.put(element.tagName(), event.asCharacters().getData());
                break;
        }
    }

    private void getAttribute(StartElement startElement) {
        switch (makeFlover) {
            case EXOTIC_FLOWER:
                elemetsMap.put(VENDOR_CODE.tagName(), startElement.getAttributeByName(new QName(VENDOR_CODE.tagName())).getValue());
                elemetsMap.put(PLANTING_DATE.tagName(), startElement.getAttributeByName(new QName(PLANTING_DATE.tagName())).getValue());
                break;
            case CUT_FLOWER:
                elemetsMap.put(VENDOR_CODE.tagName(), startElement.getAttributeByName(new QName(VENDOR_CODE.tagName())).getValue());
                elemetsMap.put(PLANTING_DATE.tagName(), startElement.getAttributeByName(new QName(PLANTING_DATE.tagName())).getValue());
                elemetsMap.put(CUT_DATE.tagName(), startElement.getAttributeByName(new QName(CUT_DATE.tagName())).getValue());
                Attribute attribute = startElement.getAttributeByName(new QName(POISONOUS.tagName()));
                if (attribute == null) {
                    elemetsMap.put(POISONOUS.tagName(), String.valueOf(POISONOUS_DEFAULT));
                } else {
                    elemetsMap.put(POISONOUS.tagName(), startElement.getAttributeByName(new QName(POISONOUS.tagName())).getValue());
                }
                break;
        }
    }

    private String tagToEnum(String qName) {
        return qName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}
