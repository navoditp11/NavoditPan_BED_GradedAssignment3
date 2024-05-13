package com.gl.tta.service;

import java.util.List;

import com.gl.tta.entity.Ticket;

public interface TicketService {

	public List<Ticket> findAll();

	public void save(Ticket theTicket);

	public Ticket findById(int theTicket_id);

	public void deleteById(int theTicket_id);
	
	public List<Ticket> searchBy(String theTitle, String theDescription);

}
