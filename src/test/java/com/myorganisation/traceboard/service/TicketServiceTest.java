package com.myorganisation.traceboard.service;

import com.myorganisation.traceboard.dto.request.TicketRequestDTO;
import com.myorganisation.traceboard.dto.response.TicketResponseDTO;
import com.myorganisation.traceboard.model.Invoice;
import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.model.enums.TicketPriority;
import com.myorganisation.traceboard.model.enums.TicketStatus;
import com.myorganisation.traceboard.repository.TicketRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    TicketServiceImpl ticketService;

    @BeforeAll
    public static void methodToExecuteBeforeAllTest() {
        System.out.println("Before All");
    }

    @BeforeEach
    public void methodToExecuteBeforeEachTest() {
        System.out.println("Before Each");
    }

    @Test
    public void createTicketShouldAddNewTicketInDB() {
        System.out.println("Inside: createTicketShouldAddNewTicketInDB TEST");
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
        Date date = new Date();
        ticket.setDateCreated(date);
        ticket.setStatus(TicketStatus.TO_DO);
        ticket.setCategory(TicketCategory.BUG);
        ticket.setPriority(TicketPriority.MAX);
        ticket.setInvoice(new Invoice());

        Mockito.when(ticketRepository.save(Mockito.any(Ticket.class)))
                .thenReturn(ticket);

        TicketResponseDTO ticketResponseDTO = ticketService.createTicket(ticketRequestDTO);

        Assertions.assertNotNull(ticketResponseDTO);
        Assertions.assertEquals(ticket.getId(), ticketResponseDTO.getId());
        Assertions.assertEquals("JUnit Test Ticket", ticketResponseDTO.getName());
        Assertions.assertEquals(1L, ticketResponseDTO.getCreatedBy());
        Assertions.assertEquals(2L, ticketResponseDTO.getAssignedTo());
        Assertions.assertEquals("Its a test ticket to demonstrate JUnit and Mockito", ticketResponseDTO.getDescription());
        Assertions.assertEquals(date, ticketResponseDTO.getDateCreated());
        Assertions.assertTrue(ticketResponseDTO.getStatus() == TicketStatus.TO_DO);
        Assertions.assertEquals(TicketCategory.BUG, ticketResponseDTO.getCategory());
        Assertions.assertEquals(TicketPriority.MAX, ticketResponseDTO.getPriority());
        Assertions.assertNotNull(ticketResponseDTO.getInvoice());
    }

    @Test
    public void getTicketShouldGiveTicket() {
        System.out.println("Inside: getTicketShouldGiveTicket TEST");
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setName("JUnit Test Ticket");
        ticket.setCreatedBy(1L);
        ticket.setAssignedTo(2L);
        ticket.setDescription("Its a test ticket to demonstrate JUnit and Mockito");
        ticket.setDateCreated(new Date());
        ticket.setStatus(TicketStatus.TO_DO);
        ticket.setCategory(TicketCategory.BUG);
        ticket.setPriority(TicketPriority.MAX);
        ticket.setInvoice(new Invoice());

        Optional<Ticket> ticketOptional = Optional.of(ticket);

        Mockito.when(ticketRepository.findById(Mockito.any(Long.class)))
                .thenReturn(ticketOptional);

        TicketResponseDTO ticketResponseDTO = ticketService.getTicket(1L);

        Assertions.assertNotNull(ticketResponseDTO);
        Assertions.assertEquals(1, ticketResponseDTO.getId());
        Assertions.assertEquals(ticket.getId(), ticketResponseDTO.getId());
        Assertions.assertTrue(ticketResponseDTO.getStatus() == TicketStatus.TO_DO);
    }

    @AfterEach
    public void methodToExecuteAfterEachTest() {
        System.out.println("After Each");
    }

    @AfterAll
    public static void methodToExecuteAfterAllTest() {
        System.out.println("After All");
    }

}
