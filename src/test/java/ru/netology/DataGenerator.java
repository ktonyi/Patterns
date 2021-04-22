package ru.netology;

import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationInfo generateData(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(
                    faker.name().lastName() + " " + faker.name().firstName(),
                    getRandomCity(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }

    private static String getRandomCity() {
        List<String> cities = Arrays.asList("Благовещенск", "Владивосток", "Абакан", "Саратов", "Великий Новгород", "Воронеж", "Санкт-Петербург", "Омск");
        Random rnd = new Random();
        return cities.get(rnd.nextInt(cities.size() - 1));
    }

    public static String getRandomDate(int maxDays) {
        Random rnd = new Random();
        int n = rnd.nextInt(maxDays) + 3;
        return LocalDate.now().plusDays(n).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}