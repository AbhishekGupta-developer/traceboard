package com.myorganisation.tatkaalseva.dao;

import com.myorganisation.tatkaalseva.enums.TicketCategory;
import com.myorganisation.tatkaalseva.enums.TicketPriority;
import com.myorganisation.tatkaalseva.enums.TicketStatus;

import java.util.Date;

public class Ticket {

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
