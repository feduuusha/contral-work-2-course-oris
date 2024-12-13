CREATE TABLE gift (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    giver_name VARCHAR NOT NULL,
    receiver_name VARCHAR NOT NULL,
    date_of_given TIMESTAMP NOT NULL
)