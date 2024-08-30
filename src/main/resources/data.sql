-- room 데이터 삽입 (created_at 컬럼 제외)
INSERT INTO room (room_id, room_type, room_name, price, status)
VALUES
    (1, 'Deluxe', 'Deluxe Room 101', 150.00, 'available'),
    (2, 'Executive', 'Executive Suite 202', 250.00, 'reserved'),
    (3, 'Presidential', 'Presidential Suite 303', 500.00, 'available');

-- users 데이터 삽입 (password 컬럼 제외)
INSERT INTO users (username, email, password, phone, role) VALUES ('random_user', 'random1@example.com', '1', '123-456-7890', 'USER');

-- reservation 데이터 삽입
INSERT INTO `reservation` (`id`, `user_id`, `room_id`, `check_in_date`, `check_out_date`, `status`, `created_at`)
VALUES
    (1, 1, 2, '2024-09-01', '2024-09-03', 'confirmed', CURRENT_TIMESTAMP),
    (2, 2, 3, '2024-09-05', '2024-09-06', 'confirmed', CURRENT_TIMESTAMP),
    (3, 3, 1, '2024-09-10', '2024-09-12', 'pending', CURRENT_TIMESTAMP);
