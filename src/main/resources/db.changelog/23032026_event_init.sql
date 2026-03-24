CREATE TABLE jiro_task.task_event
(
    id serial NOT NULL,
    task_id integer NOT NULL
    title varchar,
    description varchar,
    state jiro_task.status,
    author integer,
    assignee integer,
    dead_line DATE,
    create_date  DATE,
    update_date  DATE,
    event_type varchar,
    PRIMARY KEY (id)
);