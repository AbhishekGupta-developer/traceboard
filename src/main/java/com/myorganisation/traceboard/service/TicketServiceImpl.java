package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketInputDTO;
import com.myorganisation.traceboard.dto.TicketOutputDTO;
import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketOutputDTO createTicket(TicketInputDTO ticketInputDTO) {
        Ticket ticket = new Ticket();

        ticket.setName(ticketInputDTO.getName());
        ticket.setCreatedBy(ticketInputDTO.getCreatedBy());
        ticket.setAssignedTo(ticketInputDTO.getAssignedTo());
        ticket.setDescription(ticketInputDTO.getDescription());
        ticket.setDateCreated(new Date());
        ticket.setStatus(ticketInputDTO.getStatus());
        ticket.setCategory(ticketInputDTO.getCategory());
        ticket.setPriority(ticketInputDTO.getPriority());

        ticket = ticketRepository.save(ticket);

        TicketOutputDTO ticketOutputDTO = new TicketOutputDTO();

        ticketOutputDTO.setId(ticket.getId());
        ticketOutputDTO.setName(ticket.getName());
        ticketOutputDTO.setCreatedBy(ticket.getCreatedBy());
        ticketOutputDTO.setAssignedTo(ticket.getAssignedTo());
        ticketOutputDTO.setDescription(ticket.getDescription());
        ticketOutputDTO.setDateCreated(ticket.getDateCreated());
        ticketOutputDTO.setStatus(ticket.getStatus());
        ticketOutputDTO.setCategory(ticket.getCategory());
        ticketOutputDTO.setPriority(ticket.getPriority());

        return ticketOutputDTO;
    }

    @Override
    public TicketOutputDTO getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        TicketOutputDTO ticketOutputDTO = TicketOutputDTO.builder()
                                                .id(ticket.getId())
                                                .name(ticket.getName())
                                                .createdBy(ticket.getCreatedBy())
                                                .assignedTo(ticket.getAssignedTo())
                                                .description(ticket.getDescription())
                                                .dateCreated(ticket.getDateCreated())
                                                .status(ticket.getStatus())
                                                .category(ticket.getCategory())
                                                .priority(ticket.getPriority())
                                            .build();
        return ticketOutputDTO;
    }

    @Override
    public List<TicketOutputDTO> getAllTickets() {
        return List.of();
    }

    @Override
    public TicketOutputDTO updateTicket(Long id, TicketInputDTO ticketInputDTO) {
        return null;
    }

    @Override
    public String removeTicket(Long id) {
        return "";
    }
}
