package services.Impl;

import classes.Driver;
import classes.Taxi;
import dao.Database;
import services.DriverService;

import java.util.*;

public class DriverServiceImpl implements DriverService {
    List<Driver> driverList = new LinkedList<>();

    @Override
    public Driver add(Driver driver) {
        driverList.add(driver);
        return driver;
    }

    @Override
    public List<Driver> add(List<Driver> drivers) {
        driverList.addAll(drivers);
        Database database = new Database();
        drivers.forEach(database.getDrivers()::remove);
        database.getDrivers().addAll(drivers);
        return driverList;
    }

    @Override
    public Driver findById(Long id) {
        for (Driver driver : this.driverList) {
            if (Objects.equals(driver.getId(), id)) {
                return driver;
            } else {
                System.out.println("wrong id!!");
            }
        }
        return null;
    }

    @Override
    public List<Driver> findByName(String name) {
        return driverList.stream().filter(s -> s.getName().equals(name)).toList();
    }


    @Override
    public String assignTaxiToDriver(Long taxiId, Long driverId) {
        try {
            Database database = new Database();
            Driver activeDriver = null;
            Taxi activeTaxi = null;
            for (Driver driver : database.getDrivers()) {
                if (Objects.equals(driver.getId(), driverId)) {
                    activeDriver = driver;
                }
            }
            for (Taxi taxi : database.getTaxis()) {
                if (Objects.equals(taxiId, taxi.getId())) {
                    activeTaxi = taxi;
                }
            }
            assert activeDriver != null;
            activeDriver.setTaxi(activeTaxi);
            return "successfully assigned!!";
        } catch (InputMismatchException e) {
            throw new InputMismatchException("wrong input");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "error";

    }

    @Override
    public String changeTaxiOrDriver(Long driverId, Long taxiId) {
        Driver activeDriver = null;
        Taxi activeTaxi = null;

        Database database = new Database();
        for (Taxi taxi1 : database.getTaxis()) {
            if (Objects.equals(taxi1.getId(), taxiId)) {
                activeTaxi = taxi1;
            }
        }
        for (Driver driver1 : database.getDrivers()) {
            if (Objects.equals(driver1.getId(), driverId)) {
                activeDriver = driver1;
            }
        }
        assert activeDriver != null;
        activeDriver.setTaxi(activeTaxi);

        return "the change was successful!!";
    }

    @Override
    public List<Driver> getDriverByTaxiModel(String model) {
        return driverList.stream().filter(s -> s.getTaxi().getModel().equals(model)).toList();
    }

    @Override
    public void update(String driverName) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        for (Driver driver : database.getDrivers()) {
            if (driver.getName().equals(driverName)) {
                System.out.println("surname: ");
                driver.setSurname(scanner.next());
                System.out.println("phone number: ");
                driver.setPhoneNumber(scanner.next());
                System.out.println("money: ");
                driver.setMoney(scanner.nextBigDecimal());
            }
        }

    }
}
