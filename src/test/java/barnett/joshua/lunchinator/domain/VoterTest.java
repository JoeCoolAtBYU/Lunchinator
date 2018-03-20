package barnett.joshua.lunchinator.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VoterTest {
    
    private Voter voter;

    @Before
    public void setUp() {
        this.voter = new Voter();
    }

    @Test
    public void testGetName() {
        String name = "Joshua";
        this.voter.setName(name);
        assertEquals(name, this.voter.getName());
        
    }

    @Test
    public void testGetEmailAddress() {
        String emailAddress = "BatmanRulesAll@batcave.wayne";
        this.voter.setEmailAddress(emailAddress);
        assertEquals(emailAddress, this.voter.getEmailAddress());
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
        this.voter.setEmailAddress(email);
        this.voter.setName(name);

        Voter v = new Voter(this.voter);
        assertEquals(email, v.getEmailAddress());
        assertEquals(name, v.getName());

    }
}