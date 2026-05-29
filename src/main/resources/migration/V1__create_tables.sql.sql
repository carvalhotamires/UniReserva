CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100),
                       email VARCHAR(100) UNIQUE,
                       password VARCHAR(255),
                       role VARCHAR(20)
);

CREATE TABLE reservations (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              reservation_date DATE,
                              start_time TIME,
                              end_time TIME,
                              room_name VARCHAR(100),
                              user_id BIGINT,

                              CONSTRAINT fk_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)
);