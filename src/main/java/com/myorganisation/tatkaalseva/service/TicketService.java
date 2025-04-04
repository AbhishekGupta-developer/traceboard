package com.myorganisation.tatkaalseva.service;

import com.myorganisation.tatkaalseva.dto.TicketInputDTO;
import com.myorganisation.tatkaalseva.dto.TicketOutputDTO;

import java.util.List;

public interface TicketService {
    TicketOutputDTO createTicket(TicketInputDTO ticketInputDTO);
    TicketOutputDTO getTicket(Integer id);
    List<TicketOutputDTO> getAllTickets();
    TicketOutputDTO updateTicket(Integer id, TicketInputDTO ticketInputDTO);
    String removeTicket(Integer id);

}
