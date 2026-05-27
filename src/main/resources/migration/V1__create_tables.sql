CREATE TABLE reservations (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              reservation_date DATE,
                              start_time TIME,
                              end_time TIME,
                              status VARCHAR(50),
                              user_id BIGINT,
                              room_id BIGINT
);