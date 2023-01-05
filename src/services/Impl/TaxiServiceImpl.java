package services.Impl;

import classes.Taxi;
import dao.Database;
import enums.TaxiType;
import services.TaxiService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaxiServiceImpl implements  TaxiService{
    Database database = new Database();
    @Override
    public StringBuilder add(Taxi taxi) {
        database.getTaxis().add(taxi);
        return new StringBuilder("Successfully added");

    }

    @Override
    public StringBuilder add(List<Taxi> taxis) {
        database.getTaxis().addAll(taxis);
        return new StringBuilder("Successfully added!");
    }

    @Override
    public List<Taxi> findByInitialLetter(String model) {
        return database.getTaxis().stream().filter(x->x.getModel().startsWith(model)).toList();
    }

    @Override
    public Map<TaxiType, List<Taxi>> grouping() {
        Map<TaxiType,List<Taxi>>taxiTypeListMap = database.getTaxis().stream().collect(Collectors.groupingBy(Taxi::getTaxiType));
        return taxiTypeListMap;
    }

    @Override
    public List<Taxi> filterByTaxiType(String taxiType) {
        return database.getTaxis().stream().filter(x->x.getTaxiType().equals(TaxiType.valueOf(taxiType))).toList();
    }

    @Override
    public void update(Long id) {

        for (Taxi taxi : database.getTaxis()) {
            if (taxi.getId().equals(id)){
                System.out.print("""
                 Write a command:
                1. Refactor id:
                2. Refactor model:
                3. Refactor number:
                4. Refactor colour;
                5. Refactor year:
                6. Refactor TaxiType:
                
               """);
                int number = new Scanner(System.in).nextInt();
                switch (number) {
                    case 1 -> taxi.setId(new Scanner(System.in).nextLong());
                    case 2 -> taxi.setModel(new Scanner(System.in).nextLine());
                    case 3 -> taxi.setNumber(new Scanner(System.in).nextLine());
                    case 4 -> taxi.setColour(new Scanner(System.in).nextLine());
                    case 5 -> {
                        int num1 = new Scanner(System.in).nextInt();
                        int num2 = new Scanner(System.in).nextInt();
                        int num3 = new Scanner(System.in).nextInt();
                        taxi.setYear(LocalDate.of(num1, num2, num3));
                    }
                    case 6 -> {
                        String taxiType = new Scanner(System.in).nextLine();
                        taxi.setTaxiType(TaxiType.valueOf(taxiType));
                    }
                }
            }
        }

    }
}
