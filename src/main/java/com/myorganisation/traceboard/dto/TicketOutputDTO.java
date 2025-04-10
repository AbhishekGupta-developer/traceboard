package com.myorganisation.traceboard.dto;

import com.myorganisation.traceboard.enums.TicketCategory;
import com.myorganisation.traceboard.enums.TicketPriority;
import com.myorganisation.traceboard.enums.TicketStatus;

import java.util.Date;

public class TicketOutputDTO {

    private Integer id;
    private String name;
    private Integer createdBy;
    private Integer assignedTo;
    private String description;
    private Date dateCreated;
    private TicketStatus status;
    private TicketCategory category;
    private TicketPriority priority;
}
