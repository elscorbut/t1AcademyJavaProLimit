CREATE TABLE limits
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE NOT NULL,
    value   DECIMAL       NOT NULL
);

DO
$$
    BEGIN
        FOR i IN 1..100
            LOOP
                INSERT INTO limits (user_id, value)
                VALUES (i, 10000.00);
            END LOOP;
    END
$$;