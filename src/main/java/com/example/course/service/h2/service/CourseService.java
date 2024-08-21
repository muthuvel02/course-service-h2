package com.example.course.service.h2.service;

import com.example.course.service.h2.dao.CourseDao;

import com.example.course.service.h2.dto.CourseRequestDTO;
import com.example.course.service.h2.dto.CourseResponseDTO;
import com.example.course.service.h2.entity.CourseEntity;
import com.example.course.service.h2.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CourseService {

    //H2,DERBY , AeroSpike -> In memory Database

    private CourseDao courseDao;


    //create course object in DB -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = courseDao.save(courseEntity);
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }

    //load all the course from Database  // GET
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities = courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(), false)
                .map(AppUtils::mapEntityToDTO)
                .collect(Collectors.toList());

    }

    //filter course by course id //GET
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new RuntimeException(courseId + " not valid"));
        return AppUtils.mapEntityToDTO(courseEntity);
    }

    //delete course  //DELETE
    public void deleteCourse(int courseId) {
        courseDao.deleteById(courseId);
    }

    //update the course //PUT
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        //get the existing object
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);
        //modify existing object with new value
        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());
        //save modified value
        CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);
    }
}


