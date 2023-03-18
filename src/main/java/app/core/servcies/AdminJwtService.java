package app.core.servcies;

import app.core.auth.AdminJwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminJwtService {
    @Autowired
    AdminJwtUtil adminJwtUtil;

    public String login(UserCredentials userCredentials) throws Exception {
        Admin admin = Admin.builder().email("admin@admin.com").password("admin").build();
        if (userCredentials.getEmail().equals(admin.getEmail()) && userCredentials.getPassword().equals(admin.getPassword())) {
            return adminJwtUtil.generateToken(admin);
        } else {
            throw new Exception("Wrong email or password ");
        }
    }
}
