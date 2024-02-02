package com.emp.service;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.emp.dao.EmpRepo;
import com.emp.dao.PhoneRepo;
import com.emp.entity.Employee;
import com.emp.entity.Phone;
import com.emp.modal.EmployeeVo;
import com.emp.modal.PhoneVo;
import com.emp.util.EmpException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpService {
	@Autowired
	PhoneRepo phoneRepo;
	@Autowired
	EmpRepo empRepo;

	@Transactional
	public EmployeeVo getEmp(Long empId) {
		if (Objects.isNull(empId)) {
			throw new EmpException("Employee id empty");
		}
		Optional<Employee> employeeOptional = empRepo.findById(empId);
		if (!employeeOptional.isPresent()) {
			throw new EmpException("Employee not found");
		}
		Employee employee = employeeOptional.get();
		EmployeeVo employeeVo = new EmployeeVo();
		BeanUtils.copyProperties(employee, employeeVo);
		if (!CollectionUtils.isEmpty(employee.getPhones())) {
			List<PhoneVo> phoneVos = employee.getPhones().stream().map(item -> {
				PhoneVo phoneVo = new PhoneVo();
				BeanUtils.copyProperties(item, phoneVo);
				return phoneVo;
			}).collect(Collectors.toList());
			employeeVo.setPhones(phoneVos);
		}
		return employeeVo;
	}

	@Transactional
	public List<EmployeeVo> getEmpList() {
		List<Employee> employees = empRepo.findAll();
		List<EmployeeVo> employeeVos = employees.stream().map(employee -> {
			EmployeeVo employeeVo = new EmployeeVo();
			BeanUtils.copyProperties(employee, employeeVo);
			if (!CollectionUtils.isEmpty(employee.getPhones())) {
				List<PhoneVo> phoneVos = employee.getPhones().stream().map(phone -> {
					PhoneVo phoneVo = new PhoneVo();
					BeanUtils.copyProperties(phone, phoneVo);
					return phoneVo;
				}).collect(Collectors.toList());
				employeeVo.setPhones(phoneVos);
			}
			return employeeVo;
		}).collect(Collectors.toList());
		return employeeVos;
	}

	@Transactional
	public EmployeeVo saveEmp(EmployeeVo employeeVo) {

		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeVo, employee);
		employee.setEmpId(generateEmpId());
		employee.setCreatedBy("system");
		employee.setUpdatedBy("system");
		employee.setCreatedDate(new Date());
		employee.setUpdatedDate(new Date());
		empRepo.save(employee);

		log.info("saving employee phone nos");
		List<Phone> phones = employeeVo.getPhones().stream().map(item -> new Phone(item, employee))
				.collect(Collectors.toList());
		phoneRepo.saveAll(phones);

		List<PhoneVo> phoneVos = phones.stream()
				.map(item -> new PhoneVo(item.getId(), item.getType(), item.getPhoneNo())).collect(Collectors.toList());

		employeeVo.setId(employee.getId());
		employeeVo.setPhones(phoneVos);

		return employeeVo;
	}

	@Transactional
	public EmployeeVo updateEmp(EmployeeVo employeeVo) {
		if (Objects.isNull(employeeVo.getId())) {
			throw new EmpException("Employee id empty");
		}
		Optional<Employee> empOptional = empRepo.findById(employeeVo.getId());
		if (!empOptional.isPresent()) {
			throw new EmpException("Employee not found");
		}
		Employee employee = empOptional.get();
		employee.setEmail(employeeVo.getEmail());
		employee.setFirstName(employeeVo.getFirstName());
		employee.setLastName(employeeVo.getLastName());
		employee.setSalary(employeeVo.getSalary());
		empRepo.save(employee);
		return employeeVo;
	}

	@Transactional
	public void deleteEmp(Long empId) {
		if (Objects.isNull(empId))
			throw new EmpException("Employee id not found");
		Employee employee = empRepo.getById(empId);
		if (Objects.isNull(employee))
			throw new EmpException("Employee not found");

		phoneRepo.deleteByEmployee(employee);
		empRepo.deleteById(empId);
	}

	private void validateStr(String propName, String propValue, String errMsg, Map<String, String> errMap) {
		if (!StringUtils.hasText(propValue) || !StringUtils.hasLength(propValue)) {
			errMap.put(propName, errMsg);
		}
	}

	private void validateDoj(String propName, Date propValue, String errMsg, Map<String, String> errMap) {
		if (Objects.isNull(propValue)) {
			errMap.put(propName, errMsg);
		}
	}

	private void validateSal(String propName, Double propValue, String errMsg, Map<String, String> errMap) {
		if (Objects.isNull(propValue)) {
			errMap.put(propName, errMsg);
		}
	}

	private void validatePhone(String propName, List<String> propValue, String errMsg, Map<String, String> errMap) {
		if (Objects.isNull(propValue) || CollectionUtils.isEmpty(propValue)) {
			errMap.put(propName, errMsg);
		}
	}

	private String generateEmpId() {
		return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
	}

}
