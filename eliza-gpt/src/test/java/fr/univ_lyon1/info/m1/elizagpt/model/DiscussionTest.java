package fr.univ_lyon1.info.m1.elizagpt.model;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class DiscussionTest {

    Message message = new Message("", true);


    @Test
    public void testAddedMessageIsInDiscussion() {
        Discussion discussion = new Discussion();
        discussion.addMessage(message);
        assertThat(discussion.getFullDiscussion(), contains(message));
    }

    @Test
    public void testRemovedMessageIsNotInDiscussion() {
        Discussion discussion = new Discussion();
        discussion.addMessage(message);
        discussion.removeMessage(message.id);
        assertThat(discussion.getFullDiscussion(), not(contains(message)));
    }

    @Test
    public void testContainsWelcomeMessageOnStart() {
        Discussion discussion = new Discussion();
        assertThat(discussion.getFullDiscussion(), hasSize(1));
    }

    @Test
    public void testUserNameIsUnknownWhenNotGiven() {
        Discussion discussion = new Discussion();
        discussion.addMessage(message);
        assertThat(discussion.getUserName(), is(nullValue()));
    }

    @Test
    public void testUserNameIsKnownWhenGiven() {
        Discussion discussion = new Discussion();
        discussion.addMessage(message);
        String name = "Toto";
        discussion.addMessage(new Message("Je m'appelle " + name + ".", true));
        assertThat(discussion.getUserName(), is(notNullValue()));
        assertThat(discussion.getUserName(), is(name));
    }

    @Test
    public void testElizaNameIsNotUserName() {
        Discussion discussion = new Discussion();
        discussion.addMessage(new Message("Je m'appelle Eliza.", false));
        assertThat(discussion.getUserName(), is(nullValue()));
    }

}