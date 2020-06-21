package com.wei.lambda;

import org.junit.Test;

import java.util.*;

public class TestLambda {
    // 原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //lambda 表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,9999.99),
            new Employee("李四",38,5555.99),
            new Employee("王五",50,6666.66),
            new Employee("赵柳",16,3333.33),
            new Employee("天气",18,7777.99)
    );
    // 需求： 获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test3() {
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return  emps;
    }

    // 需求： 获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }
        return  emps;
    }

    // 优化方式1: 策略设计模式
    @Test
    public void test4() {
        List<Employee> list = filterEmployees(employees, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("----------------------------------------------------");
        List<Employee> list2 = filterEmployees(employees, new FilterEmployeeBySalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }
    public List<Employee> filterEmployees(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (mp.test(emp)) {
                emps.add(emp);
            }
        }
        return  emps;
    }

    // 优化方式2： 匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployees(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 35;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("----------------------------------------------------");

        List<Employee> list2 = filterEmployees(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 5000;
            }
        });
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }

    // 优化方式3： Lambda 表达式
    @Test
    public void test6() {
        List<Employee> list = filterEmployees(employees, (e) -> e.getAge() >= 35);
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("----------------------------------------------------");

        List<Employee> list2 = filterEmployees(employees, (e) -> e.getSalary() >= 5000);
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }

    // 优化方式4：
    @Test
    public void test7() {
        employees.stream().filter((e) -> e.getAge() >= 35).forEach(System.out::println);
        System.out.println("----------------------------------------------------");
        employees.stream().filter((e) -> e.getSalary() >= 5000).forEach(System.out::println);

    }
}
