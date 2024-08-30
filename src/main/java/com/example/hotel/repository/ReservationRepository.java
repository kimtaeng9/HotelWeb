package com.example.hotel.repository;

import com.example.hotel.entity.Reservation;
import com.example.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Room r " +
            "WHERE r.status = 'available' " +
            "AND r.roomId NOT IN (" +
            "  SELECT res.roomId FROM Reservation res " +
            "  WHERE res.checkInDate <= :checkOutDate " +
            "  AND res.checkOutDate >= :checkInDate" +
            ")")
    List<Room> findAvailableRooms(@Param("checkInDate") LocalDate checkInDate,
                                  @Param("checkOutDate") LocalDate  checkOutDate);

    // 새로운 예약 생성
    // 컨트롤러에서 makeReservaion호출하도록 변경함
    @Modifying
    @Transactional
    @Query("UPDATE Room r SET r.status = 'reserved' WHERE r.roomId = :roomId")
    int reserveRoom(@Param("roomId") Long roomId);
}
