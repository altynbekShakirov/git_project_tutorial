import classes.Client;
import classes.Driver;
import classes.License;
import classes.Taxi;
import dao.Database;
import enums.Gender;
import enums.TaxiType;
import services.Impl.ClientServiceImpl;
import services.Impl.DriverServiceImpl;
import services.Impl.TaxiServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Client>clients = new ArrayList<>(List.of(
                new Client(1L,"kutman1",LocalDate.of(2000,2,1),"233234",new BigDecimal(2000)),
                new Client(2L,"kutman2",LocalDate.of(2001,2,1),"233234",new BigDecimal(3000)),
                new Client(3L,"kutman3",LocalDate.of(2002,2,1),"233234",new BigDecimal(1000)),
                new Client(4L,"kutman4",LocalDate.of(2003,2,1),"233234",new BigDecimal(4000)),
                new Client(5L,"kutman5",LocalDate.of(2004,2,1),"233234",new BigDecimal(22000))
        ));

        License license1 = new License(1L,LocalDate.of(2010,1,1),LocalDate.of(2030,2,2));
        License license2 = new License(2L,LocalDate.of(2010,1,1),LocalDate.of(2030,2,2));
        License license3 = new License(3L,LocalDate.of(2010,1,1),LocalDate.of(2030,2,2));
        License license4 = new License(4L,LocalDate.of(2010,1,1),LocalDate.of(2030,2,2));
        License license5 = new License(5L,LocalDate.of(2010,1,1),LocalDate.of(2030,2,2));
        List<License>licenses =new ArrayList<>(List.of(license1,license2,license3,license4,license5));

        Taxi taxi1 = new Taxi(1L,"mers1","232323","red",LocalDate.ofYearDay(2000,2),TaxiType.BUSINESS);
        Taxi taxi2 = new Taxi(2L,"mers2","232323","red",LocalDate.ofYearDay(2000,2),TaxiType.BUSINESS);
        Taxi taxi3 = new Taxi(3L,"mers3","232323","red",LocalDate.ofYearDay(2000,2),TaxiType.BUSINESS);
        Taxi taxi4 = new Taxi(4L,"mers4","232323","red",LocalDate.ofYearDay(2000,2),TaxiType.BUSINESS);
        Taxi taxi5 = new Taxi(5L,"mers5","232323","red",LocalDate.ofYearDay(2000,2),TaxiType.BUSINESS);
        List<Taxi>taxis = new ArrayList<>(List.of(taxi1,taxi2,taxi3,taxi4,taxi5));

        Driver driver1 = new Driver(1L,"altyn1","shakirov",Gender.FEMALE,"333223",license1,new BigDecimal(2000),taxi1);
        Driver driver2 = new Driver(2L,"altyn2","shakirov",Gender.FEMALE,"333223",license2,new BigDecimal(2000),taxi2);
        Driver driver3 = new Driver(3L,"altyn3","shakirov",Gender.FEMALE,"333223",license3,new BigDecimal(2000),taxi3);
        Driver driver4 = new Driver(4L,"altyn4","shakirov",Gender.FEMALE,"333223",license4,new BigDecimal(2000),taxi4);
        Driver driver5 = new Driver(5L,"altyn5","shakirov",Gender.FEMALE,"333223",license5,new BigDecimal(2000),taxi5);
        List<Driver>drivers = new ArrayList<>(List.of(driver1,driver2,driver3,driver4,driver5));
        Database database = new Database();
        ClientServiceImpl clientService = new ClientServiceImpl();
        clientService.addClient(clients);
        DriverServiceImpl driverService = new DriverServiceImpl();
        driverService.add(drivers);
        TaxiServiceImpl taxiService = new TaxiServiceImpl();
        taxiService.add(taxis);
        while (true) {
            System.out.println("""
                    1 - client
                    2 - driver
                    3 - taxi""");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("""
                            1 - get client by name
                            2 - remuve client by id
                            3 - order taxi
                            4 - get client age
                            5 - universalSorting
                                                    """);
                    switch (scanner.nextInt()) {
                        case 1:
                            String f = scanner.nextLine();
                            System.out.println(clientService.getClientByName(scanner.nextLine()));
                            break;
                        case 2:
                            System.out.println(clientService.removeClientById(scanner.nextLong()));
                            break;
                        case 3:
                            System.out.println(clientService.orderTaxi(scanner.nextLong(), scanner.nextLine()));
                            break;
                        case 4:
                            System.out.println(clientService.getClientAge());
                            break;
                        case 5:
                            String d = scanner.nextLine();
                            clientService.universalSorting(scanner.nextLine());
                            break;
                        default:
                            System.out.println("error");
                    }
                    break;
                case 2:
                    System.out.println("""
                            1 - fi by id
                            2 -fi by name
                            3 - assignTaxiToDriver
                            4 - changeTaxiOrDriver
                            5 - getDriverByTaxiModel
                            6 - update by name
                            """);
                    switch (scanner.nextInt()){
                        case 1:
                            System.out.println(driverService.findById(scanner.nextLong()));
                            break;
                        case 2:
                            System.out.println(driverService.findByName(scanner.nextLine()));
                            break;
                        case 3:
                            System.out.println(driverService.assignTaxiToDriver(scanner.nextLong(), scanner.nextLong()));
                            break;
                        case 4:
                            System.out.println(driverService.changeTaxiOrDriver(scanner.nextLong(), scanner.nextLong()));
                            break;
                        case 5:
                            System.out.println(driverService.getDriverByTaxiModel(scanner.nextLine()));
                            break;
                        case 6:
                            driverService.update(scanner.nextLine());
                            break;
                    }
                    break;
                case 3:
                    System.out.println("""
                            1 - findByInitialLetter
                            2 - grouping
                            3 - filterByTaxiType
                            4 - update""");
                    switch (scanner.nextInt()){
                        case 1:
                            System.out.println(taxiService.findByInitialLetter(scanner.nextLine()));
                            break;
                        case 2:
                            System.out.println(taxiService.grouping());
                            break;
                        case 3:
                            System.out.println(taxiService.filterByTaxiType(scanner.nextLine()));
                            break;
                        case 4:
                            taxiService.update(scanner.nextLong());
                            break;
                        default:
                            System.out.println("error");


                    }


                    break;
                default:
                    System.out.println("error");
            }

        }
    }
}