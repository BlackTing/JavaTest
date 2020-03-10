package com.nny.Demo.CollectionLearn;

import java.util.*;

/**
 * 对象排序
 * 比较器
 * 根据工龄对员工进行排序
 */
public class EmpSort {

    /**
     * 匿名类，创建接口对象
     */
    static final Comparator<Employee> SENIORITY_ORDER = new Comparator<Employee>(){

        @Override
        public int compare(Employee o1, Employee o2) {
            return o2.getHireDate().compareTo(o1.getHireDate());
        }

    };

    /**
     *
     */
    static final Comparator<Employee> SENIORITY_ORDER1 = new Comparator<Employee>(){

        public int compare(Employee e1,Employee e2){
            int dateCmp = e2.getHireDate().compareTo(e1.getHireDate());

            if(dateCmp != 0)
                return dateCmp;

            return (e1.getNumber() < e2.getNumber() ? -1 : (e1.getNumber() == e2.getNumber() ? 0 : 1));
        }
    };

    static final Collection<Employee> employees = new ArrayList();

    public static void main(String[] args){
        List<Employee> e = new ArrayList<Employee>(employees);

        Collections.sort(e,SENIORITY_ORDER);

        System.out.println(e);
    }
}



























