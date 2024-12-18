package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.model.City;
import se.lexicon.model.CityDaoJDBC;

public class Main {
    public static void main(String[] args) {
        CityDao cityDao = new CityDaoJDBC();
        City city = new City("test", "NLD", "test-test", 14587489);
        City citUpdate = new City(4082, "testUpdate", "NLD", "test-update", 1458);

        // System.out.println(cityDao.findById(5));
        // System.out.println(cityDao.findByCode("NLD"));
        // System.out.println(cityDao.findByName("Ede"));
        // System.out.println(cityDao.add(city));
        //System.out.println(cityDao.update(citUpdate));
        //System.out.println(cityDao.delete(cityUpdate));
    }
}