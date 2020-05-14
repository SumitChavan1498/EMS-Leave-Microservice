package com.cg.leavecontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})
@RestController
@Api(value = "Leave Service using logger and swagger")
public class LeaveController {

	@Autowired
	LeaveJPARepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(Leave.class);
	

//	@PostMapping(path = "/apply")
//	public void applyLeave(@RequestBody Leave leave) {
//
//		System.out.println("inside applyLeave() of Controller");
//		leave.setStatus("Applied");
//		repository.save(leave);
//	}
	
	@PostMapping(path = "/apply")
	@ApiOperation(value = "retrieveLimitsFromConfigurations", nickname = "retrieveLimitsFromConfigurations")
    @ApiResponses(value = {
               @ApiResponse(code = 200, message = "Success", response = Leave.class),
               @ApiResponse(code = 500, message = "Failure", response = Leave.class)})
	
	public Leave applyLeave(@RequestBody Leave leave) {
		
		System.out.println("inside applyLeave() of Controller");
		logger.info("inside applyLeave() of Controller of Leave-Service");
		
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
