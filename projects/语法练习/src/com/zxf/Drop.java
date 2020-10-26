package com.zxf;

public class Drop {

    private String food;

    /*
    * empty true，消费者等待生产者 生产产品
    * empty false，生产者等待消费者 消费产品
    * */
    private boolean empty;

    /**
     * get方法是在消费者线程中执行的
     * @return 事物
     */
    public synchronized String get() {
        while (empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return food;
    }

    /**
     * add方法是在生产者线程中执行的
     * @param food
     */
    public synchronized void add(String food) {
        while (!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.food = food;
        notifyAll();
    }

}
