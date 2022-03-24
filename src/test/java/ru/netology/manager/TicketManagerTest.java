package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.domain.TicketByTimeAscComparator;
import ru.netology.repository.TicketRepository;
import ru.netology.domain.Ticket;

import java.util.Comparator;

class TicketManagerTest {
    private TicketManager manager;

    Ticket first = new Ticket(1, 1000, "AAA", "AAB", 100);
    Ticket second = new Ticket(2, 2000, "AAC", "AAE", 200);
    Ticket third = new Ticket(3, 3000, "AAA", "AAB", 300);
    Ticket five = new Ticket(5, 1500, "AAH", "AAI", 150);
    Ticket ten = new Ticket(10, 4000, "AAJ", "AAK", 250);
    Ticket seven = new Ticket(7, 2500, "AAA", "AAB", 350);

    @BeforeEach
    public void setUp() {
        manager = new TicketManager(new TicketRepository());
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(five);
        manager.add(ten);
        manager.add(seven);
    }

    @Test
    void shouldFindAll() {
        Ticket[] expected = new Ticket[]{first, seven, third};
        assertArrayEquals(expected, manager.findAll("AAA", "AAB"));
    }

    @Test
    void shouldFindAllMinByTimeFlight() {
        Comparator<Ticket> comparator = new TicketByTimeAscComparator();
        Ticket[] expected = new Ticket[]{first, third, seven};
        assertArrayEquals(expected, manager.findAllByTime("AAA", "AAB", comparator));
    }
}