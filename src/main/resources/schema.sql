DROP TABLE IF EXISTS `consent_history`;
DROP TABLE IF EXISTS `consent`;
DROP TABLE IF EXISTS `consent_types_history`;
DROP TABLE IF EXISTS `consent_types`;
DROP TABLE IF EXISTS `consent_types`;
DROP TABLE IF EXISTS `consent_customer`;
DROP TABLE IF EXISTS `consent_channel`;

create table consent
(
  id bigint auto_increment
    primary key,
  created_by varchar(30) null,
  created_date datetime null,
  last_modified_by varchar(30) null,
  last_modified_date datetime null,
  consent_indicator bit null,
  consent_customer_id bigint not null,
  consent_types_id bigint not null
)
;

create index FK_i_consent_consent_customer
  on consent (consent_customer_id)
;

create index FK_i_consent_consent_types
  on consent (consent_types_id)
;

create table consent_channel
(
  id bigint auto_increment
    primary key,
  name varchar(20) null,
  constraint CONSTRAINT_consent_channel_unique_name
  unique (name)
)
;

create table consent_customer
(
  id bigint auto_increment
    primary key,
  identifier varchar(30) null,
  identifier_type varchar(30) null,
  constraint CONSTRAINT_consent_customer_unique_identifier
  unique (identifier)
)
;

alter table consent
  add constraint FK_consent_consent_customer
foreign key (consent_customer_id) references consent_customer (id)
;

create table consent_history
(
  id int auto_increment
    primary key,
  action varchar(30) null,
  consent_content varchar(2000) null,
  modified_by varchar(30) null,
  modified_date datetime null,
  consent_id bigint null,
  constraint FK_consent_history_consent
  foreign key (consent_id) references consent (id)
)
;

create index FK_i_consent_history_consent
  on consent_history (consent_id)
;

create table consent_types
(
  id bigint auto_increment
    primary key,
  created_by varchar(30) null,
  created_date datetime null,
  last_modified_by varchar(30) null,
  last_modified_date datetime null,
  consent_default_indicator bit not null,
  consent_text varchar(300) null,
  description varchar(4000) null,
  display_name varchar(50) null,
  display_to_customer bit not null,
  name varchar(20) not null,
  consent_channel_id bigint not null,
  constraint CONSTRAINT_consent_types_name
  unique (name),
  constraint FK_consent_types_consent_channel_id
  foreign key (consent_channel_id) references consent_channel (id)
)
;

create index FK_i_consent_types_consent_channel
  on consent_types (consent_channel_id)
;

alter table consent
  add constraint FK_LINK_consent_consent_types
foreign key (consent_types_id) references consent_types (id)
;

create table consent_types_history
(
  id int auto_increment
    primary key,
  action varchar(30) null,
  consent_types_contents varchar(1000) null,
  modified_by varchar(30) null,
  modified_date datetime null,
  consent_types_id bigint null,
  constraint FK_consent_types_history_consent_types
  foreign key (consent_types_id) references consent_types (id)
)
;

create index FK_i_consent_types_history_consent_types
  on consent_types_history (consent_types_id)
;

