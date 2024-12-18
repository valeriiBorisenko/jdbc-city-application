package se.lexicon.model;

import se.lexicon.dao.CityDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static se.lexicon.db.MySQLConnection.getConnection;

public class CityDaoJDBC implements CityDao {

    @Override
    public City findById(int id) {
        String sql = "SELECT * FROM city WHERE id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    int cityId = resultSet.getInt("id");
                    String cityName = resultSet.getString("name");
                    String cityCountryCode = resultSet.getString("countryCode");
                    String cityDistrict = resultSet.getString("district");
                    int cityPopulation = resultSet.getInt("population");

                    return new City(cityId, cityName, cityCountryCode, cityDistrict, cityPopulation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        String sql = "SELECT * FROM city WHERE countryCode = ?";
        List<City> cities = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    int cityId = resultSet.getInt("id");
                    String cityName = resultSet.getString("name");
                    String cityCountryCode = resultSet.getString("countryCode");
                    String cityDistrict = resultSet.getString("district");
                    int cityPopulation = resultSet.getInt("population");

                    cities.add(new City(cityId,cityName,cityCountryCode,cityDistrict,cityPopulation));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public List<City> findByName(String name) {
        String sql = "SELECT * FROM city WHERE name = ?";
        List<City> cities = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    int cityId = resultSet.getInt("id");
                    String cityName = resultSet.getString("name");
                    String cityCountryCode = resultSet.getString("countryCode");
                    String cityDistrict = resultSet.getString("district");
                    int cityPopulation = resultSet.getInt("population");

                    cities.add(new City(cityId,cityName,cityCountryCode,cityDistrict,cityPopulation));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public City add(City city) {
        String sql = "INSERT INTO city (name, countryCode, district, population) VALUES(?,?,?,?)";
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        city.setId(generatedKeys.getInt(1));
                        return city;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public City update(City city) {
        String sql = "UPDATE city SET name=? , countryCode =? , district =? , population =? WHERE id =?";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            preparedStatement.executeUpdate();
            return city;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int delete(City city) {
        String sql = "DELETE FROM city WHERE id=?";

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, city.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}