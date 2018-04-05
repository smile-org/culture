CREATE TABLE banner
(
  banner_id INT AUTO_INCREMENT
    PRIMARY KEY,
  image     VARCHAR(200)                        NOT NULL,
  link      VARCHAR(100)                        NOT NULL,
  title_cn  VARCHAR(50)                         NOT NULL,
  title_en  VARCHAR(200)                        NOT NULL,
  desc_cn   VARCHAR(200)                        NOT NULL,
  desc_en   VARCHAR(500)                        NOT NULL,
  status    INT DEFAULT '0'                     NOT NULL,
  `order`   INT DEFAULT '0'                     NOT NULL,
  create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT banner_banner_id_uindex
  UNIQUE (banner_id)
)
  COMMENT '广告热图'
  ENGINE = InnoDB;

CREATE TABLE ccr
(
  ccr_id    INT AUTO_INCREMENT
    PRIMARY KEY,
  name      VARCHAR(50)                         NULL,
  phone     VARCHAR(50)                         NULL,
  `desc`    VARCHAR(2000)                       NULL,
  create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT ccr_ccr_id_uindex
  UNIQUE (ccr_id)
)
  COMMENT '传承人计划'
  ENGINE = InnoDB;

CREATE TABLE exhibit
(
  exhibit_id INT AUTO_INCREMENT
    PRIMARY KEY,
  title_cn   VARCHAR(500)  NULL,
  title_en   VARCHAR(1000) NULL,
  content_cn VARCHAR(5000) NULL,
  content_en VARCHAR(8000) NULL,
  category   INT           NOT NULL,
  form_id    INT           NULL,
  CONSTRAINT exhibit_exhibit_id_uindex
  UNIQUE (exhibit_id)
)
  COMMENT '文化展览'
  ENGINE = InnoDB;

CREATE TABLE focus
(
  focus_id  INT AUTO_INCREMENT
    PRIMARY KEY,
  image     VARCHAR(200)                        NOT NULL,
  link      VARCHAR(100)                        NOT NULL,
  title_cn  VARCHAR(50)                         NOT NULL,
  title_en  VARCHAR(200)                        NOT NULL,
  desc_cn   VARCHAR(200)                        NOT NULL,
  desc_en   VARCHAR(500)                        NOT NULL,
  status    INT DEFAULT '0'                     NOT NULL,
  `order`   INT DEFAULT '0'                     NOT NULL,
  create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT focus_focus_id_uindex
  UNIQUE (focus_id)
)
  COMMENT '热点聚焦'
  ENGINE = InnoDB;

CREATE TABLE form
(
  form_id      INT AUTO_INCREMENT
    PRIMARY KEY,
  form_en_name VARCHAR(500)                        NULL,
  form_cn_name VARCHAR(1000)                       NULL,
  create_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT form_form_id_uindex
  UNIQUE (form_id)
)
  ENGINE = InnoDB;

CREATE TABLE jjh
(
  jjh_id    INT AUTO_INCREMENT
    PRIMARY KEY,
  name      VARCHAR(50)                         NULL,
  phone     VARCHAR(50)                         NULL,
  email     VARCHAR(100)                        NULL,
  `desc`    VARCHAR(2000)                       NULL,
  create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT jjh_jjh_id_uindex
  UNIQUE (jjh_id)
)
  COMMENT '京交会'
  ENGINE = InnoDB;

CREATE TABLE module
(
  module_id INT AUTO_INCREMENT
    PRIMARY KEY,
  image     VARCHAR(200)                        NOT NULL,
  link      VARCHAR(100)                        NOT NULL,
  title_cn  VARCHAR(50)                         NOT NULL,
  title_en  VARCHAR(200)                        NOT NULL,
  desc_cn   VARCHAR(200)                        NOT NULL,
  desc_en   VARCHAR(500)                        NOT NULL,
  create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT module_module_id_uindex
  UNIQUE (module_id)
)
  COMMENT '核心模块'
  ENGINE = InnoDB;

CREATE TABLE news
(
  news_id    INT AUTO_INCREMENT
    PRIMARY KEY,
  image      VARCHAR(200)                        NOT NULL,
  title_cn   VARCHAR(200)                        NOT NULL,
  title_en   VARCHAR(500)                        NOT NULL,
  link       VARCHAR(100)                        NULL,
  desc_cn    VARCHAR(1000)                       NULL,
  desc_en    VARCHAR(2000)                       NULL,
  content_cn VARCHAR(5000)                       NULL,
  content_en VARCHAR(8000)                       NULL,
  create_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT 'now()',
  CONSTRAINT news_news_id_uindex
  UNIQUE (news_id)
)
  COMMENT '新闻表'
  ENGINE = InnoDB;

CREATE TABLE user
(
  user_id  INT AUTO_INCREMENT
    PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  token    VARCHAR(50) NOT NULL,
  nickname VARCHAR(50) NULL,
  CONSTRAINT user_username_uindex
  UNIQUE (username)
)
  COMMENT '用户表'
  ENGINE = InnoDB;

