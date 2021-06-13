package com.danko.greenhouse.parser;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.exception.FlowerException;

import java.util.Set;

public interface XmlParser {

    public Set<AbstractFlower> parserXml(String fileAddress) throws FlowerException;
}
