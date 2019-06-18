package ua.gradebook.model.beans;

import java.util.Objects;

public class Message extends ParentBean {
    private Person receiver;
    private Person sender;
    private String messageText;

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(receiver, message.receiver) &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(messageText, message.messageText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiver, sender, messageText);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", sender=" + sender +
                ", messageText='" + messageText + '\'' +
                '}';
    }
}
