package com.nny.Demo.javaBasisLearn;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Function;
import java.util.function.BinaryOperator;
import java.util.Comparator;
import java.util.function.UnaryOperator;
import java.util.function.Predicate;
import java.util.GregorianCalendar;
import java.util.Collection;
import java.util.Collections;
import java.lang.Iterable;
import java.util.function.Supplier;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.chrono.IsoChronology;
import java.lang.Number;
import java.util.stream.*;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentMap;

/**
 * 聚合操作
 * 并行
 */
public class ParallelismExamples {

    public static void main(String... args){
        m2();
    }

    /**
     * 有状态的拉姆达表达式
     */
    public static void m2(){

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };

        //这样转换有什么不同吗？
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

        /**
         * 串行流
         * 虽然没有影响结果，但是不提倡
         */
        List<Integer> serialStorage = new ArrayList<>();

        listOfIntegers
                .stream()
                .map(e -> { serialStorage.add(e); return e; })
                .forEach(e -> System.out.print(e + " ")); //1 2 3 4 5 6 7 8

        serialStorage
                .stream()
                .forEach(e -> System.out.print(e + " ")); //1 2 3 4 5 6 7 8

        /**
         * 并行流
         */
        List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<>());

        listOfIntegers
                .parallelStream()
                .map(e -> { parallelStorage.add(e); return e; })//使用有状态的拉姆达表达式，无序地添加元素到新集合中
                .forEachOrdered(e -> System.out.print(e + " "));//1 2 3 4 5 6 7 8

        parallelStorage
                .stream()
                .forEach(e -> System.out.print(e + " "));//4 1 8 7 2 5 3 6
    }

    /**
     * 干扰
     */
    public static void m1(){

        try {
            List<String> listOfStrings = new ArrayList<>(Arrays.asList("one", "two"));

            //干扰发生，在终端操作已经发生后peek操作尝试添加字符串到源中，这会导致失败
            String concatenatedString = listOfStrings
                    .stream()
                    .peek(s -> listOfStrings.add("three"))
                    .reduce((a, b) -> a + " " + b) //java.util.ConcurrentModificationException
                    .get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m0() {

        // Create sample data

        List<Person> roster = Person.createRoster();

        roster
                .stream()
                .forEach(p -> p.printPerson());

        // 1. Average age of male members in parallel

        double average = roster
                .parallelStream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();

        // 2. Concurrent reduction example

        ConcurrentMap<Person.Sex, List<Person>>
                byGenderParallel =
                roster
                        .parallelStream()
                        .collect(Collectors.groupingByConcurrent(Person::getGender));

        List<Map.Entry<Person.Sex, List<Person>>>
                byGenderList =
                new ArrayList<>(byGenderParallel.entrySet());

        byGenderList
                .stream()
                .forEach(e -> {
                    System.out.println("Gender: " + e.getKey());
                    e.getValue()
                            .stream()
                            .map(Person::getName)
                            .forEach(f -> System.out.println(f)); });

        // 3. Examples of ordering and parallelism

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(intArray));

        listOfIntegers
                .stream()
                .forEach(e -> System.out.print(e + " "));

        Comparator<Integer> normal = Integer::compare;
        Comparator<Integer> reversed = normal.reversed();
        Collections.sort(listOfIntegers, reversed);
        listOfIntegers
                .stream()
                .forEach(e -> System.out.print(e + " "));

        listOfIntegers
                .parallelStream()
                .forEach(e -> System.out.print(e + " "));

        listOfIntegers
                .parallelStream()
                .forEach(e -> System.out.print(e + " "));

        listOfIntegers
                .parallelStream()
                .forEachOrdered(e -> System.out.print(e + " "));
    }


}
