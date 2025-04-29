package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketInputDTO;
import com.myorganisation.traceboard.dto.TicketOutputDTO;
import com.myorganisation.traceboard.model.enums.TicketCategory;

import java.util.List;

public interface TicketService {
    TicketOutputDTO createTicket(TicketInputDTO ticketInputDTO);
    TicketOutputDTO getTicket(Long id);
    List<TicketOutputDTO> getAllTickets();
    TicketOutputDTO updateTicket(Long id, TicketInputDTO ticketInputDTO);
    String removeTicket(Long id);
    List<TicketOutputDTO> searchByCategory(TicketCategory category);

}
