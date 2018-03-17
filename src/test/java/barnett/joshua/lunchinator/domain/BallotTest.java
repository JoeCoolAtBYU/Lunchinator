package barnett.joshua.lunchinator.domain;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BallotTest {

    private Ballot ballot;
    private List<Voter> voters;

    @Before
    public void setUp() throws Exception {
        ballot = new Ballot();

        voters = new ArrayList<>();
        voters.add(new Voter("Bruce Wayne", "IRock@bat.org"));
        voters.add(new Voter("The Joker", "hahahahahaha@insanity.org"));
    }

    @Test
    public void testGetEndTime() {
        Date endTime = new Date();
        ballot.setEndTime(endTime);
        assertEquals(endTime, ballot.getEndTime());
    }

    @Test
    public void testGetBallotId() {
        UUID ballotId = UUID.randomUUID();
        ballot.setBallotId(ballotId);
        assertEquals(ballotId, ballot.getBallotId());
    }

    @Test
    public void testGetVoters(){
        List<Voter> voters = new ArrayList<>();
        voters.add(new Voter("Bruce Wayne", "IRock@bat.org"));
        voters.add(new Voter("The Joker", "hahahahahaha@insanity.org"));
        ballot.setVoters(voters);
        assertEquals(voters, ballot.getVoters());
    }

    @Test
    public void testConstructor() {
        Ballot ballot2 = new Ballot(ballot);

        assertNotNull(ballot2.getBallotId());
        assertEquals("11:45", new SimpleDateFormat("HH:mm").format(ballot2.getEndTime()));

    }

    @Test
    public void testConstuctorDateSet(){
        Date endTime = new Date();
        ballot.setEndTime(endTime);
        Ballot ballot2 = new Ballot(ballot);

        assertEquals(endTime, ballot2.getEndTime());
        assertNotNull(ballot2.getBallotId());
        assertEquals(0, ballot2.getVoters().size());
    }

    @Test
    public void testConstuctorDateSetVotersSet(){
        Date endTime = new Date();
        ballot.setEndTime(endTime);
        ballot.setVoters(voters);

        Ballot ballot2 = new Ballot(ballot);

        assertEquals(endTime, ballot2.getEndTime());
        assertNotNull(ballot2.getBallotId());
        assertEquals(2, ballot2.getVoters().size());
        assertEquals(voters, ballot2.getVoters());
    }
}