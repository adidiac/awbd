package com.cafeteria.cafeteria;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cafeteria.cafeteria.DbModels.MenuItem;
import com.cafeteria.cafeteria.DbModels.User;
import com.cafeteria.cafeteria.controllers.MenuItemController;
import com.cafeteria.cafeteria.controllers.PromotionController;
import com.cafeteria.cafeteria.controllers.UserController;
import com.cafeteria.cafeteria.repository.MenuItemRepository;
import com.cafeteria.cafeteria.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
class CafeteriaApplicationTests {

	@Autowired
	private MenuItemController menuItemController;
	@Autowired
	private PromotionController promotionController;
	@Autowired
	private UserController userController;

	@Autowired
    private UserRepository userRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;

	private MockMvc mockMvcMenuItem;
	private MockMvc mockMvcPromotion;
	private MockMvc mockMvcUser;


	@BeforeEach
    public void setup() {
		mockMvcMenuItem = MockMvcBuilders.standaloneSetup(menuItemController).build();
		mockMvcPromotion = MockMvcBuilders.standaloneSetup(promotionController).build();
		mockMvcUser = MockMvcBuilders.standaloneSetup(userController).build();
		
	}


    @Test
    void testGetMenuItems() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/public/menu-items/all")
                .accept(MediaType.APPLICATION_JSON);
        mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateMenuItem() throws Exception {

        var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcxNjIzODk3NiwiZXhwIjoxNzE2MzI1Mzc2fQ.eW1tNpc4DOTE5EZe94kZQ3MvJyubAVrQo-iJyrro77I";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/public/menu-items")
                .header("Authorization", JwtToken)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test1\",\"description\":\"test\",\"price\":\"1\",\"category\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteMenuItem() throws Exception {
        var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcxNjIzODk3NiwiZXhwIjoxNzE2MzI1Mzc2fQ.eW1tNpc4DOTE5EZe94kZQ3MvJyubAVrQo-iJyrro77I";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/public/menu-items/1")
                .header("Authorization", JwtToken)
                .accept(MediaType.APPLICATION_JSON);

        mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateMenuItem() throws Exception {

		MenuItem menuItem = new MenuItem(
			"test",
			"test",
			1,
			"test"
		);
		menuItemRepository.save(menuItem);

        var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcxNjIzODk3NiwiZXhwIjoxNzE2MzI1Mzc2fQ.eW1tNpc4DOTE5EZe94kZQ3MvJyubAVrQo-iJyrro77I";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/public/menu-items/1")
                .header("Authorization", JwtToken)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\",\"description\":\"test\",\"price\":\"1\",\"category\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvcMenuItem.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }


	@Test
	void testGetPromotions() throws Exception {
		var JwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJhZG1pbiIsImlhdCI6MTcxNjIzODk3NiwiZXhwIjoxNzE2MzI1Mzc2fQ.eW1tNpc4DOTE5EZe94kZQ3MvJyubAVrQo-iJyrro77I";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/promotion")
				.header("Authorization", JwtToken)		
				.accept(MediaType.APPLICATION_JSON);
		mockMvcPromotion.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testRegister () throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/public/users/register")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"test\",\"password\":\"test\",\"role\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON);
		
		mockMvcUser.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

    @Test
    void testLoginSuccess() throws Exception {
        // Mock the login method to return a user model

		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setRole("CUSTOMER");
		user.setEmail("admin");
		user.setFullName("admin");
		userRepository.save(user);

        // Perform the login request
        mockMvcUser.perform(MockMvcRequestBuilders.post("/public/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization")) // Check if Authorization header exists
                .andExpect(jsonPath("$.username").value("admin")) // Check if username is correct in response body
                .andExpect(jsonPath("$.role").value("CUSTOMER")); // Check if role is correct in response body
    }

    @Test
    void testLoginFailure() throws Exception {
    
        // Perform the login request
        mockMvcUser
				.perform(MockMvcRequestBuilders.post("/public/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"invalid\",\"password\":\"invalid\"}"))
                .andExpect(status().is4xxClientError());
    }
}