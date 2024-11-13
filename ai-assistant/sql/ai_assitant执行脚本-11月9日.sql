/*
 Navicat Premium Dump SQL

 Source Server         : 本地
 Source Server Type    : PostgreSQL
 Source Server Version : 160003 (160003)
 Source Host           : localhost:5432
 Source Catalog        : ai_assistant_db
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160003 (160003)
 File Encoding         : 65001

 Date: 09/11/2024 10:09:02
*/


-- ----------------------------
-- Sequence structure for lev_wx_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."lev_wx_user_id_seq";
CREATE SEQUENCE "public"."lev_wx_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_user";
CREATE TABLE "public"."t_user" (
  "user_id" int8 NOT NULL,
  "mobile" text COLLATE "pg_catalog"."default" NOT NULL,
  "password" text COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "role" int4,
  "educate_stage" varchar COLLATE "pg_catalog"."default",
  "nickname" varchar COLLATE "pg_catalog"."default",
  "real_name" varchar COLLATE "pg_catalog"."default",
  "license_type" varchar COLLATE "pg_catalog"."default",
  "license_number" varchar COLLATE "pg_catalog"."default",
  "school" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_user"."user_id" IS '用户ID';
COMMENT ON COLUMN "public"."t_user"."mobile" IS '手机号';
COMMENT ON COLUMN "public"."t_user"."password" IS '密码';
COMMENT ON COLUMN "public"."t_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_user"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."t_user"."role" IS '角色';
COMMENT ON COLUMN "public"."t_user"."educate_stage" IS '教育阶段';
COMMENT ON COLUMN "public"."t_user"."nickname" IS '昵称';
COMMENT ON COLUMN "public"."t_user"."real_name" IS '真实姓名';
COMMENT ON COLUMN "public"."t_user"."license_type" IS '证件类型';
COMMENT ON COLUMN "public"."t_user"."license_number" IS '证件号码';
COMMENT ON COLUMN "public"."t_user"."school" IS '学校';

-- ----------------------------
-- Table structure for t_wx_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_wx_user";
CREATE TABLE "public"."t_wx_user" (
  "id" int8 NOT NULL DEFAULT nextval('lev_wx_user_id_seq'::regclass),
  "openid" varchar(28) COLLATE "pg_catalog"."default",
  "nickname" varchar(100) COLLATE "pg_catalog"."default",
  "avatar_url" varchar(2000) COLLATE "pg_catalog"."default",
  "gender" int2,
  "country" varchar(100) COLLATE "pg_catalog"."default",
  "province" varchar(100) COLLATE "pg_catalog"."default",
  "city" varchar(100) COLLATE "pg_catalog"."default",
  "language" varchar(100) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6),
  "mobile" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_wx_user"."openid" IS '小程序用户的openid';
COMMENT ON COLUMN "public"."t_wx_user"."nickname" IS '用户昵称';
COMMENT ON COLUMN "public"."t_wx_user"."avatar_url" IS '用户头像';
COMMENT ON COLUMN "public"."t_wx_user"."gender" IS '性别 0-男、1-女';
COMMENT ON COLUMN "public"."t_wx_user"."country" IS '所在国家';
COMMENT ON COLUMN "public"."t_wx_user"."province" IS '省份';
COMMENT ON COLUMN "public"."t_wx_user"."city" IS '城市';
COMMENT ON COLUMN "public"."t_wx_user"."language" IS '语种';
COMMENT ON COLUMN "public"."t_wx_user"."create_time" IS '创建/注册时间';
COMMENT ON COLUMN "public"."t_wx_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_wx_user"."mobile" IS '手机号码';
COMMENT ON TABLE "public"."t_wx_user" IS '用户信息表';

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."lev_wx_user_id_seq"
OWNED BY "public"."t_wx_user"."id";
SELECT setval('"public"."lev_wx_user_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "public"."t_user" ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("user_id");

-- ----------------------------
-- Checks structure for table t_wx_user
-- ----------------------------
ALTER TABLE "public"."t_wx_user" ADD CONSTRAINT "lev_wx_user_gender_check" CHECK (gender = ANY (ARRAY[0, 1]));

-- ----------------------------
-- Primary Key structure for table t_wx_user
-- ----------------------------
ALTER TABLE "public"."t_wx_user" ADD CONSTRAINT "lev_wx_user_pkey" PRIMARY KEY ("id");
