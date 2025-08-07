package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.request.TicketRequestDTO;
import com.myorganisation.traceboard.dto.response.TicketResponseDTO;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.model.enums.TicketPriority;
import com.myorganisation.traceboard.model.enums.TicketStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TicketService ticketService;

    @Test
    void checkCreateNewTicket() {
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        ticketRequestDTO.setName("Test Ticket");
        ticketRequestDTO.setCreatedBy(1l);
        ticketRequestDTO.setAssignedTo(1L);
        ticketRequestDTO.setDescription("Test ticket from UT");
        ticketRequestDTO.setStatus(TicketStatus.TO_DO);
        ticketRequestDTO.setCategory(TicketCategory.BUG);
        ticketRequestDTO.setPriority(TicketPriority.MAX);

        TicketResponseDTO ticketResponseDTO = ticketService.createTicket(ticketRequestDTO);

        System.out.println(ticketResponseDTO.getName());
    }
}
