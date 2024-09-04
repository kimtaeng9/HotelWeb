-- 먼저 'users' 테이블에 데이터 삽입
INSERT INTO users (id, email, password, phone, role, username) VALUES
                                                                   (1, 'admin@example.com', '1', '1234567890', 'ROLE_ADMIN', 'admin')
                                                                   ;
ALTER TABLE users ALTER COLUMN id RESTART WITH 2;
-- 'room' 테이블에 데이터 삽입
INSERT INTO room (room_id, price, room_name, room_type) VALUES
                                                            (1, 900, 'Room A', 'Presidential'),
                                                            (2, 800, 'Room B', 'Executive'),
                                                            (3, 750, 'Room C', 'Executive'),
                                                            (4, 600, 'Room D', 'Deluxe'),
                                                            (5, 550, 'Room E', 'Deluxe');

