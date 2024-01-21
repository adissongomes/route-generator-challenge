CREATE TABLE IF NOT EXISTS route_events(
    id UUID PRIMARY KEY DEFAULT random_uuid(),
    route_id UUID NOT NULL,
    origin_id UUID NOT NULL,
    destination_id UUID NOT NULL,
    courier_id UUID NOT NULL,
    status TEXT NOT NULL,
    event_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
