ALTER TABLE tb_user_usuario_gs_2sem
    ADD data_aniversario_usuario DATE;

MERGE INTO tb_user_usuario_gs_2sem T_FILHA
    USING tb_user_gs_2sem T_PAI
    ON (T_FILHA.id_usuario = T_PAI.id)
    WHEN MATCHED THEN
        UPDATE SET T_FILHA.data_aniversario_usuario = T_PAI.data_aniversario_usuario;

ALTER TABLE tb_user_usuario_gs_2sem
    MODIFY data_aniversario_usuario DATE NOT NULL;

ALTER TABLE tb_user_gs_2sem
DROP COLUMN data_aniversario_usuario;