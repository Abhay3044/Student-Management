package com.school_management.repository;

import com.school_management.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

//    @Query("select * form students_info where name : name ")
    @Query("SELECT s FROM Students s WHERE s.name = :name")
    Students findByName(@Param("name") String name);
}
