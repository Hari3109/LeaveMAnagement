package com.cg.LeaveManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.LeaveManagement.Entity.Manager;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer>{

}
