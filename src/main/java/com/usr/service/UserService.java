package com.usr.service;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import com.usr.entity.User;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.usr.dao.EmpRepo;
import com.usr.dao.PhoneRepo;
import com.usr.entity.Phone;
import com.usr.modal.UserVo;
import com.usr.modal.PhoneVo;
import com.usr.util.UsrException;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

@Slf4j
@Service
public class UserService {
	@Autowired
	PhoneRepo phoneRepo;
	@Autowired
	EmpRepo empRepo;

	@Transactional
	public UserVo getUser(Long empId) {

		if (Objects.isNull(empId)) {
			throw new UsrException("Employee id empty");
		}
		Optional<User> employeeOptional = empRepo.findById(empId);
		if (!employeeOptional.isPresent()) {
			throw new UsrException("Employee not found");
		}
		User user = employeeOptional.get();
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user, userVo);
		if (!CollectionUtils.isEmpty(user.getPhones())) {
			List<PhoneVo> phoneVos = user.getPhones().stream().map(item -> {
				PhoneVo phoneVo = new PhoneVo();
				BeanUtils.copyProperties(item, phoneVo);
				return phoneVo;
			}).collect(Collectors.toList());
			userVo.setPhones(phoneVos);
		}
		return userVo;
	}

	@Transactional
	public List<UserVo> getUserList() {
		List<User> users = empRepo.findAll();
		List<UserVo> userVos = users.stream().map(employee -> {
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(employee, userVo);
			if (!CollectionUtils.isEmpty(employee.getPhones())) {
				List<PhoneVo> phoneVos = employee.getPhones().stream().map(phone -> {
					PhoneVo phoneVo = new PhoneVo();
					BeanUtils.copyProperties(phone, phoneVo);
					return phoneVo;
				}).collect(Collectors.toList());
				userVo.setPhones(phoneVos);
			}
			return userVo;
		}).collect(Collectors.toList());
		return userVos;
	}

	@Transactional
	public UserVo saveUser(UserVo userVo) {

		User user = new User();
		BeanUtils.copyProperties(userVo, user);
		user.setUserId(generateUserId());
		user.setCreatedBy("system");
		user.setUpdatedBy("system");
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		empRepo.save(user);

		log.info("saving employee phone nos");
		List<Phone> phones = userVo.getPhones().stream().map(item -> new Phone(item, user))
				.collect(Collectors.toList());
		phoneRepo.saveAll(phones);

		List<PhoneVo> phoneVos = phones.stream()
				.map(item -> new PhoneVo(item.getId(), item.getType(), item.getPhoneNo())).collect(Collectors.toList());

		userVo.setId(user.getId());
		userVo.setPhones(phoneVos);

		return userVo;
	}

	@Transactional
	public UserVo updateUser(UserVo userVo) {
		if (Objects.isNull(userVo.getId())) {
			throw new UsrException("Employee id empty");
		}
		Optional<User> empOptional = empRepo.findById(userVo.getId());
		if (!empOptional.isPresent()) {
			throw new UsrException("Employee not found");
		}
		User user = empOptional.get();
		user.setEmail(userVo.getEmail());
		user.setFirstName(userVo.getFirstName());
		user.setLastName(userVo.getLastName());
		empRepo.save(user);
		return userVo;
	}

	@Transactional
	public void deleteUser(Long empId) {
		if (Objects.isNull(empId))
			throw new UsrException("Employee id not found");
		User user = empRepo.getById(empId);
		if (Objects.isNull(user))
			throw new UsrException("Employee not found");

		phoneRepo.deleteByUser(user);
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

	public static void main(String[] args) throws Exception {

		String data = "www.geeksforgeeks.org";
		// The path where the image will get saved
		String path = "C:\\Users\\patna\\Documents\\workspace\\intellij\\demo.jpg";
		createQR(data, path, 200, 200);
		System.out.println("QR Code Generated!!! ");

		// String output = readQR(path);
		// System.out.println(output);

		String base64Str = "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";
		String outputPath = "C:\\Users\\patna\\Documents\\workspace\\intellij\\output.jpg";

		byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Str);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
		// write the image to a file
		File outputfile = new File(outputPath);
		ImageIO.write(image, "png", outputfile);
		System.out.println("asdfadsf");

	}

}
