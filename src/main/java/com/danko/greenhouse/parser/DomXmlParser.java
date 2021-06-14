package com.danko.greenhouse.parser;

import com.danko.greenhouse.entity.*;
import com.danko.greenhouse.exception.FlowerException;
import com.danko.greenhouse.validator.CustomFileValidator;
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

import static com.danko.greenhouse.parser.FlowerXmlTag.*;

public class DomXmlParser implements XmlParser {
    private static Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private Set<AbstractFlower> flowers;
    private final static int TAG_POSITION = 0;
    private final static boolean POISONOUS_DEFAULT = false;

    public DomXmlParser() {
        flowers = new HashSet<>();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser configuration Exception" + e);
        }
    }

    public Set<AbstractFlower> parserXml(String fileAddress) throws FlowerException {
        Document document = null;
        if (!CustomFileValidator.isFileValid(fileAddress)) {
            logger.log(Level.ERROR, "File can not valid ...");
            throw new FlowerException("File can not valid ...");
        }
        try {
            document = documentBuilder.parse(fileAddress);
            Element root = document.getDocumentElement();

            logger.log(Level.DEBUG, "DOM parser has been started...");
            NodeList cutFlowersList = root.getElementsByTagName(FlowerXmlTag.CUT_FLOWER.tagName());
            parserNodeList(cutFlowersList, FlowerXmlTag.CUT_FLOWER.tagName());

            NodeList exoticFlowersList = root.getElementsByTagName(FlowerXmlTag.EXOTIC_FLOWER.tagName());
            parserNodeList(exoticFlowersList, FlowerXmlTag.EXOTIC_FLOWER.tagName());
            logger.log(Level.DEBUG, "DOM parser has been finished. Flowers has been made.");
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, "Parsing failure..." + e);
            throw new FlowerException("Parsing failure...");
        }
        return flowers;
    }

    private void parserNodeList(NodeList nodeList, String type) {
        if (FlowerXmlTag.CUT_FLOWER.tagName().equals(type)) {
            cutFlowersMaker(nodeList);
        } else {
            exoticFlowersMaker(nodeList);
        }
    }

    private void cutFlowersMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            CutFlower.Builder builder = CutFlower.builder();
            Element element = (Element) nodeList.item(i);

            if (element.hasAttribute(POISONOUS.tagName())) {
                builder.poisonousSet(Boolean.parseBoolean(nodeList.item(i).getAttributes().getNamedItem(POISONOUS.tagName()).getNodeValue()));
            } else {
                builder.poisonousSet(POISONOUS_DEFAULT);
            }
            builder.titleSet(getContent(element, TITLE.tagName())).
                    stemLengthSet(Double.valueOf(getContent(element, STEM_LENGTH.tagName()))).
                    temperatureSet(Double.valueOf(getContent(element, TEMPERATURE_CUT.tagName()))).
                    originSet(Origin.valueOf(enumName(getContent(element, ORIGIN.tagName())))).
                    budColorSet(Color.valueOf(enumName(getContent(element, BUD_COLOR.tagName())))).
                    leafColorSet(Color.valueOf(enumName(getContent(element, LEAF_COLOR.tagName())))).
                    vendorCodeSet(nodeList.item(i).getAttributes().getNamedItem(VENDOR_CODE.tagName()).getNodeValue()).
                    cutDateSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(CUT_DATE.tagName()).getNodeValue())).
                    plantingDateSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(PLANTING_DATE.tagName()).getNodeValue()));

            logger.log(Level.DEBUG, "Has been made CutFlower");
            flowers.add((AbstractFlower) builder.build());
        }
    }

    private void exoticFlowersMaker(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            ExoticFlower.Builder builder = ExoticFlower.builder();
            Element element = (Element) nodeList.item(i);

            builder.titleSet(getContent(element, TITLE.tagName())).
                    wateringSet(Integer.valueOf(getContent(element, WATERING.tagName()))).
                    soilSet(Soil.valueOf(enumName(getContent(element, SOIL.tagName())))).
                    originSet(Origin.valueOf(enumName(getContent(element, ORIGIN.tagName())))).
                    photophilousSet(Boolean.parseBoolean(getContent(element, PHOTOPHILOUS.tagName()))).
                    leafColorSet(Color.valueOf(enumName(getContent(element, LEAF_COLOR.tagName())))).
                    temperatureSet(Double.valueOf(getContent(element, TEMPERATURE_EXOTIC_FLOWER.tagName()))).
                    multiplyingSet(Multiplying.valueOf(enumName(getContent(element, MULTIPLYING.tagName())))).
                    vendorCodeSet(nodeList.item(i).getAttributes().getNamedItem(VENDOR_CODE.tagName()).getNodeValue()).
                    plantingDateSet(LocalDate.parse(nodeList.item(i).getAttributes().getNamedItem(PLANTING_DATE.tagName()).getNodeValue()));

            logger.log(Level.DEBUG, "Has been made ExoticFlower");
            flowers.add((AbstractFlower) builder.build());
        }
    }

    private String getContent(Element elementOfFlower, String elementName) {
        NodeList nodeListFull = elementOfFlower.getElementsByTagName(elementName);
        Node nodeElement = nodeListFull.item(TAG_POSITION);
        String result = nodeElement.getChildNodes().item(TAG_POSITION).getNodeValue();
        return result;
    }

    private static String enumName(String enumName) {
        return enumName.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}
