package com.example.StockTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.stock.DTO.AdminDTOLogin;
import com.app.stock.controller.AdminController;
import com.app.stock.entities.AdminEntity;
import com.app.stock.exception.AdminNotFound;
import com.app.stock.exception.MobileNumberException;
import com.app.stock.exception.PasswordMissmatchException;
import com.app.stock.service.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminTestCases {
	

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

	@Test
    public void testLoginAdmin() throws AdminNotFound, PasswordMissmatchException, MobileNumberException {
        AdminDTOLogin adminDTOLogin = new AdminDTOLogin("abcd@gmail.com","Abcd@123");
        when(adminService.loginAdmin(adminDTOLogin)).thenReturn(new AdminEntity());

        ResponseEntity<Object> responseEntity = adminController.loginAdmin(adminDTOLogin);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(adminService, times(1)).loginAdmin(adminDTOLogin);
    }

    @Test
    public void testGetAdmin() throws AdminNotFound {
        long adminId = 1L;
        when(adminService.getAdmin(adminId)).thenReturn(new AdminEntity());

        ResponseEntity<Object> responseEntity = adminController.getAdmin(adminId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(adminService, times(1)).getAdmin(adminId);
    }

    @Test
    public void testGetAllAdmin() {
        List<AdminEntity> adminList = Arrays.asList(new AdminEntity(), new AdminEntity());
        when(adminService.getAll()).thenReturn(adminList);

        ResponseEntity<Object> responseEntity = adminController.getAllAdmin();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(adminService, times(1)).getAll();
    }

    @Test
    public void testDeleteAdmin() throws AdminNotFound {
        long adminId = 1L; 
        ResponseEntity<Object> responseEntity = adminController.deleteAdmin(adminId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Admin Delete Successfully", responseEntity.getBody());
        verify(adminService, times(1)).deleteAdmin(adminId);
    } 

}
