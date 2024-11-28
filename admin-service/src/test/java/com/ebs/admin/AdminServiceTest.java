package com.ebs.admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ebs.admin.dto.AdminDto;
import com.ebs.admin.entity.Admin;
import com.ebs.admin.exceptions.AdminNotRegisterException;
import com.ebs.admin.exceptions.InvalidEmailPasswordException;
import com.ebs.admin.repository.AdminRepository;
import com.ebs.admin.serviceimpl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterAdmin() {
        Admin admin = new Admin();
        admin.setEmailId("test@example.com");
        admin.setPassword("password");

        when(adminRepository.save(admin)).thenReturn(admin);

        String result = adminService.registerAdmin(admin);
        assertEquals("Admin Registered Successfully", result);

        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    void testAdminLogin_Success() {
        Admin admin = new Admin();
        admin.setEmailId("test@example.com");
        admin.setPassword("password");

        when(adminRepository.findByEmailId("test@example.com")).thenReturn(admin);

        String result = adminService.adminLogin("test@example.com", "password");
        assertEquals("Login Successfully", result);
    }

    @Test
    void testAdminLogin_AdminNotRegisterException() {
        when(adminRepository.findByEmailId("test@example.com")).thenReturn(null);

        Exception exception = assertThrows(AdminNotRegisterException.class, () -> {
            adminService.adminLogin("test@example.com", "password");
        });

        assertEquals("Admin id is not register", exception.getMessage());
    }

    @Test
    void testAdminLogin_InvalidEmailPasswordException() {
        Admin admin = new Admin();
        admin.setEmailId("test@example.com");
        admin.setPassword("password");

        when(adminRepository.findByEmailId("test@example.com")).thenReturn(admin);

        Exception exception = assertThrows(InvalidEmailPasswordException.class, () -> {
            adminService.adminLogin("test@example.com", "wrongpassword");
        });

        assertEquals("Invalid_password", exception.getMessage());
    }

    @Test
    void testForgotPassword_Success() {
        Admin admin = new Admin();
        admin.setEmailId("test@example.com");
        admin.setPassword("oldpassword");

        AdminDto adminDto = new AdminDto();
        adminDto.setEmailId("test@example.com");
        adminDto.setPassword("newpassword123");
        adminDto.setConfirmpassword("newpassword123");

        when(adminRepository.findByEmailId("test@example.com")).thenReturn(admin);
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        String result = adminService.forgotPassword(adminDto);
        assertEquals("Password Reset Succesfully", result);

        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    void testForgotPassword_AdminNotRegisterException() {
        AdminDto adminDto = new AdminDto();
        adminDto.setEmailId("test@example.com");

        when(adminRepository.findByEmailId("test@example.com")).thenReturn(null);

        Exception exception = assertThrows(AdminNotRegisterException.class, () -> {
            adminService.forgotPassword(adminDto);
        });

        assertEquals("Admin id is not register", exception.getMessage());
    }

    @Test
    void testForgotPassword_InvalidPasswordException_InvalidPasswordPattern() {
        Admin admin = new Admin();
        admin.setEmailId("test@example.com");

        AdminDto adminDto = new AdminDto();
        adminDto.setEmailId("test@example.com");
        adminDto.setPassword("short");
        adminDto.setConfirmpassword("short");

        when(adminRepository.findByEmailId("test@example.com")).thenReturn(admin);

        Exception exception = assertThrows(InvalidEmailPasswordException.class, () -> {
            adminService.forgotPassword(adminDto);
        });

        assertEquals("Invalid_password", exception.getMessage());
    }

    @Test
    void testForgotPassword_InvalidPasswordException_PasswordMismatch() {
        Admin admin = new Admin();
        admin.setEmailId("test@example.com");

        AdminDto adminDto = new AdminDto();
        adminDto.setEmailId("test@example.com");
        adminDto.setPassword("newpassword123");
        adminDto.setConfirmpassword("differentpassword");

        when(adminRepository.findByEmailId("test@example.com")).thenReturn(admin);

        Exception exception = assertThrows(InvalidEmailPasswordException.class, () -> {
            adminService.forgotPassword(adminDto);
        });

        assertEquals("Invalid_Password", exception.getMessage());
    }
}