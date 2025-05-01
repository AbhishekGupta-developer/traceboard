package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketRequestDTO;
import com.myorganisation.traceboard.dto.TicketResponseDTO;
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
    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = new Ticket();

        ticket.setName(ticketRequestDTO.getName());
        ticket.setCreatedBy(ticketRequestDTO.getCreatedBy());
        ticket.setAssignedTo(ticketRequestDTO.getAssignedTo());
        ticket.setDescription(ticketRequestDTO.getDescription());
        ticket.setDateCreated(new Date());
        ticket.setStatus(ticketRequestDTO.getStatus());
        ticket.setCategory(ticketRequestDTO.getCategory());
        ticket.setPriority(ticketRequestDTO.getPriority());

        ticket = ticketRepository.save(ticket);

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();

        ticketResponseDTO.setId(ticket.getId());
        ticketResponseDTO.setName(ticket.getName());
        ticketResponseDTO.setCreatedBy(ticket.getCreatedBy());
        ticketResponseDTO.setAssignedTo(ticket.getAssignedTo());
        ticketResponseDTO.setDescription(ticket.getDescription());
        ticketResponseDTO.setDateCreated(ticket.getDateCreated());
        ticketResponseDTO.setStatus(ticket.getStatus());
        ticketResponseDTO.setCategory(ticket.getCategory());
        ticketResponseDTO.setPriority(ticket.getPriority());

        return ticketResponseDTO;
    }

    @Override
    public TicketResponseDTO getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
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
        return ticketResponseDTO;
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {

        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketResponseDTO> ticketResponseDTOList = new ArrayList<>();

        for(Ticket ticket : ticketList) {
            TicketResponseDTO ticketResponseDTO = TicketResponseDTO.builder()
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

            ticketResponseDTOList.add(ticketResponseDTO);
        }

        return ticketResponseDTOList;
    }

    @Override
    public TicketResponseDTO updateTicket(Long id, TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        ticket.setName(ticketRequestDTO.getName());
        ticket.setCreatedBy(ticketRequestDTO.getCreatedBy());
        ticket.setAssignedTo(ticketRequestDTO.getAssignedTo());
        ticket.setDescription(ticketRequestDTO.getDescription());
        ticket.setDateCreated(new Date());
        ticket.setStatus(ticketRequestDTO.getStatus());
        ticket.setCategory(ticketRequestDTO.getCategory());
        ticket.setPriority(ticketRequestDTO.getPriority());

        ticket = ticketRepository.save(ticket);

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();

        ticketResponseDTO.setId(ticket.getId());
        ticketResponseDTO.setName(ticket.getName());
        ticketResponseDTO.setCreatedBy(ticket.getCreatedBy());
        ticketResponseDTO.setAssignedTo(ticket.getAssignedTo());
        ticketResponseDTO.setDescription(ticket.getDescription());
        ticketResponseDTO.setDateCreated(ticket.getDateCreated());
        ticketResponseDTO.setStatus(ticket.getStatus());
        ticketResponseDTO.setCategory(ticket.getCategory());
        ticketResponseDTO.setPriority(ticket.getPriority());

        return ticketResponseDTO;
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
    public List<TicketOutputDTO> searchByCategory(TicketCategory category) {
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
