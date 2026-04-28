CREATE TABLE tb_enrollments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    event_id UUID NOT NULL,
    participant_id UUID NOT NULL,
    date_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (event_id) REFERENCES tb_events(id) ON DELETE CASCADE,
    FOREIGN KEY (participant_id) REFERENCES tb_participants(id) ON DELETE CASCADE
);