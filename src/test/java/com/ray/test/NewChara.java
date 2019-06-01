package com.ray.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ray.Ma
 * @date 2019/5/31 16:49
 */
public class NewChara {
    public static void main(String[] args) {
//
        List list = new ArrayList();
        list.add("ac");
        list.add("da");
        list.add("bb");
        list.add("ba");
        list.add("aa");
        list.add("ab");
        list.add("bba");

        test(list);
        System.out.println(list);
//        opreate();
//        test1();
//        testParallel();
//排序
//        testOptional();
        testDate();
    }

    public static void test(List<String> names) {
        Collections.sort(names, (m, n) -> m.compareTo(n));
        System.out.println(names);
    }

    public static void opreate() {
        MathOperation o = (a, b) -> a + b;
        System.out.println(opreation(10, 5, o));
        MathOperation m = (a, b) -> {
            return a * b;
        };
        System.out.println(opreation(10, 3, m));
    }

    private static int opreation(int a, int b, MathOperation ma) {
        return ma.operate(a, b);
    }

    private static void test1() {


        List<String> strings = Arrays.asList("abc", "", "bc", "ef", "efg", "abcd", "bcd", "jkl");

        List<String> collect = strings.stream().filter(p -> !p.isEmpty()).sorted((m, n) -> m.compareTo(n)).collect(Collectors.toList());
//        System.out.println(collect);
////方法一 调用方法
//        strings.stream().forEach(p -> System.out.println(p));
////方法二 和方法一样的效果
//        strings.stream().forEach(System.out::println);

//       生成10个随机数并且打印
//        Random ran = new Random();
//        ran.ints().limit(10).forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 3);
//
//        List<Double> collect1 = integers.stream().map(i -> Math.pow(i, 2)).collect(Collectors.toList());
//        List<Double> collect2 = integers.stream().map(i -> Math.pow(i, 2)).distinct().collect(Collectors.toList());
//        System.out.println(collect1);
//        System.out.println(collect2);

        long count = integers.stream().count();
        Double average = integers.stream().mapToInt(i -> i).average().getAsDouble();
        int sum = integers.stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum);
        System.out.println(average);
        System.out.println(count);


    }

    public static void testParallel() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "jkl");

        long count = strings.parallelStream().count();

        String collect = strings.stream().filter(p -> !p.isEmpty()).collect(Collectors.joining(","));


        List<Integer> nums = Arrays.asList(1, 4, 1, 4, 2, 6, 2);
        IntSummaryStatistics intSummaryStatistics = nums.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(collect);
        System.out.println(count);
    }

    public static void testOptional() {
        Integer value = null;
        Integer value1 = new Integer(10);

        Optional<Integer> a = Optional.ofNullable(null);
        Optional<Integer> b = Optional.ofNullable(value);
        Optional<Integer> c = Optional.of(value1);
        boolean present = a.isPresent();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c.get());
        System.out.println(present);
        Integer integer = a.orElse(new Integer(0));
        System.out.println(integer);
    }

    public static void testDate() {
        LocalDateTime current = LocalDateTime.now();
        System.out.println(current);
        LocalDate localDate = current.toLocalDate();
        LocalTime localTime = current.toLocalTime();
        System.out.println(localDate);
        System.out.println(localTime);

        int dayOfMonth = current.getDayOfMonth();
        System.out.println("----------");
        System.out.println(dayOfMonth+"dayofmonth");
        System.out.println(current.getDayOfWeek());
        System.out.println(current.getDayOfYear());
        System.out.println(current.getHour());
        System.out.println(current.getMinute());
        System.out.println(current.getMonth());
        System.out.println(current.toLocalDate());
        System.out.println(current.toLocalTime());
        System.out.println(current.getNano()+"--------");
        System.out.println(current.getYear());
        System.out.println(current.getChronology());
        System.out.println(current.getDayOfMonth());
        System.out.println(current.getMonthValue());
        System.out.println(LocalDate.of(2019,10,12));
        System.out.println(LocalTime.of(20,12));
        System.out.println(LocalTime.parse("20:15:20"));
        System.out.println();
        System.out.println();
        System.out.println();
    }
}

interface MathOperation {
    int operate(int a, int b);
}
