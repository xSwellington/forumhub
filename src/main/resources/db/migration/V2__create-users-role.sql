CREATE TABLE roles (
    id bigint not null auto_increment,
    name varchar(50) not null unique,
    primary key (id)
);

CREATE TABLE users_roles(
    role_id bigint not null,
    user_id bigint not null
);


alter table users_roles add constraint fk_roles_role_id foreign key (role_id) references roles(id) ON DELETE CASCADE ;
alter table users_roles add constraint fk_roles_user_id foreign key (user_id) references users(id) ON DELETE CASCADE ;
