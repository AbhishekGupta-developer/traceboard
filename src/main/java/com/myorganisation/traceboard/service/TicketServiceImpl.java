package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketInputDTO;
import com.myorganisation.traceboard.dto.TicketOutputDTO;
import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketOutputDTO createTicket(TicketInputDTO ticketInputDTO) {
        Ticket ticket = new Ticket();
        ticket.setName(ticketInputDTO.getName());
        ticket.setCreatedBy(ticketInputDTO.getCreatedBy());

    }

    @Override
    public TicketOutputDTO getTicket(Integer id) {
        return null;
    }

    @Override
    public List<TicketOutputDTO> getAllTickets() {
        return List.of();
    }

    @Override
    public TicketOutputDTO updateTicket(Integer id, TicketInputDTO ticketInputDTO) {
        return null;
    }

    @Override
    public String removeTicket(Integer id) {
        return "";
    }
}
