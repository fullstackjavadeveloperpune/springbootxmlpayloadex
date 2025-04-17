package com.fullstack.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custId;

    private String custName;

    private String custAddress;

    private long custContactNumber;

    private double custAccBalance;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;

    private String custEmailId;

    private String custPassword;
}
