package com.gl.tta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.tta.entity.Ticket;

public interface TicketsRepository extends JpaRepository<Ticket, Integer>{

	List<Ticket> findByTitleContainsAndDescriptionContainsAllIgnoreCase(String ticket_title, String ticket_description);

}
