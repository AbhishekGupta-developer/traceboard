package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.TicketInputDTO;
import com.myorganisation.traceboard.dto.TicketOutputDTO;

import java.util.List;

public interface TicketService {
    TicketOutputDTO createTicket(TicketInputDTO ticketInputDTO);
    TicketOutputDTO getTicket(Integer id);
    List<TicketOutputDTO> getAllTickets();
    TicketOutputDTO updateTicket(Integer id, TicketInputDTO ticketInputDTO);
    String removeTicket(Integer id);

}
