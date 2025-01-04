package core;

import config.Configuration;

import java.util.*;

public class TicketPool implements TicketOperation {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private final Configuration config;

    public TicketPool(Configuration config) {
        this.config = config;
    }


    @Override
    public synchronized void addTickets(String ticket) {
        while (tickets.size() >= config.getMaxTicketCapacity()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        notifyAll();
    }

    @Override
    public synchronized String removeTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        String ticket = tickets.remove(0);
        return ticket;
    }

    public synchronized int getTicketCount() {
        return tickets.size();
    }

}
