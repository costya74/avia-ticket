package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;

public class TicketRepository {
    private Ticket[] tickets = new Ticket[0];

    // команда принимает и сохраняет в памяти
    public void save(Ticket ticket) {
        // вычисляем длину массива и создаем массив размером на 1 ячейку больше
        int length = tickets.length + 1;
//  создаем новый массив
        Ticket[] tmp = new Ticket[length];
// просим выдать: ячейку от куда копир, № позиции от куда копир, куда копир, от куда, какое кол-во эл-тов
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
//    вычисляем номер последней ячейки
        int lastIndex = tmp.length - 1;
//  копир в новую ячейку
        tmp[lastIndex] = ticket;
//        сохраняем в ячейке
        tickets = tmp;
    }

    public Ticket[] getAll() {
        return tickets;
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void removeById(int id) {
        int length = tickets.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        // Дай мне номер массива, если =, то копир и удаляй
        for (Ticket ticket : tickets) {
            if (ticket.getId() != id) {
                tmp[index] = ticket;
                index++;
            }
            if (findById(id) == null) {
                throw new NotFoundException("удаление не возможно, так как продукт " + id + " не существует");
            }
        }
        tickets = tmp;
    }
}
