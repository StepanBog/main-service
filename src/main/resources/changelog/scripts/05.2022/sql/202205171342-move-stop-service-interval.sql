alter table service_stop_interval
    add column position_id uuid
        constraint service_stop_interval_position_id_id_fk
            references position,
    drop column employee_id;