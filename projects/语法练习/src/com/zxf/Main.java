package com.zxf;

public class Main {

    public static void saleTickets(){
        Station station = new Station();
        for (int i = 1; i <= 4; i++) {
            Thread thread = new Thread(station);
            thread.setName("" + i);
            thread.start();
        }
    }

    public static void test1(){
        new Thread(() -> {
            synchronized ("1"){
                System.out.println("1 - 1");
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized ("2"){
                    System.out.println("1 - 2");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized ("2"){
                System.out.println("2 - 1");
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized ("1"){
                    System.out.println("2 - 2");
                }
            }
        }).start();
    }

    public static void test2(){
        Person jack = new Person("Jack");
        Person rose = new Person("Rose");
        new Thread(() -> { jack.hello(rose); }).start();
        new Thread(() -> { rose.hello(jack); }).start();
    }

    public static void main(String[] args) {

        Drop drop = new Drop();
        (new Thread(new Consumer(drop))).start();
        (new Thread(new Producer(drop))).start();

        /*
        * 1，调用wait, notify必须是同一个 obj 对象
        * 2，调用wait, notify的线程必须拥有obj对象的内部锁
        * */

    }

}
