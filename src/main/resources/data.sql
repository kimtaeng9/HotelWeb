
-- room 데이터 삽입 (created_at 컬럼 제외)
INSERT INTO `room` (`room_id`, `room_type`, `room_name`, `price`, `reserved_user_id`, `status`)
VALUES
    (1, 'Deluxe', 'Deluxe Room 101', 150.00, NULL, 'available'),
    (2, 'Executive', 'Executive Suite 202', 250.00, 1, 'reserved'),
    (3, 'Presidential', 'Presidential Suite 303', 500.00, NULL, 'available');

-- users 데이터 삽입
INSERT INTO `users` (`id`, `username`, `email`, `phone`, `role`, `password`)
VALUES
    (1, 'john_doe', 'john@example.com', '123-456-7890', 'guest', 'password123'),
    (2, 'jane_smith', 'jane@example.com', '098-765-4321', 'admin', 'password456'),
    (3, 'alice_wang', 'alice@example.com', '555-123-4567', 'user', 'password789');

-- reservation 데이터 삽입
INSERT INTO `reservation` (`id`, `user_id`, `room_id`, `check_in_date`, `check_out_date`, `status`, `created_at`)
VALUES
    (1, 1, 2, '2024-09-01 14:00:00', '2024-09-03 11:00:00', 'confirmed', CURRENT_TIMESTAMP),
    (2, 2, 3, '2024-09-05 15:00:00', '2024-09-06 10:00:00', 'confirmed', CURRENT_TIMESTAMP),
    (3, 3, 1, '2024-09-10 16:00:00', '2024-09-12 12:00:00', 'pending', CURRENT_TIMESTAMP);
