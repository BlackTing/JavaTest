package com.nny.Demo.javaBasisLearn;

import java.lang.Iterable;
import java.lang.Number;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class BulkDataOperationsExamples {

    public static void main(String... args) {

        // Create sample data

        List<Person> roster = Person.createRoster();

        // 1. Print names of members, for-each loop

        for (Person p : roster) {
            System.out.println(p.getName());
        }

        // 2. Print names of members, forEach operation

        roster
                .stream()
                .forEach(e -> System.out.println(e.getName()));

        // 3. Print names of male members, forEach operation

        roster
                .stream()
                .filter(e -> e.getGender() == Person.Sex.MALE)
                .forEach(e -> System.out.println(e.getName()));

        // 4. Print names of male members, for-each loop

        for (Person p : roster) {
            if (p.getGender() == Person.Sex.MALE) {
                System.out.println(p.getName());
            }
        }

        // 5. Get average age of male members of the collection:

        double average = roster
                .stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

    }
}