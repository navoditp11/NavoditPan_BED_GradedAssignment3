package com.gl.tta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.tta.entity.Ticket;
import com.gl.tta.service.TicketService;

@Controller
@RequestMapping("/admin")
public class TicketsTrackerController {
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/welcome")
	public String greet() {
		return "Welcome";
	}
	
	@RequestMapping("/list")
	public String listTickets(Model theModel) {
		
		//get tickets from the DB
		List<Ticket> theTickets = ticketService.findAll();
		
		//add the tickets to the spring model
		theModel.addAttribute("tickets", theTickets);
		
		return "admin/list-tickets";		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Ticket theTicket = new Ticket();
		
		theModel.addAttribute("ticket", theTicket);
		
		return "admin/ticket-form";
	}
	
	@PostMapping("/save")
	public String saveTicket(
			@ModelAttribute("ticket") Ticket theTicket) {
		
		ticketService.save(theTicket);
		
		return "redirect:/admin/list";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theTicket_id,
			Model theModel) {
		
		//get the particular ticket from the service
		Ticket theTicket = ticketService.findById(theTicket_id);
		
		//set this ticket as a model attribute to pre-populate the form
		theModel.addAttribute("ticket", theTicket);
		
		//send over to the form
		return "admin/update-ticket-form";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theTicket_id) {
		
		//delete the ticket from DB
		ticketService.deleteById(theTicket_id);
		
		return "redirect:/admin/list";
	}
	
	@RequestMapping("/showFormForView")
	public String showFormForView(@RequestParam("id") int theTicket_id,
			Model theModel) {
		
		//get the particular ticket from the service
		Ticket theTicket = ticketService.findById(theTicket_id);
		
		//set this ticket as a model attribute to pre-populate the form
		theModel.addAttribute("ticket", theTicket);
		
		//send over to the form
		return "admin/view-ticket";
	}
	
	@RequestMapping("/search")
    public String search(@RequestParam("title") String theTitle, @RequestParam("description") String theDescription, Model theModel) {

      // Check the title and the description
      // If both of them are empty, then just give list of all tickets
      if (theTitle.trim().isEmpty() && theDescription.trim().isEmpty()) {
        return "redirect:/admin/list-tickets";
      } else {

        // Else, search by 'title' and 'author'
        List<Ticket> theTickets = ticketService.searchBy(theTitle, theDescription);

        // Add it to the UI Model
        theModel.addAttribute("tickets", theTickets);

        return "admin/list-tickets";
      }
    }

}
