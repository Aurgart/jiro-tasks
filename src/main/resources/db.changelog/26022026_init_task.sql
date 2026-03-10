CREATE TYPE jiro_task.status as ENUM ('TO_DO','ANALYSIS','IN_PROGRESS','DONE','REJECTED');

CREATE TABLE jiro_task.task
(
    id serial NOT NULL,
    title varchar NOT NULL,
    description varchar NOT NULL,
    state jiro_task.status NOT NULL DEFAULT 'TO_DO',
    author integer NOT NULL,
    assignee integer NOT NULL,
    dead_line DATE NOT NULL,
    create_date  DATE NOT NULL,
    update_date  DATE,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX task_unix01 ON jiro_task.task(title);