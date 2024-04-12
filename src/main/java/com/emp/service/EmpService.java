package com.emp.service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import com.emp.entity.BaseEntity;
import com.emp.entity.Employee;
import com.emp.modal.EmpFilterVo;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.emp.modal.EmpVo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.emp.dao.EmpRepo;
import com.emp.util.EmpException;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.text.html.Option;

@Slf4j
@Service
public class EmpService {
    @Autowired
    EmpRepo empRepo;

    @Transactional
    public EmpVo getEmp(Long empId) {
        if (Objects.isNull(empId)) {
            throw new EmpException("Employee id empty");
        }
        Optional<Employee> employeeOptional = empRepo.findById(empId);
        if (!employeeOptional.isPresent()) {
            throw new EmpException("Employee not found");
        }
        Employee user = employeeOptional.get();
        EmpVo empVo = new EmpVo();
        BeanUtils.copyProperties(user, empVo);

        return empVo;
    }

    @Transactional
    public List<EmpVo> getEmpList(EmpFilterVo empFilter) {
        List<Employee> users = empRepo.findAll();
        List<EmpVo> empVos = users.stream().map(usr -> {
            EmpVo empVo = new EmpVo();
            BeanUtils.copyProperties(usr, empVo);
            return empVo;
        }).collect(Collectors.toList());
        return empVos;
    }

    @Transactional
    public EmpVo saveEmp(EmpVo empVo) {

        Employee emp = empRepo.findFirstByUserId(empVo.getUserId());
        if (Objects.nonNull(emp)) {
            throw new EmpException("Employee already exist in system");
        }
        emp = Employee.builder().build();
        BeanUtils.copyProperties(empVo, emp);
        create(emp);
        empRepo.save(emp);

        return empVo;
    }

    @Transactional
    public EmpVo updateEmp(EmpVo empVo) {
        if (Objects.isNull(empVo.getId())) {
            throw new EmpException("Employee id empty");
        }
        Optional<Employee> empOptional = empRepo.findById(empVo.getId());
        if (!empOptional.isPresent()) {
            throw new EmpException("Employee not found");
        }
        Employee emp = empOptional.get();
        if (StringUtils.hasText(empVo.getFirstName()))
            emp.setFirstName(emp.getFirstName());
        if (StringUtils.hasText(empVo.getLastName()))
            emp.setLastName(empVo.getLastName());
        if (StringUtils.hasText(empVo.getEmail()))
            emp.setEmail(empVo.getEmail());
        if (StringUtils.hasText(empVo.getMobile()))
            emp.setMobile(empVo.getMobile());
        update(emp);
        empRepo.save(emp);

        return empVo;
    }

    @Transactional
    public void deleteEmp(Long empId) {
        if (Objects.isNull(empId))
            throw new EmpException("Employee id not found");
        Optional<Employee> empOptional = empRepo.findById(empId);
        if (empOptional.isPresent())
            empRepo.delete(empOptional.get());
        else
            throw new EmpException("Employee already deleted");

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

    private String generateUserId() {
        return RandomStringUtils.randomAlphanumeric(10).toUpperCase();
    }

    public static void createQR(String data, String path, int height, int width) throws WriterException, IOException {

        String charset = "UTF-8";
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));

        File file = new File(path);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String base64Str = Base64.getEncoder().encodeToString(fileContent);

        System.out.println(base64Str);

        FileUtils.forceDelete(file);

    }

    public static String readQR(String path) throws Exception {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }

    private void create(BaseEntity baseEntity) {
        String system = "system";
        Date currDate = new Date();
        baseEntity.setCreatedBy(system);
        baseEntity.setUpdatedBy(system);
        baseEntity.setCreatedDate(currDate);
        baseEntity.setUpdatedDate(currDate);
    }

    private void update(BaseEntity baseEntity) {
        String system = "system";
        Date currDate = new Date();
        baseEntity.setUpdatedBy(system);
        baseEntity.setUpdatedDate(currDate);
    }

}
