INSERT INTO member (member_id, first_name, last_name, is_active, is_admin, is_student_member, email, password, join_date,
                    membership_fee_amount,
                    membership_paid_date)
VALUES (1, 'Aliko', 'Dangote', true, false, false, 'a@test.com', '{noop}pass', '2017-12-31', '99.99', '2018-01-02 12:31:11'),
       (2, 'Bill', 'Gates', true, false, true, 'b@test.com', '{noop}pass', '2017-12-31', '19.99', null),
       (3, 'Folrunsho', 'Alakija', true, true, false, 'c@test.com', '{noop}pass', '2017-12-31', '99.99',
        '2018-01-02 12:31:11');

INSERT INTO member_phone (member_phone_id, member_id, phone_number) VALUES
  (1, 1, '123-123-1234'),
  (2, 1, '123-123-1234'),
  (3, 2, '123-123-1234');

INSERT INTO member_family (member_family_id, member_id, name)
VALUES (1, 1, 'Bob Smith'),
       (2, 1, 'Jane Jones'),
       (3, 2, 'Melinda Gates');

INSERT INTO member_address (member_address_id, member_id, street_address, city, state, zip_code) VALUES
  (1, 1, '123 Fake Street', 'Fakeville', 'MN', '12345'),
  (2, 1, '123 Fake Street', 'Fakeville', 'MN', '12345'),
  (3, 2, '123 Fake Street', 'Fakeville', 'MN', '12345');

alter sequence HIBERNATE_SEQUENCE restart with (select max(member_id) + 1 from member)
