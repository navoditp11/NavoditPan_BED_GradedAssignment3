package com.gl.tta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.tta.entity.Ticket;
import com.gl.tta.repository.TicketsRepository;
import com.gl.tta.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	TicketsRepository ticketsRepository;

	@Override
	public List<Ticket> findAll() {
		
		List<Ticket> tickets = ticketsRepository.findAll();
		return tickets;
	}

	@Override
	public void save(Ticket theTicket) {
		ticketsRepository.save(theTicket);
		
	}

	@Override
	public Ticket findById(int theTicket_id) {
		return ticketsRepository.findById(theTicket_id).get();
	}

	@Override
	public void deleteById(int theTicket_id) {
		ticketsRepository.deleteById(theTicket_id);
		
	}

	@Override
	public List<Ticket> searchBy(String theTitle, String theDescription) {
		List<Ticket> tickets=ticketsRepository.findByTitleContainsAndDescriptionContainsAllIgnoreCase(theTitle, theDescription);
		return tickets;
	}

}
