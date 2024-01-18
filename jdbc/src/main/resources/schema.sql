DROP TABLE IF EXISTS "students";

DROP SEQUENCE IF EXISTS students_id_seq;
CREATE SEQUENCE students_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "students" (
    "id" integer  DEFAULT nextval('students_id_seq') NOT NULL,
    "name" text,
    "country" text,
    "age" integer,
    CONSTRAINT "students_pkey" PRIMARY KEY ("id")
);