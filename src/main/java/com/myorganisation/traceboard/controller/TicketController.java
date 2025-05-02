package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.TicketRequestDTO;
import com.myorganisation.traceboard.dto.TicketResponseDTO;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        return new ResponseEntity<>(ticketService.createTicket(ticketRequestDTO), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicket(@PathVariable Long id) {
        return new ResponseEntity<>(ticketService.getTicket(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id, @RequestBody TicketRequestDTO ticketRequestDTO) {
        return new ResponseEntity<>(ticketService.updateTicket(id, ticketRequestDTO), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<String> removeTicket(@RequestParam Long id) {
        return new ResponseEntity<>(ticketService.removeTicket(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TicketResponseDTO>> searchByCategory(@RequestParam TicketCategory category) {
        return new ResponseEntity<>(ticketService.searchByCategory(category), HttpStatusCode.valueOf(200));
    }
}
