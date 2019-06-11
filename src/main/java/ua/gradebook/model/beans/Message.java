package ua.gradebook.model.beans;

import java.util.Objects;

public class Message extends ParentBean {
    private Integer receiverId;
    private Integer senderId;
    private String messageText;

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
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
        return Objects.equals(receiverId, message.receiverId) &&
                Objects.equals(senderId, message.senderId) &&
                Objects.equals(messageText, message.messageText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverId, senderId, messageText);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", receiverId=" + receiverId +
                ", senderId=" + senderId +
                ", messageText='" + messageText + '\'' +
                '}';
    }
}
