package com.cg.leavecontroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.leavemodel.Leave;
import com.cg.leaverepository.LeaveJPARepository;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
public class LeaveController {

	@Autowired
	LeaveJPARepository repository;

//	@PostMapping(path = "/apply")
//	public void applyLeave(@RequestBody Leave leave) {
//
//		System.out.println("inside applyLeave() of Controller");
//		leave.setStatus("Applied");
//		repository.save(leave);
//	}
	
	@PostMapping(path = "/apply")
	public Leave applyLeave(@RequestBody Leave leave) {
		
		System.out.println("inside applyLeave() of Controller");
		if(leave.getUtilized()+leave.getNumberOfDays()<=12) {
			leave.setStatus("Applied");
			repository.save(leave);
			return(leave);
		}
		else {
			return(null);
		}
	}
	

	@GetMapping(path = "/status")
	public List<Leave> retrieveStatus() {
		System.out.println("inside retrieveStatus() of Controller");
		return repository.findAll();

	}

	@PutMapping(path = "/response/{empId}")
	public Leave updateDetails(@PathVariable int empId, @RequestBody Leave leave) {
		
		System.out.println("inside updateDetails() of Controller");
		if(leave.getStatus().equals("Approved")) {
			leave.setUtilized(leave.getUtilized()+leave.getNumberOfDays());
		}
		repository.deleteById(leave.getLeaveId());
		repository.save(leave);
		return leave;
	}
	
	@GetMapping(path = "/getLeave/{managerId}")
	public List<Leave> getLeaveByManagerId(@PathVariable int managerId) {
		System.out.println("inside getLeaveByManagerId() of Controller");
		return repository.findLeaveBymanagerId(managerId);

	}
	
	@GetMapping(path = "/getMyLeaves/{empId}")
	public List<Leave> getLeaveByEmpId(@PathVariable int empId) {
		System.out.println("inside getLeaveByEmpId() of Controller");
		return repository.findLeaveByempId(empId);

	}
	
	@GetMapping(path = "/getPendingLeaves/{managerId}")
	public List<Leave> getPendingLeaveByEmpId(@PathVariable int managerId) {
		System.out.println("inside getLeaveByEmpId() of Controller");
		return repository.findLeaveBymanagerId(managerId).stream().filter(leav ->leav.getStatus().equals("Applied")).collect(Collectors.toList());

	}

}


//
//{
//	  "empId": 100000,
//  "status": "Applied",
//  "total": 12,
//  "utilized": 2,
//  "balance": 10,
//  "dateOfApplication": "2020-02-12",
//  "dateOfLeave": "2020-02-14",
//  "numberOfDays": 3,
//  "managerId": 100100
//	}
