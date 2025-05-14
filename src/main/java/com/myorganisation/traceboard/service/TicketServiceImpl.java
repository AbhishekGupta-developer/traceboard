package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketRequestDTO;
import com.myorganisation.traceboard.dto.TicketResponseDTO;
import com.myorganisation.traceboard.model.Invoice;
import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Ticket ticket = copyTicketRequestDTOToTicket(ticketRequestDTO, new Ticket());

        Invoice invoice = new Invoice();

        //Manual Task (before cascading)
        //invoice = invoiceRepository.save(invoice);

        ticket.setInvoice(invoice);
        invoice.setTicket(ticket);

        ticket = ticketRepository.save(ticket);

        //Manual Task (before cascading)
        //invoiceRepository.save(invoice);

        return convertTicketToTicketResponseDTO(ticket);
    }

    @Override
    public TicketResponseDTO getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        return convertTicketToTicketResponseDTO(ticket);
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {

        return convertListOfTicketToListOfTicketResponseDTO(ticketRepository.findAll());
    }

    @Override
    public TicketResponseDTO updateTicket(Long id, TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        ticket = copyTicketRequestDTOToTicket(ticketRequestDTO, ticket);

        ticket = ticketRepository.save(ticket);

        return convertTicketToTicketResponseDTO(ticket);
    }

    @Override
    public String removeTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if(ticket == null) {
            return "Ticket doesn't exist!";
        } else {
            String name = ticket.getName();
            ticketRepository.deleteById(id);
            return "Ticket: " + name + " (" + id + "), deleted successfully!";
        }
    }

    @Override
    public List<TicketResponseDTO> searchByQuery(String query) {
        return convertListOfTicketToListOfTicketResponseDTO(ticketRepository.searchByQuery(query));
    }

    @Override
    public Page<TicketResponseDTO> getTicketPage(Integer page, Integer size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Ticket> ticketPage = ticketRepository.findAll(pageable);

        Page<TicketResponseDTO> ticketResponseDTOPage = ticketPage.map(ticket -> convertTicketToTicketResponseDTO(ticket));

        return ticketResponseDTOPage;
    }

    //Helper method to copy TicketRequestDTO to Ticket
    public Ticket copyTicketRequestDTOToTicket(TicketRequestDTO ticketRequestDTO, Ticket ticket) {
        ticket.setName(ticketRequestDTO.getName());
        ticket.setCreatedBy(ticketRequestDTO.getCreatedBy());
        ticket.setAssignedTo(ticketRequestDTO.getAssignedTo());
        ticket.setDescription(ticketRequestDTO.getDescription());
        ticket.setDateCreated(new Date());
        ticket.setStatus(ticketRequestDTO.getStatus());
        ticket.setCategory(ticketRequestDTO.getCategory());
        ticket.setPriority(ticketRequestDTO.getPriority());

        return ticket;
    }

    //Helper method to convert Ticket to TicketResponseDTO
    public TicketResponseDTO convertTicketToTicketResponseDTO(Ticket ticket) {
        return TicketResponseDTO.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .createdBy(ticket.getCreatedBy())
                .assignedTo(ticket.getAssignedTo())
                .description(ticket.getDescription())
                .dateCreated(ticket.getDateCreated())
                .status(ticket.getStatus())
                .category(ticket.getCategory())
                .priority(ticket.getPriority())
                .invoice(ticket.getInvoice())
                .build();
    }

    //Helper method to convert List<Ticket> to List<TicketResponseDTO>
    public List<TicketResponseDTO> convertListOfTicketToListOfTicketResponseDTO(List<Ticket> ticketList) {
        List<TicketResponseDTO> ticketResponseDTOList = new ArrayList<>();

        for(Ticket ticket : ticketList) {
            TicketResponseDTO ticketResponseDTO = convertTicketToTicketResponseDTO(ticket);

            ticketResponseDTOList.add(ticketResponseDTO);
        }

        return ticketResponseDTOList;
    }

}
