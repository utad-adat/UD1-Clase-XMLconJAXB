package org.example;

import org.example.models.Countries;
import org.example.models.Country;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {

    private static String path = "./src/main/resources/countries.xml";

    public static void main(String[] args) {
        readXML();
    }

    private static void readXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Countries countries = (Countries) unmarshaller.unmarshal(new File(path));

            System.out.println("DALE" + countries.getCountryList());
            for (Country c: countries.getCountryList()) {
                System.out.println("----------");
                System.out.println("Pais: " + c.getName());
                System.out.println("Capital: "+ c.getCapital());
                System.out.println("Población: " + c.getPopulation());
            }

            addNewCountry(countries);
        } catch (JAXBException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addNewCountry(Countries countries) throws JAXBException {
        Country country = new Country();
        country.setName("Portugal");
        country.setCapital("Lisboa");
        country.setPopulation("9029130");

        countries.getCountryList().add(country);

        JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(countries, new File(path));
    }
}