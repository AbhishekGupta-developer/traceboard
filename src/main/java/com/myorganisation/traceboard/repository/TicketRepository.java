package com.myorganisation.traceboard.repository;

import com.myorganisation.traceboard.model.Ticket;
import com.myorganisation.traceboard.model.enums.TicketCategory;
import com.myorganisation.traceboard.model.enums.TicketPriority;
import com.myorganisation.traceboard.model.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //Custom finder methods
    List<Ticket> findByCategory(TicketCategory category);
    List<Ticket> findByPriority(TicketPriority priority);
    List<Ticket> findByStatus(TicketStatus status);

    //JPQL
    @Query("SELECT t FROM Ticket t " +
            "WHERE " +
            "CAST(t.id AS string) = :q " +
            "OR LOWER(t.name) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR STR(t.createdBy) = :q " +
            "OR STR(t.assignedTo) = :q " +
            "OR LOWER(CAST(t.status AS string)) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(CAST(t.category AS string)) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(CAST(t.priority AS string)) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Ticket> searchByQuery(@Param("q") String query);

}
