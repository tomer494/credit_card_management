    create table `user` (
       `id` integer not null AUTO_INCREMENT,
        `dob` timestamp,
        `email` varchar(50),
        `id_number` varchar(20),
        `name` varchar(100),
        `password` varchar(255),
        `phone` varchar(20),
        primary key (id)
    );

    CREATE INDEX `email` ON `user` (`email`);
    CREATE INDEX `name` ON `user` (`name`);
    CREATE INDEX `phone` ON `user` (`phone`);
    CREATE unique INDEX `id_number` ON `user` (`id_number`);

    create table `card` (
       `id` bigint not null AUTO_INCREMENT,
        `active` boolean not null,
        `balance` double not null,
        `cardNumber` varchar(20),
        `credit` double not null,
        `issue_date` timestamp,
        `issued` boolean not null,
        `last_active` timestamp,
        `user_id` integer,
        primary key (`id`),
        constraint FK_User_card FOREIGN KEY (user_id) REFERENCES user(`id`)
    );

    CREATE UNIQUE INDEX cardNumber ON `card` (cardNumber);

    create table `transaction` (
       `id` bigint not null AUTO_INCREMENT,
        `balance` double not null,
        `credit` double not null,
        `date` timestamp,
        `description` varchar(255),
        `init_balance` double not null,
        `init_credit` double not null,
        `type` varchar(10),
        `card_id` bigint,
        `user_id` integer,
        primary key (`id`),
        constraint FK_card_transaction FOREIGN KEY (card_id) REFERENCES card(`id`),
        constraint FK_User_transaction FOREIGN KEY (user_id) REFERENCES user(`id`)
    );
    CREATE INDEX `date_type` ON `transaction` (`date`,`type`);


    create table `user_cards` (
       user_id integer not null,
       card_id bigint not null,
       constraint UK_card_user_cards unique (card_id),
       constraint FK_card_user_cards FOREIGN KEY (card_id) REFERENCES card(`id`),
       constraint FK_User_user_cards FOREIGN KEY (user_id) REFERENCES user(`id`)
    );

    create table `user_transactions` (
       user_id integer not null,
        transaction_id bigint not null,
        constraint UK_transactions unique (transaction_id),
      constraint FK_transaction_user_transactions FOREIGN KEY (transaction_id) REFERENCES transaction(`id`),
      constraint FK_User_user_transactions FOREIGN KEY (user_id) REFERENCES user(`id`)
    );