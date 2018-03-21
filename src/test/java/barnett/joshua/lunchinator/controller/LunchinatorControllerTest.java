package barnett.joshua.lunchinator.controller;

import barnett.joshua.lunchinator.domain.BallotById;
import barnett.joshua.lunchinator.service.BallotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
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

    @Mock
    BallotService ballotService;

    private ObjectMapper objectMapper;
    private BallotById testBallotById;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.controller).build();

        this.objectMapper = new ObjectMapper();

        this.testBallotById = new BallotById();
        this.testBallotById.setEndTime("11/12/20 11:45");
        this.testBallotById.setVoters(new ArrayList());


    }

    @Test
    public void createBallot() throws Exception {

        when(ballotService.getBallot(this.testBallotById)).thenReturn(this.testBallotById);

        MvcResult test = this.mockMvc.perform(post("/api/create-ballot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(this.testBallotById)))
                .andExpect(status().isOk())
                .andReturn();

    assertTrue(test.getResponse().getContentAsString().contains("\"ballotId\":"));
    assertFalse("return should only include ballotId not endTime", test.getResponse().getContentAsString().contains("\"endTime\":"));
    assertFalse("return should only include ballotId not Voters.", test.getResponse().getContentAsString().contains("\"voters\":"));
    }
}