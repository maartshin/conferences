insert into conference (description, max_entries, name, start_time, status) values ('Conference with max entries', 2, 'Conference_1', '2022-12-12', 'DEFAULT')
insert into participant (conference_id, email, name) values (1, 'participant@email.ee', 'participant1')
insert into participant (conference_id, email, name) values (1, 'participant@email.ee', 'participant2')

insert into conference (description, max_entries, name, start_time, status) values ('Cancelled conference', 2, 'Conference_2', '2022-12-12', 'CANCELLED')

insert into conference (description, max_entries, name, start_time, status) values ('Conference to be cancelled', 20, 'Conference_3', '2022-12-12', 'DEFAULT')

insert into conference (description, max_entries, name, start_time, status) values ('Upcoming conference', 20, 'Conference_4', '2022-12-12', 'DEFAULT')

insert into conference (description, max_entries, name, start_time, status) values ('Conference, where participant should be removed', 20, 'Conference_5', '2022-12-12', 'DEFAULT')
insert into participant (conference_id, email, name) values (5, 'participant@email.ee', 'participant3')
