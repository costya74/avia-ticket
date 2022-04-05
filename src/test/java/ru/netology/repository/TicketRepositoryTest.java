package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    private TicketRepository repository = new TicketRepository();

    Ticket first = new Ticket(1, 1000, "AAA", "AAB", 100);
    Ticket second = new Ticket(2, 2000, "AAC", "AAE", 200);
    Ticket third = new Ticket(3, 3000, "AAA", "AAB", 300);
    Ticket five = new Ticket(5, 1500, "AAH", "AAI", 150);
    Ticket ten = new Ticket(10, 4000, "AAJ", "AAK", 250);
    Ticket seven = new Ticket(7, 2500, "AAA", "AAB", 350);


    @Test
    void shouldSave() {
        repository.save(first);
        Ticket[] expected = new Ticket[]{first};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGetAll() {
        Ticket[] expected = {};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGetAllMax() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(five);
        repository.save(ten);
        repository.save(seven);

        Ticket[] expected = {first, second, third, five, ten, seven};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        repository.save(first);
        Ticket[] expected = new Ticket[]{first};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByIdNull() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = repository.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByIdNumber() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        int expected = 3;
        Ticket actual = repository.findById(3);
        assertEquals(expected, actual.getId());
    }

    @Test
    void shouldRemoveById() {
        repository.save(first);
        repository.save(second);
        repository.save(third);

        repository.removeById(2);
        Ticket[] actual = new Ticket[]{first, third};
        Ticket[] expected = new Ticket[]{first, third};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveNoExistById() {
        repository.save(first);
        repository.save(third);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(-1);
        });
    }
}
