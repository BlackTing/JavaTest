package com.nny.Demo.ThreadTest;

public class VolatileLearn {

    private int value = 0;
    public volatile int valuetwo = 0;//线程不安全，根据先行发生原则，why?

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static void main(String args[]){

        VolatileLearn bean = new VolatileLearn();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {

//                synchronized (bean){
//                    for(int i=0;i<10000;i++){
//                        bean.setValue(i);
//                    }
//                }

                bean.valuetwo = 5;
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {

//                synchronized (bean) {
//
//                    int value = bean.getValue();
//                    System.out.println(value);
//                }

                System.out.println(bean.valuetwo);

            }
        });


        threadA.start();
        threadB.start();
    }

}
