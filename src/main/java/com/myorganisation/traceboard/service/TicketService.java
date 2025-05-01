package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketRequestDTO;
import com.myorganisation.traceboard.dto.TicketResponseDTO;
import com.myorganisation.traceboard.model.enums.TicketCategory;

import java.util.List;

public interface TicketService {
    TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO);
    TicketResponseDTO getTicket(Long id);
    List<TicketResponseDTO> getAllTickets();
    TicketResponseDTO updateTicket(Long id, TicketRequestDTO ticketRequestDTO);
    String removeTicket(Long id);
    List<TicketResponseDTO> searchByCategory(TicketCategory category);

}
