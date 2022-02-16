package taskManagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    private long id;
    private String name;
    private String description;
    private User user;
    private TaskStatus status;
    private Date deadline;
}
