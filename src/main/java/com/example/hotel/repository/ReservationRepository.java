package com.example.hotel.repository;

import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Room r " +
            "LEFT JOIN Reservation res ON r = res.room " +
            "AND (res.checkInDate <= :checkOutDate AND res.checkOutDate >= :checkInDate) " +
            "WHERE res.room IS NULL")
    List<Room> findAvailableRooms(@Param("checkInDate") LocalDate checkInDate,
                                  @Param("checkOutDate") LocalDate checkOutDate);

    List<Reservation> findByUserId(Long userId);
}
