package barnett.joshua.lunchinator.domain;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BallotTest {

    private Ballot ballot;

    @Before
    public void setUp() throws Exception {
        ballot = new Ballot();
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
    }
}