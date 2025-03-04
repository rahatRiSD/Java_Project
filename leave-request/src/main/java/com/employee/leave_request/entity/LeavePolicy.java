package com.employee.leave_request.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity representing leave policies in the system.
 *
 * author Leave Management System
 */
@Entity
@Table(name = "leave_policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeavePolicy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "minimum_days_notice")
    private Integer minimumDaysNotice;

    @Column(name = "max_consecutive_days")
    private Integer maxConsecutiveDays;

    @Column(name = "max_days_per_year")
    private Integer maxDaysPerYear;

    @Builder.Default
    @Column(name = "pro_rata_accrual")
    private Boolean proRataAccrual = false;

    @Builder.Default
    @Column(name = "carryover_allowed")
    private Boolean carryoverAllowed = false;

    @Column(name = "carryover_days_limit")
    private Integer carryoverDaysLimit;

    @Column(name = "carryover_expiry_months")
    private Integer carryoverExpiryMonths;

    @Builder.Default
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