package com.develcode.GoFinancies.model.entity;

import com.develcode.GoFinancies.model.emuns.AppointmentStatus;
import com.develcode.GoFinancies.model.emuns.AppointmentType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@Builder
public class Appointments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "month")
	private Integer month;
	
	@Column(name = "year")
	private Integer year;
	
	@ManyToOne /* Muitos lançamentos para um usuario */
	@JoinColumn(name = "id_user")
	private User user;
	
	@Column(name = "value")
	private BigDecimal value;
	
	@Column(name = "created_at")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate createdAt; 
	
	@Column(name = "type")
	@Enumerated(value = EnumType.STRING)
	private AppointmentType type;
	
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private AppointmentStatus status;
}
