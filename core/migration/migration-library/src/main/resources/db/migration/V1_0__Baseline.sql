--region repository
create table if not exists repository (
    id int8 not null,
    created timestamp not null,
    removed timestamp,
    updated timestamp,
    uuid varchar(255) not null,
    full_name varchar(255) not null,
    name varchar(255) not null,
    remote_id varchar(255) not null,
    primary key (id)
);

alter table repository add constraint uk_repository_uuid unique (uuid);
alter table repository add constraint uk_repository_remote_id unique (remote_id);

create index idx_repository_remote_id on repository USING btree (remote_id);
create index idx_repository_full_name on repository USING btree (full_name);

create sequence repository_seq start 1 increment 1;
--endregion

--region user
create table user_ (
    id int8 not null,
    created timestamp not null,
    removed timestamp,
    updated timestamp,
    uuid varchar(255) not null,
    login varchar(255) not null,
    remote_id varchar(255),
    primary key (id)
);

alter table user_ add constraint uk_user_uuid unique (uuid);
alter table user_ add constraint uk_user_remote_id unique (remote_id);

create index idx_user_remote_id on user_ USING btree (remote_id);

create sequence user_seq start 1 increment 1;
--endregion

create table commit (
    id int8 not null,
    created timestamp not null,
    removed timestamp,
    updated timestamp,
    uuid varchar(255) not null,
    remote_created timestamp not null,
    sha varchar(255) not null,
    repository_id int8 not null,
    user_id int8 not null,
    primary key (id)
);

alter table commit add constraint UK_commit_uuid unique (uuid);
alter table commit add constraint UK_commit_sha unique (sha);

alter table commit add constraint fk_commit_repository_id foreign key (repository_id) references repository;
alter table commit add constraint fk_commit_user_id foreign key (user_id) references user_;

create index idx_commit_repository_id on commit USING btree (repository_id);
create index idx_commit_sha on commit USING btree (sha);

create sequence commit_seq start 1 increment 1;
--endregion

--region repository user
create table repository_contributor (
    id int8 not null,
    created timestamp not null,
    removed timestamp,
    updated timestamp,
    uuid varchar(255) not null,
    repository_id int8 not null,
    user_id int8 not null,
    primary key (id)
);

alter table repository_contributor add constraint fk_repository_contributor_repository_id foreign key (repository_id) references repository;
alter table repository_contributor add constraint fk_repository_contributor_user_id foreign key (user_id) references user_;

alter table repository_contributor add constraint uk_repository_contributor_id_repository_id unique (repository_id, user_id);
alter table repository_contributor add constraint uk_repository_contributor_uuid unique (uuid);

create index idx_repository_contributor_repository_id on repository_contributor USING btree (repository_id);
create index idx_repository_contributor_repository_id_user_id on repository_contributor USING btree (repository_id, user_id);

create sequence repository_contributor_seq start 1 increment 1;
--endregion