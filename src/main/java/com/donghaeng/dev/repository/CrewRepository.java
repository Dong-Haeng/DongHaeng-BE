package com.donghaeng.dev.repository;

import com.donghaeng.dev.domain.Crew;
import com.donghaeng.dev.domain.Division;
import com.donghaeng.dev.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    List<Crew> findAllByUniversity(University university);

    @Query("select c from Crew c where c.university =:univesity and c.isRecruiting =:isRecruiting")
    List<Crew> findAllByUniversityFilterIsRecruiting(@Param("university")University university,
                                                     @Param("isRecruiting") Boolean isRecruiting);

    @Query("select c from Crew c where c.university =:univesity and c.isRecruiting =:isRecruiting and c.division =:division")
    List<Crew> findAllByUniversityFilterByDivisionAndIsRecruiting(@Param("university")University university,
                                                                  @Param("division") Division division,
                                                                  @Param("isRecruiting") Boolean isRecruiting);
}
