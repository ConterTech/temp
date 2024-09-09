-- Project Name : MAGOYAMESHI
-- Date/Time    : 2024/09/09 8:05:37
-- Author       : yamamoto
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2
/*
 << 注意！！ >>
 BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
 これにより、drop table, create table 後もデータが残ります。
 この機能は一時的に $$TableName のような一時テーブルを作成します。
 この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
 */
-- 検証トークン
-- * BackupToTempTable
DROP TABLE if exists verificationToken CASCADE;



-- * RestoreFromTempTable
CREATE TABLE verificationToken (
    id INT NOT NULL COMMENT 'トークンid',
    user INT NOT NULL COMMENT 'ユーザid',
    token VARCHAR(200) NOT NULL COMMENT 'トークン',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT verificationToken_PKC PRIMARY KEY (id)
) COMMENT '検証トークン';



-- ユーザ属性
-- * BackupToTempTable
DROP TABLE if exists role CASCADE;



-- * RestoreFromTempTable
CREATE TABLE role (
    role_id INT NOT NULL COMMENT '属性id',
    name VARCHAR(20) NOT NULL COMMENT '属性',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT role_PKC PRIMARY KEY (role_id)
) COMMENT 'ユーザ属性';



-- お気に入り
-- * BackupToTempTable
DROP TABLE if exists favorite CASCADE;



-- * RestoreFromTempTable
CREATE TABLE favorite (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT favorite_PKC PRIMARY KEY (store_id, user_id)
) COMMENT 'お気に入り';



-- カテゴリ
-- * BackupToTempTable
DROP TABLE if exists category CASCADE;



-- * RestoreFromTempTable
CREATE TABLE category (
    category_id INT NOT NULL COMMENT 'カテゴリid',
    category VARCHAR(30) NOT NULL COMMENT 'カテゴリ名',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT category_PKC PRIMARY KEY (category_id)
) COMMENT 'カテゴリ';



-- レビュー
-- * BackupToTempTable
DROP TABLE if exists review CASCADE;



-- * RestoreFromTempTable
CREATE TABLE review (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    review_star INT NOT NULL COMMENT '評価星',
    review_text TEXT NOT NULL COMMENT '評価文',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT review_PKC PRIMARY KEY (store_id, user_id)
) COMMENT 'レビュー';



-- 予約情報
-- * BackupToTempTable
DROP TABLE if exists reservation CASCADE;



-- * RestoreFromTempTable
CREATE TABLE reservation (
    store_id INT NOT NULL COMMENT '店舗id',
    user_id INT NOT NULL COMMENT 'ユーザid',
    checkin_time TIME NOT NULL COMMENT '開始時間',
    number_of_people INT NOT NULL COMMENT '予約人数',
    remarks TEXT NOT NULL COMMENT '備考欄',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT reservation_PKC PRIMARY KEY (store_id, user_id)
) COMMENT '予約情報';



-- ユーザ情報
-- * BackupToTempTable
DROP TABLE if exists user CASCADE;



-- * RestoreFromTempTable
CREATE TABLE user (
    user_id INT NOT NULL COMMENT 'ユーザid',
    name VARCHAR(30) NOT NULL COMMENT '氏名',
    phone_number VARCHAR(15) NOT NULL COMMENT '電話番号',
    post_code VARCHAR(10) NOT NULL COMMENT '郵便番号',
    address VARCHAR(100) NOT NULL COMMENT '住所',
    email VARCHAR(50) NOT NULL COMMENT 'メールアドレス',
    age INT NOT NULL COMMENT '年齢',
    gender VARCHAR(20) NOT NULL COMMENT '性別',
    password VARCHAR(100) NOT NULL COMMENT 'パスワード',
    role INT NOT NULL COMMENT '属性',
    enabled boolean NOT NULL COMMENT '有効可否',
    paid_flag boolean DEFAULT 0 COMMENT '有料会員フラグ',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT user_PKC PRIMARY KEY (user_id)
) COMMENT 'ユーザ情報';



-- 店舗特別営業時間
-- * BackupToTempTable
DROP TABLE if exists store_special_business_time CASCADE;



-- * RestoreFromTempTable
CREATE TABLE store_special_business_time (
    store_id INT NOT NULL COMMENT '店舗id',
    special_business_day DATE NOT NULL COMMENT '特別営業日',
    business_start_time TIME NOT NULL COMMENT '営業開始時間',
    business_end_time TIME NOT NULL COMMENT '営業終了時間',
    rest_flag boolean DEFAULT 0 COMMENT '定休日',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_special_business_time_PKC PRIMARY KEY (store_id, special_business_day)
) COMMENT '店舗特別営業時間';



-- 店舗営業時間
-- * BackupToTempTable
DROP TABLE if exists store_business_time CASCADE;



-- * RestoreFromTempTable
CREATE TABLE store_business_time (
    store_id INT NOT NULL COMMENT '店舗id',
    weekday INT NOT NULL COMMENT '曜日:0：月曜日
1：火曜日
2：水曜日
3：木曜日
4：金曜日
5：土曜日
6：日曜日
7：祝日',
    business_start_time TIME NOT NULL COMMENT '営業開始時間',
    business_end_time TIME NOT NULL COMMENT '営業終了時間',
    rest_flag boolean DEFAULT 0 COMMENT '定休日',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_business_time_PKC PRIMARY KEY (store_id, weekday)
) COMMENT '店舗営業時間';



-- 店舗情報
-- * BackupToTempTable
DROP TABLE if exists store CASCADE;



-- * RestoreFromTempTable
CREATE TABLE store (
    store_id INT NOT NULL COMMENT '店舗id',
    store_name VARCHAR(30) NOT NULL COMMENT '店舗名',
    image_name VARCHAR(300) NOT NULL COMMENT '店舗写真',
    post_code VARCHAR(10) NOT NULL COMMENT '郵便番号',
    address VARCHAR(100) NOT NULL COMMENT '住所',
    phone_number VARCHAR(15) NOT NULL COMMENT '電話番号',
    parking_storage INT NOT NULL COMMENT '駐車場台数',
    store_describe TEXT NOT NULL COMMENT '店舗説明',
    category_id INT NOT NULL COMMENT 'カテゴリid',
    delete_flag boolean DEFAULT 0 COMMENT '削除フラグ',
    CONSTRAINT store_PKC PRIMARY KEY (store_id)
) COMMENT '店舗情報:店舗情報';