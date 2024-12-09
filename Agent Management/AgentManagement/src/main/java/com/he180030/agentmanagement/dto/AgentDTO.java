package com.he180030.agentmanagement.dto;

import com.he180030.agentmanagement.model.Status;

import java.util.Date;

public class AgentDTO {
    private long id;
    private String email;
    private String name;
    private String address;
    private Status status;
    private Date enrolledDate;
    private float accountBalance;
}
