package com.danko.greenhouse.customXmlParser;

import com.danko.greenhouse.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DomXmlParser {
    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Set<Flower> flowers;
    private final static int TAG_POSITION = 0;
    private final static boolean POISONOUS = false;
    public static Logger logger = LogManager.getLogger();

    public DomXmlParser() {
        flowers = new HashSet<>();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser configuration Exception" + e);
        }
    }

    public Set<Flower> purserXml(String fileAddress) {//TODO FILE ADDRESS VALIDATION
        Document document = null;
        try {
            document = documentBuilder.parse(fileAddress);
            Element root = document.getDocumentElement();

            logger.log(Level.INFO, "Has been started parsing Cut-Flowers");
            NodeList cutFlowersList = root.getElementsByTagName(FlowerXmlTag.CUT_FLOWER.getTagName());
            purserNodeList(cutFlowersList, FlowerXmlTag.CUT_FLOWER.getTagName());
            logger.log(Level.INFO, "Has been finished parsing CutFlowers");

            logger.log(Level.INFO, "Has been started parsing ExoticFlowers");
            NodeList exoticFlowersList = root.getElementsByTagName(FlowerXmlTag.EXOTIC_FLOWER.getTagName());
            purserNodeList(exoticFlowersList, FlowerXmlTag.EXOTIC_FLOWER.getTagName());
            logger.log(Level.INFO, "Has been finished parsing ExoticFlowers");

        } catch (IOException e) {
            logger.log(Level.ERROR, "File ERROR or I/O error" + e);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parsing failure" + e);
        }
        return flowers;
    }

    private void purserNodeList(NodeList nodeList, String type) {
        if (FlowerXmlTag.CUT_FLOWER.getTagName().equals(type)) {
            CutFlower.CutFlowerBuilder cutFlowerBuilder = CutFlower.builder();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                cutFlowerBuilder.vendorCodeSet(nodeList.item(i).getAttributes().getNamedItem(FlowerXmlTag.VENDOR_CODE.getTagName()).getNodeValue());
                if (element.hasAttribute(FlowerXmlTag.POISONOUS.getTagName())) {
                    cutFlowerBuilder.poisonousSet(Boolean.parseBoolean(nodeList.item(i).getAttributes().getNamedItem(FlowerXmlTag.POISONOUS.getTagName()).getNodeValue()));
                } else {
                    cutFlowerBuilder.poisonousSet(POISONOUS);
                }
                cutFlowerBuilder.cutDateSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(FlowerXmlTag.CUT_DATE.getTagName()).getNodeValue()));
                cutFlowerBuilder.plantingDateSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(FlowerXmlTag.PLANTING_DATE.getTagName()).getNodeValue()));
                cutFlowerBuilder.titleSet(getElementContent(element, FlowerXmlTag.TITLE.getTagName()));
                cutFlowerBuilder.temperatureSet(Double.valueOf(getElementContent(element, FlowerXmlTag.TEMPERATURE_CUT.getTagName())));
                cutFlowerBuilder.stemLengthSet(Double.valueOf(getElementContent(element, FlowerXmlTag.STEM_LENGTH.getTagName())));
                cutFlowerBuilder.originSet(Origin.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.ORIGIN.getTagName()))));
                cutFlowerBuilder.leafColorSet(Color.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.LEAF_COLOR.getTagName()))));
                cutFlowerBuilder.budColorSet(Color.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.BUD_COLOR.getTagName()))));
                logger.log(Level.DEBUG, "Has been made CutFlower");
                flowers.add((Flower) cutFlowerBuilder.build());
            }
        } else {

            ExoticFlower.ExoticFlowerBuilder exoticFlowerBuilder = ExoticFlower.builder();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                exoticFlowerBuilder.vendorCodeSet(nodeList.item(i).getAttributes().getNamedItem(FlowerXmlTag.VENDOR_CODE.getTagName()).getNodeValue());
                exoticFlowerBuilder.titleSet(getElementContent(element, FlowerXmlTag.TITLE.getTagName()));
                exoticFlowerBuilder.originSet(Origin.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.ORIGIN.getTagName()))));
                exoticFlowerBuilder.leafColorSet(Color.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.LEAF_COLOR.getTagName()))));
                exoticFlowerBuilder.plantingDateSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(FlowerXmlTag.PLANTING_DATE.getTagName()).getNodeValue()));
                exoticFlowerBuilder.soilSet(Soil.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.SOIL.getTagName()))));
                exoticFlowerBuilder.multiplyingSet(Multiplying.valueOf(inToEnumName(getElementContent(element, FlowerXmlTag.MULTIPLYING.getTagName()))));
                exoticFlowerBuilder.wateringSet(Integer.valueOf(getElementContent(element, FlowerXmlTag.WATERING.getTagName())));
                exoticFlowerBuilder.temperatureSet(Double.valueOf(getElementContent(element, FlowerXmlTag.TEMPERATURE_EXOTIC_FLOWER.getTagName())));
                exoticFlowerBuilder.photophilousSet(Boolean.parseBoolean(getElementContent(element, FlowerXmlTag.PHOTOPHILOUS.getTagName())));
                logger.log(Level.DEBUG, "Has been made ExoticFlower");
                flowers.add((Flower) exoticFlowerBuilder.build());
            }
        }
    }

    private String getElementContent(Element elementOfFlower, String elementName) {
        NodeList nodeListFull = elementOfFlower.getElementsByTagName(elementName);
        Node nodeElement = nodeListFull.item(TAG_POSITION);
        String result = nodeElement.getChildNodes().item(TAG_POSITION).getNodeValue();
        return result;
    }

    private static String inToEnumName(String enumName) {
        return enumName.replaceAll("-", "_").toUpperCase();
    }
}
