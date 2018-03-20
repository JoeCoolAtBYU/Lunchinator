package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.BallotById;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class LunchinatorControllerTest {

    @InjectMocks
    private LunchinatorController controller;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private BallotById testBallotById;
    UUID ballotId = UUID.randomUUID();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.controller).build();

        objectMapper = new ObjectMapper();

        testBallotById = new BallotById();
        testBallotById.setEndTime("11/12/12 11:45");
        testBallotById.setVoters(new ArrayList());


    }

    @Test
    public void createBallot() throws Exception {
        MvcResult test = this.mockMvc.perform(post("/api/create-ballot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(testBallotById)))
                .andExpect(status().isOk())
                .andReturn();

    assertTrue(test.getResponse().getContentAsString().contains("\"ballotId\":"));
    assertFalse("return should only include ballotId not endTime", test.getResponse().getContentAsString().contains("\"endTime\":"));
    assertFalse("return should only include ballotId not Voters.", test.getResponse().getContentAsString().contains("\"voters\":"));
    }
}