INSERT INTO reservation (user_id, room_id, adults, check_in_date, check_out_date, status, created_at) VALUES
                                                                                                          (1, 1, 2, '2024-09-01', '2024-09-05', 'Booked', '2024-08-01'),
                                                                                                          (2, 3, 1, '2024-09-10', '2024-09-12', 'Checked In', '2024-08-20'),
                                                                                                          (3, 2, 4, '2024-09-15', '2024-09-18', 'Cancelled', '2024-08-25'),
                                                                                                          (4, 5, 2, '2024-09-20', '2024-09-22', 'Booked', '2024-09-01');
INSERT INTO room (room_type, room_name, price) VALUES
                                                   ('Single', 'Cozy Corner', 100),
                                                   ('Double', 'Sea View Retreat', 150),
                                                   ('Suite', 'Luxury Suite', 300),
                                                   ('Single', 'Budget Room', 80),
                                                   ('Double', 'Mountain View', 200);
INSERT INTO users (password, username, email, phone, role) VALUES
                                                               ('1', 'john_doe', '1', '123-456-7890', 'guest'),
                                                               ('password456', 'jane_smith', 'jane.smith@example.com', '234-567-8901', 'admin'),
                                                               ('password789', 'michael_brown', 'michael.brown@example.com', '345-678-9012', 'guest'),
                                                               ('password321', 'linda_jones', 'linda.jones@example.com', '456-789-0123', 'guest');
