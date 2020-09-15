package com.zxf;

public class Producer implements Runnable{

    private Drop drop;
    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String[] foods = {"beef","bread","apple","cookie"};
        for (int i = 0; i < foods.length; i++) {
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            // 将foods[i]传递给消费者
            drop.add(foods[i]);
        }
        // 告诉消费者不会再生产任何东西
        drop.add(null);
    }
}
