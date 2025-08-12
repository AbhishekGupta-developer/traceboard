package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.request.TicketRequestDTO;
import com.myorganisation.traceboard.dto.response.TicketResponseDTO;
import com.myorganisation.traceboard.model.Invoice;
import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.model.enums.TicketPriority;
import com.myorganisation.traceboard.model.enums.TicketStatus;
import com.myorganisation.traceboard.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketServiceImpl ticketService;

    @Test
    public void createTicketShouldAddNewTicketInDB() {
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        ticketRequestDTO.setName("JUnit Test Ticket");
        ticketRequestDTO.setCreatedBy(1L);
        ticketRequestDTO.setAssignedTo(2L);
        ticketRequestDTO.setDescription("Its a test ticket to demonstrate JUnit and Mockito");
        ticketRequestDTO.setStatus(TicketStatus.TO_DO);
        ticketRequestDTO.setCategory(TicketCategory.BUG);
        ticketRequestDTO.setPriority(TicketPriority.MAX);

        Ticket ticket = new Ticket();
        ticket.setId(10L);
        ticket.setName("JUnit Test Ticket");
        ticket.setCreatedBy(1L);
        ticket.setAssignedTo(2L);
        ticket.setDescription("Its a test ticket to demonstrate JUnit and Mockito");
        ticket.setDateCreated(new Date());
        ticket.setStatus(TicketStatus.TO_DO);
        ticket.setCategory(TicketCategory.BUG);
        ticket.setPriority(TicketPriority.MAX);
        ticket.setInvoice(new Invoice());

        Mockito.when(ticketRepository.save(Mockito.any(Ticket.class)))
                .thenReturn(ticket);

        TicketResponseDTO ticketResponseDTO = ticketService.createTicket(ticketRequestDTO);

        Assertions.assertNotNull(ticketResponseDTO);
        Assertions.assertEquals(ticket.getId(), ticketResponseDTO.getId());
        Assertions.assertTrue(ticketResponseDTO.getStatus() == TicketStatus.TO_DO);
    }

}
