package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.request.TicketRequestDTO;
import com.myorganisation.traceboard.dto.response.TicketResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService {
    TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDTO);
    TicketResponseDTO getTicket(Long id);
    List<TicketResponseDTO> getAllTickets();
    TicketResponseDTO updateTicket(Long id, TicketRequestDTO ticketRequestDTO);
    String removeTicket(Long id);
    List<TicketResponseDTO> searchByQuery(String query);
    Page<TicketResponseDTO> getTicketPage(Integer page, Integer size, String sortBy, String orderBy);
}
