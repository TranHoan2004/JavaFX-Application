package com.he180030.agentmanagement.dto;

import com.he180030.agentmanagement.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private long id;
    private String email;
    private String name;
    private String address;
    private Status status;
    private Date enrolledDate;
    private float accountBalance;
}
