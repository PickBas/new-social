import com.newsocial.appuser.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AppUserApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AppUserControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AppUserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void getUserByUsernameTest() throws Exception {
        Role role = new Role(1L, "USER", new ArrayList<>());
        roleRepository.save(role);
        AppUser appUser = new AppUser(
                1L,
                "firstUser",
                "test@email.com",
                "Asdf123!",
                "First",
                "Last",
                "My job",
                "USA",
                LocalDate.now(),
                new ArrayList<>(List.of(role)));
        userRepository.save(appUser);
        role.addUser(appUser);
        roleRepository.save(role);
        String uri = "/api/v1/user/";
        mockMvc.perform(MockMvcRequestBuilders.get(uri + "firstUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
