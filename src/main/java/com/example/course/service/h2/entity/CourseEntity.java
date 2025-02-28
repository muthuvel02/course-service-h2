package com.example.course.service.h2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "COURSE_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String name;
    private String trainerName;
    private String duration;
    private Date startDate;
    private String courseType;
    private double fees;
    private boolean isCertificateAvailable;
    private String description;

}