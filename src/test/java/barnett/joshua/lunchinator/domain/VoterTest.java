package barnett.joshua.lunchinator.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VoterTest {
    
    Voter voter;

    @Before
    public void setUp() throws Exception {
        voter = new Voter();
    }

    @Test
    public void testGetName() {
        String name = "Joshua";
        voter.setName(name);
        assertEquals(name, voter.getName());
        
    }

    @Test
    public void testGetEmailAddress() {
        String emailAddress = "BatmanRulesAll@batcave.wayne";
        voter.setEmailAddress(emailAddress);
        assertEquals(emailAddress, voter.getEmailAddress());
    }

    @Test
    public void testConstuctor(){
        Voter v = new Voter();
        assertNull(v.getEmailAddress());
        assertNull(v.getName());

    }

    @Test
    public void testConstuctor2(){
        String name = "name";
        String email = "email";
        Voter v = new Voter(name, email);
        assertEquals(email, v.getEmailAddress());
        assertEquals(name, v.getName());

    }

    @Test
    public void testConstuctor3(){
        String name = "name";
        String email = "email";
        voter.setEmailAddress(email);
        voter.setName(name);

        Voter v = new Voter(voter);
        assertEquals(email, v.getEmailAddress());
        assertEquals(name, v.getName());

    }
}