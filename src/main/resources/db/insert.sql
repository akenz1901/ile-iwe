set foreign_key_checks = 0;

truncate table learning_party;
truncate table authority;
truncate table instructor;

insert into learning_party(`id`, `email`, `password`, `enabled`)
value(123, 'tobi@mail.com', '123pass233', false),
      (124, 'bobi@mail.com', '123pazz233', false),
      (125, 'robi@mail.com', '123pasee233', false),
      (126, 'dibi@mail.com', '123paff233', false),
      (127, 'tibi@mail.com', '123paph233', false);

set foreign_key_checks = 1;