package com.app.stock.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.app.stock.DTO.AdminDTO;
import com.app.stock.DTO.AdminDTOLogin;
import com.app.stock.entities.AdminEntity;
import com.app.stock.exception.AdminNotFound;
import com.app.stock.exception.CustomException;
import com.app.stock.repositories.AdminRepository;
import com.app.stock.exception.MobileNumberException;
import com.app.stock.exception.PasswordMissmatchException;

import jakarta.validation.Valid;

@Service
@Validated
public class AdminService {
	@Autowired
	private AdminRepository adminRepo;

	public static Logger logger = LoggerFactory.getLogger(AdminService.class);

	/**
	 * Create a new AdminEntity based on the provided AdminDTO.
	 * 
	 * @throws CustomException
	 * @throws MobileNumberException
	 */
	public AdminEntity createAdmin(@Valid AdminDTO admin) throws CustomException, MobileNumberException {
		try {
			logger.info("Creating a new admin with email: {}", admin.getAdminEmail());

			if (adminRepo.existsByAdminEmail(admin.getAdminEmail())) {

				throw new CustomException("Admin is already exists with this email");
			} else if (adminRepo.existsByAdminMobileno(admin.getAdminMobileno())) {

				throw new MobileNumberException("Admin is already exists with this Mobileno");
			}

			AdminEntity a1 = new AdminEntity();
			a1.setAdminName(admin.getAdminName());
			a1.setAdminPassword(admin.getAdminPassword());
			a1.setAdminEmail(admin.getAdminEmail().toLowerCase());
			a1.setAdminMobileno(admin.getAdminMobileno());

			logger.info("Admin created successfully with email: {}", admin.getAdminEmail());
			return adminRepo.save(a1);
		} catch (CustomException | MobileNumberException e) {
			logger.warn("Failed to create admin. Reason: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Delete an admin based on the provided admin id.
	 * 
	 * @throws AdminNotFound
	 */
	public void deleteAdmin(long id) throws AdminNotFound {

		logger.info("Deleting admin with id: {}", id);
		Optional<AdminEntity> op = adminRepo.findById(id);
		if (op.isPresent()) {
			logger.info("Admin deleted successfully with id: {}", id);
			adminRepo.deleteById(id);
		} else {
			logger.warn("Failed to delete admin. Reason: {Admin Not Found}");
			throw new AdminNotFound("Admin Not Found");
		}
	}

	/**
	 * Login an admin based on the provided AdminDTOLogin.
	 * 
	 * @throws AdminNotFound
	 * @throws PasswordMissmatchException
	 */
	public AdminEntity loginAdmin(@Valid AdminDTOLogin admin) throws AdminNotFound, PasswordMissmatchException {
		try {
			logger.info("Logging in admin with email: {}", admin.getAdminEmail());
			AdminEntity adminEnt = adminRepo.findByAdminEmail(admin.getAdminEmail())
					.orElseThrow(() -> new AdminNotFound("Admin not found"));
			if (!adminEnt.getAdminPassword().equals(admin.getAdminPassword())) {
				throw new PasswordMissmatchException("You have entered Invalid password");
			}

			logger.info("Admin logged in successfully with email: {}", admin.getAdminEmail());
			return adminEnt;
		} catch (AdminNotFound | PasswordMissmatchException e) {
			logger.warn("Failed to log in admin. Reason: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Update an existing admin based on the provided id and AdminDTO.
	 * 
	 * @throws AdminNotFound
	 */

	public AdminEntity updateAdmin(Long id, AdminDTO admin) throws AdminNotFound {
		try {
			logger.info("Updating admin with id: {}", id);
			AdminEntity existingAdmin = adminRepo.findById(id).orElseThrow(() -> new AdminNotFound("Admin not found"));

			existingAdmin.setAdminName(admin.getAdminName());
			existingAdmin.setAdminEmail(admin.getAdminEmail());
			existingAdmin.setAdminPassword(admin.getAdminPassword());
			existingAdmin.setAdminMobileno(admin.getAdminMobileno());

			logger.info("Admin updated successfully with id: {}", id);
			return adminRepo.save(existingAdmin);
		} catch (AdminNotFound e) {
			logger.warn("Failed to update admin. Reason: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Get a Admin by Id.
	 * 
	 * @throws AdminNotFound
	 */
	public AdminEntity getAdmin(Long id) throws AdminNotFound {
		try {
			logger.info("Fetching admin with id: {}", id);
			AdminEntity admin = adminRepo.findById(id).orElseThrow(() -> new AdminNotFound("Admin not found"));
			logger.info("Admin fetched successfully with id: {}", id);
			return admin;
		} catch (AdminNotFound e) {
			logger.warn("Failed to fetch admin. Reason: {}", e.getMessage());
			throw e;
		}
	}

	/**
	 * Get a list of all admins.
	 */
	public List<AdminEntity> getAll() {
		try {
			logger.info("Fetching all admins");
			List<AdminEntity> allAdmin = adminRepo.findAll();
			logger.info("Fetched all admins successfully");
			return allAdmin;
		} catch (Exception e) {
			logger.warn("Failed to fetch all admins. Reason: {}", e.getMessage());
			throw e;
		}

	}

}