package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.ErrorResponseDTO;
import com.myorganisation.traceboard.dto.TicketRequestDTO;
import com.myorganisation.traceboard.dto.TicketResponseDTO;
import com.myorganisation.traceboard.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        return new ResponseEntity<>(ticketService.createTicket(ticketRequestDTO), HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicket(@PathVariable Long id) {
        TicketResponseDTO ticketResponseDTO = ticketService.getTicket(id);
        if(ticketResponseDTO == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "Ticket not found.";
            String details = "Ticket id: " + id + " doesn't exist.";
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(localDateTime, message, details);
            return new ResponseEntity<>(errorResponseDTO, HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(ticketResponseDTO, HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id, @RequestBody TicketRequestDTO ticketRequestDTO) {
        TicketResponseDTO ticketResponseDTO = ticketService.updateTicket(id, ticketRequestDTO);
        if(ticketResponseDTO == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "Ticket not found.";
            String details = "Ticket id: " + id + " doesn't exist.";
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(localDateTime, message, details);
            return new ResponseEntity<>(errorResponseDTO, HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(ticketResponseDTO, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<?> removeTicket(@RequestParam Long id) {
        String response = ticketService.removeTicket(id);
        if(response == null) {
            LocalDateTime localDateTime = LocalDateTime.now();
            String message = "Ticket not found.";
            String details = "Ticket id: " + id + " doesn't exist.";
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(localDateTime, message, details);
            return new ResponseEntity<>(errorResponseDTO, HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TicketResponseDTO>> searchByQuery(@RequestParam("q") String query) {
        return new ResponseEntity<>(ticketService.searchByQuery(query), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TicketResponseDTO>> getTicketPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String orderBy
    ) {
        return new ResponseEntity<>(ticketService.getTicketPage(page, size, sortBy, orderBy), HttpStatusCode.valueOf(200));
    }
}
