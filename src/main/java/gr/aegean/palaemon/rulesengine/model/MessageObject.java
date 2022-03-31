package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

@ToString
public class MessageObject {
    private String type;
    private String sender;
    private String audience;
    private String layout;
    private String deliveryChannel;
    private String messageCode;


    public MessageObject(String type) {
        this.type = type;
    }

    public MessageObject() {
    }

    public String getType() {
        return type;
    }

    public String getSender() {
        return sender;
    }

    public String getAudience() {
        return audience;
    }

    public String getLayout() {
        return layout;
    }

    public String getDeliveryChannel() {
        return deliveryChannel;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setDeliveryChannel(String deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
