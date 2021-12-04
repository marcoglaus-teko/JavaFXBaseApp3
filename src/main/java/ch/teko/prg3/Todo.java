package ch.teko.prg3;

public class Todo {
    private String description;
    private String until;

    public Todo(String description, String until) {
        this.description = description;
        this.until = until;
    }

    public Todo() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }
}
