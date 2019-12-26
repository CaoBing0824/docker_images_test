/* 统一信用代码修改为非必填 */
ALTER TABLE t_open_api_ent_info MODIFY credit_code VARCHAR(18) DEFAULT NULL;