package com.zxf;

public class Consumer implements Runnable{

    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run(){
        String food = null;
        while ((food = drop.get()) != null){
            System.out.format("生产者通知消费者生产了食物: %s%n",food);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
