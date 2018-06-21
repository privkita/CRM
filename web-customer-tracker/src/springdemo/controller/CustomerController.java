package springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springdemo.entity.Customer;
import springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject CustomerService
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		// get customers list
		List<Customer> customers = customerService.getCustomers();
		
		// add customers list to model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormAdd")
	public String showFormAdd(Model model) {
		
		Customer theCustomer = new Customer();
		model.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save Customer
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	@GetMapping("showFormUpdate")
	public String showFormUpdate(@RequestParam("customerId") int id, Model model) {
		
		// get the customer from service
		Customer theCustomer = customerService.getCustomer(id);
		
		// set the customer as a model attribute
		model.addAttribute("customer", theCustomer);
		
		// send over to form
		return "customer-form";
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		// delete customer by primary key
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") 
								String theSearchName, Model model) {
		
		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		// add customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}

}
