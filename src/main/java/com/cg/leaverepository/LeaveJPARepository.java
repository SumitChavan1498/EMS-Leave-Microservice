package com.cg.leaverepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.leavemodel.Leave;

@Repository
public interface LeaveJPARepository extends JpaRepository<Leave, Integer> {
	
	public List<Leave> findLeaveBymanagerId(int managerId);
	public List<Leave> findLeaveByempId(int empId);

}
