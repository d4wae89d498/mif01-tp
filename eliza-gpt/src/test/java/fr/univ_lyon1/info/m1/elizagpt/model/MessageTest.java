package fr.univ_lyon1.info.m1.elizagpt.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for <code>Message.java</code>
 */
class MessageTest {

    @Test
    public void testMessageIsCreatedWithContent() {
        String content = "contenu du message";
        Message message = new Message(content, true);
        assertThat(message.content, is(content));
    }


    @Test
    public void testMessageIsFromUser() {
        Message message = new Message("", true);
        assertThat(message.isFromUser, is(true));
    }

    @Test
    public void testMessageIsFromEliza() {
        Message message = new Message("", false);
        assertThat(message.isFromUser, is(false));
    }

    @Test
    public void testTwoUserMessagesHaveDifferentIds() {
        Message m1 = new Message("", true);
        Message m2 = new Message("", true);
        assertThat(m1.id, not(m2.id));
    }

    @Test
    public void testTwoElizaMessagesHaveDifferentIds() {
        Message m1 = new Message("", false);
        Message m2 = new Message("", false);
        assertThat(m1.id, not(m2.id));
    }

    @Test
    public void testUserMessageAndElizaMessageHaveDifferentIds() {
        Message user = new Message("", true);
        Message eliza = new Message("", false);
        assertThat(user.id, not(eliza.id));
    }
}
