package glfp.glfp.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {

    private String message;
    private Long from;  //member Id

    @Override
    public String toString() {
        return "MessageModel{" +
                "message='" + message + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
