CREATE TYPE jiro_task.status as ENUM ('TO_DO','ANALYSIS','IN_PROGRESS','DONE','REJECTED');


CREATE TABLE jiro_task.task
(
    id serial NOT NULL,
    title varchar NOT NULL,
    description varchar NOT NULL,
    state status NOT NULL DEFAULT 'TO_DO',
    author NOT NULL,
    assignee NOT NULL,
    dead_line DATE NOT NULL,
    create_date  DATE NOT NULL,
    update_date  DATE,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX user_unix01 ON jiro_user.jiro_user(title);