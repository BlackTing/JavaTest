package com.nny.Demo.javaBasisLearn;

/**
 * 嵌套类
 * 局部类
 * 局部类
 *
 * 声明局部类
 * 访问封闭类和封闭块中的变量或参数
 *
 * 验证电话号码的有效性
 */

public class LocalClassExample {

    static String regularExpression = "[^0-9]";//除了数字0-9

    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {

        /**
         *会导致参数变成not 有效final
         */
        //phoneNumber1 = "1234";

        final int numberLength = 10;

        //int numberLength = 10;

        class PhoneNumber {
            /**
             * 静态的常量变量
             */
            static final String a = "a";

            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber){

                /**
                 * 导致局部变量not有效final
                 */
                //numberLength = 7;

                String currentNumber = phoneNumber.replaceAll(regularExpression, "");

                if (currentNumber.length() == numberLength)
                    formattedPhoneNumber = currentNumber;
                else
                    formattedPhoneNumber = null;
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }


            public void printOriginalNumbers() {
                System.out.println("Original numbers are " + phoneNumber1 +
                    " and " + phoneNumber2);
            }

        }

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        myNumber1.printOriginalNumbers();

        if (myNumber1.getNumber() == null)
            System.out.println("First number is invalid");
        else
            System.out.println("First number is " + myNumber1.getNumber());
        if (myNumber2.getNumber() == null)
            System.out.println("Second number is invalid");
        else
            System.out.println("Second number is " + myNumber2.getNumber());

    }

    public static void main(String... args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}

