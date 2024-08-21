package com.example.course.service.h2.dao;

import com.example.course.service.h2.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends CrudRepository<CourseEntity,Integer> {
}
