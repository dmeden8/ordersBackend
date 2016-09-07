package hr.ddcode.cafford;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import hr.ddcode.cafford.data.repository.userAccount.UserAccountRepository;
import hr.ddcode.cafford.service.UserAccountService;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(CaffeOrdersApplication.class)
public class UserAccountServiceTest {
	
	@Mock
    private UserAccountRepository userAccountMock;
	
    @InjectMocks
    private UserAccountService userServiceMock;
	
	private MockMvc mockMvc;
		
    @Before
    public void setup() 
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userServiceMock).build();
    }

    /*
	@Test
    public void testUsersAll() throws Exception {
								
		UserAccount user1 = new UserAccount("dean", "dean");
		UserAccount user2 = new UserAccount("david", "david");
		
		when(userAccountMock.findAll()).thenReturn(Arrays.asList(user1, user2));
				
        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("dean")));
        
        verify(userAccountMock, times(1)).findAll();
        verifyNoMoreInteractions(userAccountMock);

    }
    */
	
	
    

}
