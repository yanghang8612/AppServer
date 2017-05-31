CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_phone_number` char(11) NOT NULL,
  `user_password` varchar(16) NOT NULL,
  `user_type` tinyint(1) DEFAULT 0,
  `is_vip` boolean DEFAULT FALSE,
  `invitation_code` char(6) DEFAULT NULL UNIQUE,
  `superior_user_id` bigint(20) DEFAULT 0,
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_login_time` datetime DEFAULT NULL,
  `certification_state` boolean DEFAULT FALSE,
  `debit_card_state` boolean DEFAULT FALSE,
  `header_state` boolean DEFAULT FALSE,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `user_abandoned` (
	`user_id` bigint(20) NOT NULL,
	`user_phone_number` char(11) NOT NULL,
	`user_password` varchar(16) NOT NULL,
	`user_type` tinyint(1) DEFAULT 0,
	`is_vip` boolean DEFAULT FALSE,
	`invitation_code` char(6) DEFAULT NULL,
	`superior_user_id` bigint(20) DEFAULT 0,
	`register_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`last_login_time` datetime DEFAULT NULL,
	`certification_state` boolean DEFAULT FALSE,
	`debit_card_state` boolean DEFAULT FALSE,
	`header_state` boolean DEFAULT FALSE,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `token` char(10) NOT NULL,
  `token_expiration_time` char(13) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_wallet` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL UNIQUE,
	`wallet_balance` decimal(10,2) DEFAULT 0,
	`wallet_points` int(10) DEFAULT 0,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_wallet_balance_record` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`type` tinyint(1) NOT NULL,
	`amount` decimal(10,2) NOT NULL,
	`time` datetime DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_wallet_points_record` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`type` tinyint(1) NOT NULL,
	`amount` int(10) NOT NULL,
	`time` datetime DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_certification_info` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL UNIQUE,
	`user_name` varchar(16) NOT NULL,
	`user_spell` varchar(64) NOT NULL,
	`user_identity_card` char(18) NOT NULL UNIQUE,
	`user_address` varchar(128) NOT NULL,
	`user_sex` char(1) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_debit_card` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL UNIQUE,
	`owner_name` varchar(32) NOT NULL,
	`card_number` varchar(20) NOT NULL,
	`card_type` varchar(32) NOT NULL,
	`head_office` varchar(64) NOT NULL,
	`branch` varchar(64) DEFAULT NULL,
	`province` varchar(64) DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_bank_card` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`owner_name` varchar(32) NOT NULL,
	`card_number` varchar(20) NOT NULL,
	`card_type` varchar(32) NOT NULL,
	`bank_name` varchar(64) NOT NULL,
	`phone_number` char(11) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `apply_credit_card` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`apply_bank` tinyint(1) NOT NULL,
	`apply_user_name` varchar(32) NOT NULL,
	`apply_user_phone_number` char(11) NOT NULL,
	`apply_user_company` varchar(128) NOT NULL,
	`apply_time` datetime DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `apply_loan` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`house_address` varchar(128) NOT NULL,
	`house_property_card` varchar(128) NOT NULL,
	`house_land_sources` varchar(16) NOT NULL,
	`house_type` varchar(16) NOT NULL,
	`house_build_year` varchar(16) NOT NULL,
	`house_build_area` varchar(16) NOT NULL,
	`house_owned_by_others` boolean DEFAULT FALSE,
	`house_is_mortgaged` boolean DEFAULT FALSE,
	`house_borrower_is_owner` boolean DEFAULT FALSE,
	`house_handing_time` varchar(16) NOT NULL,
	`borrower_name` varchar(32) NOT NULL,
	`borrower_phone_number` char(11) NOT NULL,
	`borrower_amount` varchar(16) NOT NULL,
	`borrower_marriage` varchar(16) NOT NULL,
	`borrower_address` varchar(128) NOT NULL,
	`borrower_detailed_address` varchar(128) NOT NULL,
	`apply_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`apply_state` tinyint(1) DEFAULT 0,
	`apply_detail` varchar(256),
	PRIMARY KEY (`id`)
);

CREATE TABLE `payment_order` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`order_transaction` varchar(32) NOT NULL,
	`order_type` tinyint(1) NOT NULL,
	`order_create_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`order_amount` decimal(10,2) NOT NULL,
	`order_state` tinyint(1) DEFAULT 0,
	`payment_id` varchar(32) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `recommend_list` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`type` tinyint(1) NOT NULL,
	`recommender_id` bigint(20) NOT NULL,
	`recommended_id` bigint(20) NOT NULL,
	`recommend_time` datetime DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_share_profit` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) NOT NULL,
	`share_cycle` char(6) NOT NULL,
	`recommend_profit` decimal(10,2) DEFAULT 0,
	`credit_card_profit` decimal(10,2) DEFAULT 0,
	`loan_profit` decimal(10,2) DEFAULT 0,
	`mall_profit` decimal(10,2) DEFAULT 0,
	PRIMARY KEY (`id`)
);