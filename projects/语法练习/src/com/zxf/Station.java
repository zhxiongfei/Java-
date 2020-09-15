package com.zxf;

public class Station implements Runnable{
    private int tickets = 100;

    /**
     * 卖一张票 同步语句线程安全
     * @return 是否还有票可卖
     */
    public boolean saleTicket(){
        synchronized (this) {
            if (tickets < 1) return false;
            tickets--;
            String name = Thread.currentThread().getName();
            System.out.println("线程" + name + "卖了一张票,还剩" + tickets + "张");
            return tickets > 0;
        }
    }

    /**
     * 卖一张票 同步方法线程安全
     * @return 是否还有票可卖
     */
    public synchronized boolean saleTickets(){
        if (tickets < 1) return false;
        tickets--;
        String name = Thread.currentThread().getName();
        System.out.println("线程" + name + "卖了一张票,还剩" + tickets + "张");
        return tickets > 0;
    }

    @Override
    public void run() {
        while (saleTickets());
    }
}
