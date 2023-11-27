package com.emp.service;

import com.emp.dao.EmpRepo;
import com.emp.dao.PhoneRepo;
import com.emp.entity.Employee;
import com.emp.entity.Phone;
import com.emp.modal.EmployeeVo;
import com.emp.modal.PhoneVo;
import com.emp.util.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class EmpService {
    @Autowired
    PhoneRepo phoneRepo;
    @Autowired
    EmpRepo empRepo;

    @Transactional
    public EmployeeVo saveEmp(EmployeeVo employeeVo) {

        log.info("validating employee details");
        validateEmp(employeeVo);

        log.info("saving employee details");
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeVo, employee);
        employee.setCreatedBy("system");
        employee.setUpdatedBy("system");
        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());
        empRepo.save(employee);

        log.info("saving employee phone nos");
        List<Phone> phones = employeeVo.getPhoneNoList().stream().map(item -> new Phone(item, employee)).collect(Collectors.toList());
        phoneRepo.saveAll(phones);

        List<PhoneVo> phoneVos = phones.stream().map(item -> new PhoneVo(item.getId(), item.getPhoneNo())).collect(Collectors.toList());

        employeeVo.setId(employee.getId());
        employeeVo.setPhoneNos(phoneVos);

        return employeeVo;
    }

    public EmployeeVo getTaxDetails(Long empId) {
        if (Objects.isNull(empId))
            throw new AppException("Invalid emp id");
        Employee employee = empRepo.getById(empId);
        if (Objects.isNull(employee)) throw new AppException("Employee not found");

        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmpId(employee.getEmpId());
        employeeVo.setDoj(employee.getDoj());
        employeeVo.setSalary(employee.getSalary());
        employeeVo.setFirstName(employee.getFirstName());
        employeeVo.setLastName(employee.getLastName());

        calEmpTax(employeeVo);
        return employeeVo;
    }

    public void calEmpTax(EmployeeVo employeeVo) {
        Date doj = employeeVo.getDoj();
        Date yearStartDt = getDateFromYear(Year.now().getValue(), 3, 1);
        Date yearEndDt = getDateFromYear(Year.now().getValue() + 1, 2, 31);

        double totalSal;
        double taxDeduct = 0;
        double cessAmnt = 0;
        if (doj.getTime() <= yearStartDt.getTime()) {
            totalSal = employeeVo.getSalary().doubleValue() * 12;
        } else {
            long daysDiff = DAYS.between(Instant.ofEpochMilli(doj.getTime()), Instant.ofEpochMilli(yearEndDt.getTime()));
            totalSal = daysDiff * (employeeVo.getSalary() / 30);
        }

        if (totalSal <= 250000) {
            taxDeduct = 0;
        } else if (totalSal <= 500000) {
            taxDeduct = (0.05 * (totalSal - 250000));
        } else if (totalSal <= 1000000) {
            taxDeduct = (0.1 * (totalSal - 500000)) + 0.05 * 250000;
        } else {
            taxDeduct = (0.2 * (totalSal - 1000000)) + (0.1 * 500000) + (0.05 * 250000);
        }

        if (totalSal > 2500000) {
            cessAmnt = (totalSal - 2500000) * 0.02;
            employeeVo.setCessAmt(cessAmnt);
        }
        employeeVo.setCessAmt(cessAmnt);
        employeeVo.setYearlySal(totalSal);
        employeeVo.setTaxAmt(taxDeduct);
    }

    private Date getDateFromYear(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    private void validateEmp(EmployeeVo employeeVo) {
        Map<String, String> errMap = new HashMap<>();

        validateStr("Emp Id", employeeVo.getEmpId(), "Empty/Invalid", errMap);
        validateStr("Emp First Name", employeeVo.getFirstName(), "Empty/Invalid", errMap);
        validateStr("Emp Last Name", employeeVo.getLastName(), "Empty/Invalid", errMap);
        validateStr("Emp Id", employeeVo.getEmpId(), "Empty/Invalid", errMap);
        validateStr("Emp Email", employeeVo.getEmail(), "Empty/Invalid", errMap);

        validateDoj("Emp DOJ", employeeVo.getDoj(), "Empty/Invalid", errMap);
        validateSal("Emp Salary", employeeVo.getSalary(), "Empty/Invalid", errMap);
        validatePhone("Emp Phone", employeeVo.getPhoneNoList(), "Empty/Invalie", errMap);

        if (!CollectionUtils.isEmpty(errMap)) {
            throw new AppException(errMap);
        }

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


}
