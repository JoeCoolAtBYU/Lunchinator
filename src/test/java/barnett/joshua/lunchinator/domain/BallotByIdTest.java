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
    public void setUp() {
        this.ballotById = new BallotById();

        this.voters = new ArrayList<>();
        this.voters.add(new Voter("Bruce Wayne", "IRock@bat.org"));
        this.voters.add(new Voter("The Joker", "hahahahahaha@insanity.org"));
    }

    @Test
    public void testGetEndTime() {
        String endTime = "3/22/12 11:45";
        this.ballotById.setEndTime(endTime);
        assertEquals(endTime, this.ballotById.getEndTime());
    }

    @Test
    public void testGetBallotId() {
        UUID ballotId = UUID.randomUUID();
        this.ballotById.setBallotId(ballotId);
        assertEquals(ballotId, this.ballotById.getBallotId());
    }

    @Test
    public void testGetVoters(){
        List<Voter> voters = new ArrayList<>();
        voters.add(new Voter("Bruce Wayne", "IRock@bat.org"));
        voters.add(new Voter("The Joker", "hahahahahaha@insanity.org"));
        this.ballotById.setVoters(voters);
        assertEquals(voters, this.ballotById.getVoters());
    }

    @Test
    public void testConstructor() {
        BallotById ballotById2 = new BallotById(this.ballotById);

        assertNotNull(ballotById2.getBallotId());
        assertEquals("11:45", new SimpleDateFormat("HH:mm").format(ballotById2.getEndTime()));

    }

    @Test
    public void testConstuctorDateSet(){
        String endTime = "11/12/12 11:45";
        this.ballotById.setEndTime(endTime);
        BallotById ballotById2 = new BallotById(this.ballotById);

        assertEquals(endTime, ballotById2.getEndTime());
        assertNotNull(ballotById2.getBallotId());
        assertEquals(0, ballotById2.getVoters().size());
    }

    @Test
    public void testConstuctorDateSetVotersSet(){
        String endTime = "11/12/12 11:45";
        this.ballotById.setEndTime(endTime);
        this.ballotById.setVoters(this.voters);

        BallotById ballotById2 = new BallotById(this.ballotById);

        assertEquals(endTime, ballotById2.getEndTime());
        assertNotNull(ballotById2.getBallotId());
        assertEquals(2, ballotById2.getVoters().size());
        assertEquals(this.voters, ballotById2.getVoters());
    }
}