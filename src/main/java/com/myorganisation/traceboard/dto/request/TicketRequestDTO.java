package com.myorganisation.traceboard.dto.request;

import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.model.enums.TicketPriority;
import com.myorganisation.traceboard.model.enums.TicketStatus;
import lombok.Data;

@Data
public class TicketRequestDTO {

    private String name;
    private Long createdBy;
    private Long assignedTo;
    private String description;
    private TicketStatus status;
    private TicketCategory category;
    private TicketPriority priority;
}
