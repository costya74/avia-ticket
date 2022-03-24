package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.Comparator;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TicketManager {
    public TicketRepository repository;        // связали менеджер с репозиторием

    //    создаем продукт в корзину
    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    //создаем метод поиска по вылету(от) и прилету(к)
    public Ticket[] findAll(String from, String to) {
        // создаем новый массив с нуля
        Ticket[] result = new Ticket[0];
        // перебираем все билеты в репозитории
        for (Ticket ticket : repository.getAll()) {
            // если билет вылета и прилета один
            if (ticket.getDeparture().contains(from) && ticket.getArrival().contains(to)) {
                //берем билет из ячейки и кладем в новую создавая массив с большим размером
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result); // сортируем по результату, после чего выводим
        return result;
    }

    //создаем метод поиска по времени перелета
    public Ticket[] findAllByTime(String from, String to, Comparator<Ticket> comparator) {
        // создаем новый массив с нуля
        Ticket[] result = new Ticket[0];
        // перебираем все билеты в репозитории
        for (Ticket ticket : repository.getAll()) {
            // если билет вылета и прилета один
            if (ticket.getDeparture().contains(from) && ticket.getArrival().contains(to)) {
                //берем билет из ячейки и кладем в новую создавая массив с большим размером
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result, comparator); // сортируем по результату, после чего выводим
        return result;


    }
}