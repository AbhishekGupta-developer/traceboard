package com.myorganisation.traceboard.repository;

import com.myorganisation.traceboard.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
