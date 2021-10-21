package com.springbootrestgraphjpa.repository;

import com.springbootrestgraphjpa.entity.Student;
import com.springbootrestgraphjpa.request.InQueryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  // select * from student where first_name = <firstName>
  // JPA translates method name into mysql query
  List<Student> findByFirstName(final String firstName);

  // JPA
  // select * from student where first_name = <firstName> and last_name = <lastName>
  Student findByFirstNameAndLastName(final String firstName, final String lastName);

  // JPQL
  // select * from student where first_name = <firstName> and last_name = <lastName>
  @Query("From Student where firstName = :firstname and lastName = :lastname")
  Student getByLastNameAndFirstName(
      @Param("firstname") final String firstname, @Param("lastname") final String lastname);

  // select * from student where first_name = <firstName> and last_name = <lastName>

  // select * from student where first_name = <firstName> or last_name = <lastName>
  List<Student> findByFirstNameOrLastName(final String firstName, final String lastName);

  // select * from student where first_name in (<firstName1>, <firstName2>)
  List<Student> findByFirstNameIn(final List<String> firstNames);

  // select * from student where first_name like '%on%'
  List<Student> findByFirstNameContains(final String firstName);

  // select * from student where first_name like 'John%'
  List<Student> findByFirstNameStartsWith(final String firstName);

  // select * from student where first_name like '%n'
  List<Student> findByFirstNameEndsWith(final String firstName);

  @Modifying      // With JPQL when modifying data
  @Transactional  // With JPQL when modifying data
  @Query("Update Student set firstName = :firstName where id = :id")
  // Modifying can either be 'void' or 'Integer' typed methods
  Integer updateFirstName(final long id, final String firstName);

  @Modifying
  @Transactional
  @Query("Delete From Student where firstName = :firstName")
  Integer deleteByFirstName(final String firstName);
}
