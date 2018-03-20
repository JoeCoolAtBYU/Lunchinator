package barnett.joshua.lunchinator.domain;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BallotByIdTest {

    private BallotById ballotById;
    private List<Voter> voters;

    @Before
    public void setUp() throws Exception {
        ballotById = new BallotById();

        voters = new ArrayList<>();
        voters.add(new Voter("Bruce Wayne", "IRock@bat.org"));
        voters.add(new Voter("The Joker", "hahahahahaha@insanity.org"));
    }

    @Test
    public void testGetEndTime() {
        String endTime = "3/22/12 11:45";
        ballotById.setEndTime(endTime);
        assertEquals(endTime, ballotById.getEndTime());
    }

    @Test
    public void testGetBallotId() {
        UUID ballotId = UUID.randomUUID();
        ballotById.setBallotId(ballotId);
        assertEquals(ballotId, ballotById.getBallotId());
    }

    @Test
    public void testGetVoters(){
        List<Voter> voters = new ArrayList<>();
        voters.add(new Voter("Bruce Wayne", "IRock@bat.org"));
        voters.add(new Voter("The Joker", "hahahahahaha@insanity.org"));
        ballotById.setVoters(voters);
        assertEquals(voters, ballotById.getVoters());
    }

    @Test
    public void testConstructor() {
        BallotById ballotById2 = new BallotById(ballotById);

        assertNotNull(ballotById2.getBallotId());
        assertEquals("11:45", new SimpleDateFormat("HH:mm").format(ballotById2.getEndTime()));

    }

    @Test
    public void testConstuctorDateSet(){
        String endTime = "11/12/12 11:45";
        ballotById.setEndTime(endTime);
        BallotById ballotById2 = new BallotById(ballotById);

        assertEquals(endTime, ballotById2.getEndTime());
        assertNotNull(ballotById2.getBallotId());
        assertEquals(0, ballotById2.getVoters().size());
    }

    @Test
    public void testConstuctorDateSetVotersSet(){
        String endTime = "11/12/12 11:45";
        ballotById.setEndTime(endTime);
        ballotById.setVoters(voters);

        BallotById ballotById2 = new BallotById(ballotById);

        assertEquals(endTime, ballotById2.getEndTime());
        assertNotNull(ballotById2.getBallotId());
        assertEquals(2, ballotById2.getVoters().size());
        assertEquals(voters, ballotById2.getVoters());
    }
}