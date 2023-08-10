package com.anadolusigorta.campaignmanagement.infrastructure.taskmanagement.jpa.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "as_robot")
public class AsRobotPolicyProposalEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name = "proposal_no")
    private String proposalNumber;

    @Column(name = "policy_no")
    private String policyNo;


    @ManyToOne
    @JoinColumn(name = "knime_task_id", nullable = false)
    private KnimeTaskEntity knimeTask;

    @Column(name = "is_active")
    private Boolean isActive;

}
