package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketInputDTO;
import com.myorganisation.traceboard.dto.TicketOutputDTO;
import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketOutputDTO> ticketOutputDTOList = new ArrayList<>();

        for(Ticket ticket : ticketList) {
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

            ticketOutputDTOList.add(ticketOutputDTO);
        }

        return ticketOutputDTOList;
    }

    @Override
    public TicketOutputDTO updateTicket(Long id, TicketInputDTO ticketInputDTO) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

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
    public String removeTicket(Long id) {
        String name = ticketRepository.findById(id).orElse(null).getName();

        if(name == null || name.equals(null)) {
            return "Ticket doesn't exist!";
        }

        ticketRepository.deleteById(id);

        return "Ticket: " + name + " (" + id + "), deleted successfully!";
    }

    @Override
    public String removeAllTickets() {
        ticketRepository.deleteAll();
        return "All tickets deleted successfully";
    }

    @Override
    public List<TicketOutputDTO> searchByCategory(TicketCategory category){
        List<Ticket> ticketList = ticketRepository.findByCategory(category);

        List<TicketOutputDTO> ticketOutputDTOList = new ArrayList<>();

        for(Ticket ticket : ticketList) {
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

            ticketOutputDTOList.add(ticketOutputDTO);
        }

        return ticketOutputDTOList;


    }
}
