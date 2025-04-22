package com.myorganisation.traceboard.controller;

import com.myorganisation.traceboard.dto.TicketInputDTO;
import com.myorganisation.traceboard.dto.TicketOutputDTO;
import com.myorganisation.traceboard.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketOutputDTO> createTicket(@RequestBody TicketInputDTO ticketInputDTO) {
        return new ResponseEntity<>(ticketService.createTicket(ticketInputDTO), HttpStatusCode.valueOf(200));
    }
}
