package es.bit.BananaTaskManager.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {

    private String message;
}
