CREATE TABLE IF NOT EXISTS routes
(
    id             UUID PRIMARY KEY,
    origin_id      UUID      NOT NULL,
    destination_id UUID      NOT NULL,
    courier_id     UUID      NOT NULL,
    status         TEXT      NOT NULL,
    created_at     TIMESTAMP NOT NULL DEFAULT now(),
    updated_at     TIMESTAMP          DEFAULT now()
);

CREATE TABLE IF NOT EXISTS route_events
(
    id         UUID               DEFAULT random_uuid() PRIMARY KEY,
    route_id   UUID      NOT NULL,
    status     TEXT      NOT NULL,
    event_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    constraint fk_route_id foreign key (route_id) references routes (id)
);
