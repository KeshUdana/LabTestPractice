package threads;
import core.AbstractTicketHandler;
import core.TicketPool;
import logging.Logger;

import java.time.LocalDateTime;

public class Customer extends AbstractTicketHandler implements Runnable {
    public LocalDateTime Time;

    public Customer(TicketPool ticketPool) {
        super(ticketPool);
    }
    @Override
    public void run() {
        while (true) {
            String ticket = ticketPool.removeTicket();
            if (ticket != null) {

                Logger.log("Customer retrieved: " + ticket+
                        " -- Time: "+LocalDateTime.now());


            } else {
                Logger.log("Customer found no tickets available.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Customer interrupted.");
            }
        }
    }
    @Override
    public void handleTickets() {
        run();
    }
}