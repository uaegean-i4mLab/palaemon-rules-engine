package gr.aegean.palaemon.rulesengine.model;

public class MessageBodyResponse {

    private String content;

    public MessageBodyResponse(String conent) {
        this.content = conent;
    }

    public MessageBodyResponse() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
