INSERT INTO member (member_id, first_name, last_name, is_active) VALUES
  (1, 'Aliko', 'Dangote', true),
  (2, 'Bill', 'Gates', true),
  (3, 'Folrunsho', 'Alakija', true);

INSERT INTO member_email (member_email_id, member_id, email_address) VALUES
  (1, 1, 'dangote@test.com'),
  (2, 1, 'dangote2@test.com'),
  (3, 2, 'gates@test.com'),
  (4, 3, 'alakija@test.com');

INSERT INTO member_phone (member_phone_id, member_id, phone_number) VALUES
  (1, 1, '123-123-1234'),
  (2, 1, '123-123-1234'),
  (3, 2, '123-123-1234'),
  (4, 3, '123-123-1234');

INSERT INTO member_family (member_family_id, member_id, first_name, last_name) VALUES
  (1, 1, 'Bob', 'Smith'),
  (2, 1, 'Jane', 'Jones'),
  (3, 2, 'Melinda', 'Gates'),
  (4, 3, 'Joe', 'Degiovanni');

INSERT INTO member_address (member_address_id, member_id, street_address, city, state, zip_code) VALUES
  (1, 1, '123 Fake Street', 'Fakeville', 'MN', '12345'),
  (2, 1, '123 Fake Street', 'Fakeville', 'MN', '12345'),
  (3, 2, '123 Fake Street', 'Fakeville', 'MN', '12345'),
  (4, 3, '123 Fake Street', 'Fakeville', 'MN', '12345');
