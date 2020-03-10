package com.nny.Demo.javaBasisLearn;

/**
 * 内部类
 * 输出数组的偶数位置的值
 */
public class DataStructure {

    // Create an array
    private final static int SIZE = 15;

    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public void printEven() {

        DataStructureIterator iterator = this.new EvenIterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();
    }

    /**
     * 内部接口
     */
    interface DataStructureIterator extends java.util.Iterator<Integer> { }

    /**
     * 内部类
     */
    private class EvenIterator implements DataStructureIterator {

        private int nextIndex = 0;

        public boolean hasNext() {
            return (nextIndex <= SIZE - 1);
        }

        public Integer next() {

            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            nextIndex += 2;

            return retValue;
        }
    }

    public static void main(String s[]) {

        DataStructure ds = new DataStructure();

        ds.printEven();
    }
}
