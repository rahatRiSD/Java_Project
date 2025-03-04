package com.employee.leave_request.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing the approval workflows for leave requests.
 * This defines which roles are required to approve specific types of leave requests.
 *
 * @author Leave Management System
 */
@Entity
@Table(name = "leave_approval_workflows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveApprovalWorkflow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "leave_approval_roles", 
                     joinColumns = @JoinColumn(name = "workflow_id"))
    @Column(name = "role_name")
    private List<String> approverRoles;

    @Column(name = "min_days_trigger")
    private Integer minDaysTrigger;
    
    @Column(name = "sequential_approval", nullable = false)
    private Boolean sequentialApproval = false;

    @Column(name = "is_active", nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}