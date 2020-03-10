package com.nny.Demo.javaBasisLearn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import java.util.function.*;
import java.lang.Double;

/**
 * 聚合操作
 * 归约操作
 */
public class ReductionExamples {

    public static void main(String... args) {


        List<Person> roster = Person.createRoster();

        roster
                .stream()
                .forEach(p -> p.printPerson());

        /**
         *
         */
        double average = roster
                .stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        /**
         *
         */
        Integer totalAge = roster
                .stream()
                .mapToInt(Person::getAge)
                .sum();

        /**
         *
         */
        Integer totalAgeReduce = roster
                .stream()
                .map(Person::getAge)
                .reduce(
                        0,
                        (a, b) -> a + b);

        Averager averageCollect = roster.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(Averager::new, Averager::accept, Averager::combine);

        double a = averageCollect.average();

        List<String> namesOfMaleMembersCollect = roster
                .stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(p -> p.getName())
                .collect(Collectors.toList());

        namesOfMaleMembersCollect
                .stream()
                .forEach(p -> System.out.println(p));

        Map<Person.Sex, List<Person>> byGender =
                roster
                        .stream()
                        .collect(
                                Collectors.groupingBy(Person::getGender));

        List<Map.Entry<Person.Sex, List<Person>>>
                byGenderList =
                new ArrayList<>(byGender.entrySet());

        byGenderList
                .stream()
                .forEach(e -> {
                    System.out.println("Gender: " + e.getKey());
                    e.getValue()
                            .stream()
                            .map(Person::getName)
                            .forEach(f -> System.out.println(f)); });

        Map<Person.Sex, List<String>> namesByGender =
                roster
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getGender,
                                        Collectors.mapping(
                                                Person::getName,
                                                Collectors.toList())));

        List<Map.Entry<Person.Sex, List<String>>>
                namesByGenderList =
                new ArrayList<>(namesByGender.entrySet());

        namesByGenderList
                .stream()
                .forEach(e -> {
                    System.out.println("Gender: " + e.getKey());
                    e.getValue()
                            .stream()
                            .forEach(f -> System.out.println(f)); });

        Map<Person.Sex, Integer> totalAgeByGender =
                roster
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getGender,
                                        Collectors.reducing(
                                                0,
                                                Person::getAge,
                                                Integer::sum)));

        List<Map.Entry<Person.Sex, Integer>>
                totalAgeByGenderList =
                new ArrayList<>(totalAgeByGender.entrySet());

        totalAgeByGenderList
                .stream()
                .forEach(e ->
                        System.out.println("Gender: " + e.getKey() +
                                ", Total Age: " + e.getValue()));

        // 9. Average age by gender

        System.out.println("Average age by gender:");
        Map<Person.Sex, Double> averageAgeByGender =
                roster
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getGender,
                                        Collectors.averagingInt(Person::getAge)));

        for (Map.Entry<Person.Sex, Double> e : averageAgeByGender.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}
