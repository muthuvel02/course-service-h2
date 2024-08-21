package com.example.course.service.h2.controller;

import com.example.course.service.h2.dto.CourseRequestDTO;
import com.example.course.service.h2.dto.CourseResponseDTO;
import com.example.course.service.h2.dto.ServiceResponseDTO;
import com.example.course.service.h2.service.CourseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ServiceResponseDTO<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        ServiceResponseDTO<CourseResponseDTO> serviceResponse=new ServiceResponseDTO<>();
        try {
            CourseResponseDTO newCourse = courseService.onboardNewCourse(courseRequestDTO);
            serviceResponse.setStatus(HttpStatus.OK);
            serviceResponse.setResponse(newCourse);
        }catch(Exception exception){
            serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    @GetMapping
    public ServiceResponseDTO<List<CourseResponseDTO>> findALlCourse() {
        List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourses();
        return new ServiceResponseDTO<>(HttpStatus.OK, courseResponseDTOS);
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponseDTO<CourseResponseDTO> findCourse(@PathVariable Integer courseId) {
        CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponseDTO<>(HttpStatus.OK, responseDTO);
    }

    @GetMapping("/search/request")
    public ServiceResponseDTO<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId) {
        CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponseDTO<>(HttpStatus.OK, responseDTO);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponseDTO <CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course) {
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, course);
        return new ServiceResponseDTO<>(HttpStatus.OK, courseResponseDTO);
    }


}
