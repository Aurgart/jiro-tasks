ALTER TYPE jiro_task.status ADD VALUE 'DELETED';

CREATE TABLE jiro_task.state_scheme
(
    id serial NOT NULL,
    state_from jiro_task.status NOT NULL,
    state_to jiro_task.status NOT NULL,
    forbidden boolean not null DEFAULT false,
    PRIMARY KEY (id)
);

insert into  jiro_task.state_scheme(state_form, state_to)
values  ('TO_DO','IN_PROGRESS');
insert into  jiro_task.state_scheme(state_form, state_to)
values  ('TO_DO','DELETED');
insert into  jiro_task.state_scheme(state_form, state_to)
values  ('IN_PROGRESS','DONE');
insert into  jiro_task.state_scheme(state_form, state_to)
values  ('IN_PROGRESS','DELETED');
insert into  jiro_task.state_scheme(state_form, state_to)
values  ('DONE ','DELETED');
insert into  jiro_task.state_scheme(state_form, state_to, forbidden)
values  ('TO_DO','DONE',true);
insert into  jiro_task.state_scheme(state_form, state_to, forbidden)
values  ('IN_PROGRESS','TO_DO',true);
insert into  jiro_task.state_scheme(state_form, state_to, forbidden)
values  ('DONE','IN_PROGRESS',true);
