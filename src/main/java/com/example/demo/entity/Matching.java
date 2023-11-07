package com.example.demo.entity;

import com.example.demo.matching.dto.MatchingDetailDto;
import com.example.demo.type.AgeGroup;
import com.example.demo.type.MatchingType;
import com.example.demo.type.Ntrp;
import com.example.demo.type.RecruitStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SITE_USER_ID", nullable = false)
    private SiteUser siteUser;

    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;

    @Column(name = "CONTENT", length = 1023)
    private String content;

    @Column(name = "LOCATION", length = 255, nullable = false)
    private String location;

    @Column(name = "LOCATION_IMG", length = 1023)
    private String locationImg;

    @Column(name = "DATE", nullable = false) // yyyy-MM-dd
    private LocalDate date;

    @Column(name = "START_TIME", nullable = false) // HH:mm
    private LocalTime startTime;

    @Column(name = "END_TIME", nullable = false) // HH:mm
    private LocalTime endTime;

    @Column(name = "RECRUIT_DUE_DATE", nullable = false) // yyyy-MM-dd HH:mm
    private LocalDateTime recruitDueDate;

    @Column(name = "RECRUIT_NUM", nullable = false)
    private Integer recruitNum;

    @Column(name = "COST", nullable = false)
    private Integer cost;

    @Column(name = "IS_RESERVED", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isReserved;

    @Enumerated(EnumType.STRING)
    @Column(name = "NTRP", length = 50)
    private Ntrp ntrp;

    @Enumerated(EnumType.STRING)
    @Column(name = "AGE", length = 50)
    private AgeGroup age;

    @Enumerated(EnumType.STRING)
    @Column(name = "RECRUIT_STATUS", length = 50, columnDefinition = "DEFAULT 'OPEN'")
    private RecruitStatus recruitStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "MATCHING_TYPE", length = 50)
    private MatchingType matchingType;

    @Column(name = "CONFIRMED_NUM", columnDefinition = "INT DEFAULT 1")
    private Integer confirmedNum;

    @CreatedDate
    @Column(name = "CREATE_TIME") // yyyy-MM-dd HH:mm
    private LocalDateTime createTime;

    public static Matching fromDto(MatchingDetailDto matchingDetailDto, SiteUser siteUser) {
        // 시간 파싱
        DateTimeFormatter formForDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formForTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formForDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(matchingDetailDto.getDate(), formForDate);
        LocalTime startTime = LocalTime.parse(matchingDetailDto.getStartTime(), formForTime);
        LocalTime endTime = LocalTime.parse(matchingDetailDto.getEndTime(), formForTime);
        LocalDateTime recruitDueDate = LocalDateTime
                .parse(matchingDetailDto.getRecruitDueDate(), formForDateTime);

        return Matching.builder()
                .siteUser(siteUser)
                .title(matchingDetailDto.getTitle())
                .content(matchingDetailDto.getContent())
                .location(matchingDetailDto.getLocation())
                .locationImg(matchingDetailDto.getLocationImg())
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .recruitDueDate(recruitDueDate)
                .recruitNum(matchingDetailDto.getRecruitNum())
                .cost(matchingDetailDto.getCost())
                .isReserved(matchingDetailDto.getIsReserved())
                .ntrp(matchingDetailDto.getNtrp())
                .age(matchingDetailDto.getAgeGroup())
                .recruitStatus(matchingDetailDto.getRecruitStatus())
                .matchingType(matchingDetailDto.getMatchingType())
                .confirmedNum(matchingDetailDto.getApplyNum())
                .build();
    }

    // setter 없이 Matching 수정하기 위한 메서드
    public void update(Matching matching){
        this.title = matching.getTitle();
        this.content = matching.getContent();
        this.location = matching.getLocation();
        this.locationImg = matching.getLocationImg();
        this.date = matching.getDate();
        this.startTime = matching.getStartTime();
        this.endTime = matching.getEndTime();
        this.recruitDueDate = matching.getRecruitDueDate();
        this.recruitNum = matching.getRecruitNum();
        this.cost = matching.getCost();
        this.isReserved = matching.getIsReserved();
        this.ntrp = matching.getNtrp();
        this.age = matching.getAge();
        this.matchingType = matching.getMatchingType();
    }
}