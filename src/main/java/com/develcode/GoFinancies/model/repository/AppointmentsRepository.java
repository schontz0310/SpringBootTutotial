package com.develcode.GoFinancies.model.repository;

import com.develcode.GoFinancies.model.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
}