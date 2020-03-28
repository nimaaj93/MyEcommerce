package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "notification_log")
@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipients;

    @Enumerated(EnumType.STRING)
    @NotNull
    private NotificationChannel notificationChannel;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date sendDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverDateTime;

    private String fromSender;

}