package threads;
import config.Configuration;
import core.AbstractTicketHandler;
import core.TicketPool;
import logging.Logger;
public class Vendor extends AbstractTicketHandler implements Runnable {
    private final int ticketReleaseRate;
    private final int maxTicketCapacity;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate,int maxTicketCapacity) {
        super(ticketPool);
        this.ticketReleaseRate = ticketReleaseRate;
        this.maxTicketCapacity=maxTicketCapacity;
    }
    @Override
    public void run() {
        for (int i = 0; i <maxTicketCapacity; i++) {
            String ticket = "Ticket-" + System.nanoTime();
            ticketPool.addTickets(ticket);
            Logger.log("Vendor added: " + ticket);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Vendor interrupted.");
            }
        }
    }
    @Override
    public void handleTickets() {
        run();
    }
}