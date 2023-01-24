package com.ourgame.ourgameserver.game.pack;

import javax.xml.bind.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


public class PackParser {
    public static void main(String[] args) throws JAXBException {
        new PackParser();
    }

    public PackParser() throws JAXBException {
        System.out.println(parseFolderToPack(""));
    }

    public Package parseFolderToPack(String folderPath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Package.class);
        StreamSource xml = new StreamSource("src/main/resources/content.xml");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<Package> jaxbElement = unmarshaller.unmarshal(xml, Package.class);
        Package pack = jaxbElement.getValue();

        Package result = new Package(new Info(List.of("sdf", "sdf")), "asdf", 4, "sdfg");

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {
            marshaller.marshal(result, new FileOutputStream("src/main/resources/a.xml"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return pack;
    }
}
