package com.ourgame.ourgameserver.game.pack;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


public class PackParser {
    public static void main(String[] args) throws JAXBException {
        System.out.println(getPackage("testPack"));
    }

    private PackParser() {
    }

    public static Package getPackage(String packName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Package.class);
        StreamSource xml = new StreamSource("src/main/resources/packs/" + packName + "/content.xml");
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
