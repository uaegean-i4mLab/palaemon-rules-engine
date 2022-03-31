package gr.aegean.palaemon.rulesengine.model;

public class MessageBodyResponse {

    private String content;
    private String visualAid;

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

    public String getVisualAid() {
        return visualAid;
    }

    public void setVisualAid(String visualAid) {
        this.visualAid = visualAid;
    }
}
