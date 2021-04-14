package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] carData = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000",
                "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28",
                "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

        ArrayList<Transport> cars = new ArrayList<>();

        for (String datum : carData) {
            cars.add(new Transport(datum));
        }

        for (Transport car : cars) {
            car.setCarData();
        }

        Map<Integer, Double> groupedSums = new HashMap<>();

        double sumLightCars = 0;
        double sumHeavyCars = 0;
        double sumPassengerCars = 0;
        double sumTechCars = 0;

        for (Transport car : cars) {
            switch (car.getType()) {
                case 100:
                    car.costs = car.computeCostsFor100();
                    sumLightCars += car.costs;
                case 200:
                    car.costs = car.computeCostsFor200();
                    sumHeavyCars += car.costs;
                    groupedSums.put(car.getType(), car.costs);
                case 300:
                    car.costs = car.computeCostsFor300();
                    sumPassengerCars += car.costs;
                    groupedSums.put(car.getType(), car.costs);
                case 400:
                    car.costs = car.computeCostsFor400();
                    sumTechCars += car.costs;
                    groupedSums.put(car.getType(), car.costs);
            }
        }
//        Подсчет общей суммы затрат, выше каждая переменная - расчет на каждую категорию, вот это запринтить(1й пункт ДЗ)
//        Слагаемые суммы надо тоже попринтить, каждое (вторая часть первого пункта ДЗ)
        double overallSum = sumLightCars + sumHeavyCars + sumPassengerCars + sumTechCars;
        System.out.println("Общая сумма расходов на ГСМ: " + Math.round(overallSum));
        System.out.println("Общая сумма расходов на ГСМ по легковым авто: " + Math.round(sumLightCars));
        System.out.println("Общая сумма расходов на ГСМ по грузовым авто: " + Math.round(sumHeavyCars));
        System.out.println("Общая сумма расходов на ГСМ по пассажирскому транспорту: " + Math.round(sumPassengerCars));
        System.out.println("Общая сумма расходов на ГСМ по тяжелой технике: " + Math.round(sumTechCars));

        groupedSums.put(100, sumLightCars);
        groupedSums.put(200, sumHeavyCars);
        groupedSums.put(300, sumPassengerCars);
        groupedSums.put(400, sumTechCars);

        Double maxCosts = (Double)Collections.max(groupedSums.values());
        groupedSums.forEach((k, v) -> {
            if (v == maxCosts) {
                System.out.println("Тип авто с наибольшим расходом: " + k);
            }

        });
        Double minCosts = (Double)Collections.min(groupedSums.values());
        groupedSums.forEach((k, v) -> {
            if (v == minCosts) {
                System.out.println("Тип авто с наименьшим расходом: " + k);
            }

        });

        for (Transport car : cars) {
            System.out.println("Car type: " + car.getType() + " Car number: " + car.getNumber() + " Car kilometers: " + car.getKilometers() + " Car additional info: " + car.getAddInfo());
        }
    }
}
