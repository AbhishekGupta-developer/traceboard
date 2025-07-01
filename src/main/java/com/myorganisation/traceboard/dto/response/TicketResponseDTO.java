package com.myorganisation.traceboard.dto.response;

import com.myorganisation.traceboard.model.Invoice;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.model.enums.TicketPriority;
import com.myorganisation.traceboard.model.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {

    private Long id;
    private String name;
    private Long createdBy;
    private Long assignedTo;
    private String description;
    private Date dateCreated;
    private TicketStatus status;
    private TicketCategory category;
    private TicketPriority priority;
    private Invoice invoice;
}
