package medrecords.dto;

public record ErrorResponse(
        int status, String errorMessage,
        java.util.List<String> violations) {

    public ErrorResponse(
            int status, String errorMessage){
        this(status,errorMessage, null);
    }
}
