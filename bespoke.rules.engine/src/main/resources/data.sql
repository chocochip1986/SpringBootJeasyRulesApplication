INSERT INTO DISBURSEMENT_SCHEME_CONFIG (id) VALUES
(1);


INSERT INTO RULE (id, disbursement_scheme_config_id, sequence, description, deleted) VALUES
(1, 1, 1, 'First rule', FALSE),
(2, 1, 1, 'Deleted rule', TRUE),
(3, 1, 1, 'Third rule', FALSE),
(4, 1, 1, 'Fourth rule', FALSE);


INSERT INTO CRITERION (id, sequence, rule_id, description, deleted) VALUES
(1, 1, 1, 'Criterion 1', FALSE),
(2, 1, 2, 'Criterion 2', TRUE),
(3, 1, 4, 'Criterion 3', FALSE),
(4, 1, 3, 'Criterion 4', FALSE),
(5, 1, 2, 'Criterion 5', FALSE),
(6, 1, 4, 'Criterion 6', TRUE),
(7, 1, 3, 'Criterion 7', FALSE),
(8, 1, 1, 'Criterion 8', TRUE);


INSERT INTO CONDITION (id, sequence, criterion_id, parameter, operator, value, as_of, pass, deleted) VALUES
(1, 1, 4, 'birthDate', 'SMALLER_THAN', CAST(CURRENT_TIMESTAMP(0) AS VARCHAR(255)), CURRENT_TIMESTAMP(0), TRUE, FALSE),
(2, 1, 1, 'deathDate', 'EQUAL_TO', 'NULL', CURRENT_TIMESTAMP(0), TRUE, FALSE),
(3, 1, 5, 'income', 'GREATER_THAN', '1000000', CURRENT_TIMESTAMP(0), TRUE, TRUE),
(4, 1, 6, 'income', 'SMALLER_THAN', '200000', CURRENT_TIMESTAMP(0), TRUE, FALSE),
(5, 1, 3, 'income', 'SMALLER_THAN', '10000', CURRENT_TIMESTAMP(0), TRUE, FALSE),
(6, 1, 7, 'birthDate', 'GREATER_THAN', CAST(CURRENT_TIMESTAMP(0) AS VARCHAR(255)), CURRENT_TIMESTAMP(0), TRUE, FALSE),
(7, 1, 4, 'nationality', 'EQUAL_TO', 'Singaporean', CURRENT_TIMESTAMP(0), TRUE, FALSE),
(8, 1, 1, 'citizenshipAttainmentDate', 'SMALLER_THAN', CAST(CURRENT_TIMESTAMP(0) AS VARCHAR(255)), CURRENT_TIMESTAMP(0), TRUE, FALSE),
(9, 1, 7, 'citizenshipAttainmentDate', 'EQUAL_TO', 'NULL', CURRENT_TIMESTAMP(0), TRUE, FALSE),
(10, 1, 6, 'income', 'GREATER_THAN', '1000', CURRENT_TIMESTAMP(0), TRUE, TRUE);


INSERT INTO PERSON (id, name, nric, nationality, birth_date, death_date, citizenship_attainment_date, income) VALUES
(1, 'john', 'T1234567C', 'Singaporean', FORMATDATETIME(CURRENT_TIMESTAMP(), 'Y-MM-01'), NULL, FORMATDATETIME(CURRENT_TIMESTAMP(), 'Y-MM-01'), 0),
(2, 'doe', 'T78542367C', 'Singaporean', FORMATDATETIME(DATEADD(yy,-2,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, FORMATDATETIME(DATEADD(yy,-2,CURRENT_TIMESTAMP) ,'Y-MM-01'), 0),
(3, 'mary', NULL, 'Indonesian', FORMATDATETIME(DATEADD(yy,-90,CURRENT_TIMESTAMP) ,'Y-MM-01'), FORMATDATETIME(DATEADD(yy,-2,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, 0),
(4, 'tim', NULL, 'Japanese', FORMATDATETIME(DATEADD(yy,-20,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, NULL, 5000000),
(5, 'jerry', NULL, 'Korean', FORMATDATETIME(DATEADD(yy,-56,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, NULL, 12358),
(6, 'beth', 'S7182458J', 'Singaporean', FORMATDATETIME(DATEADD(yy,-51,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, FORMATDATETIME(DATEADD(yy,-10,CURRENT_TIMESTAMP) ,'Y-MM-01'), 89631),
(7, 'liz', 'T1234567C', 'Singaporean', FORMATDATETIME(DATEADD(yy,-19,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, FORMATDATETIME(DATEADD(yy,-19,CURRENT_TIMESTAMP) ,'Y-MM-01'), 2000),
(8, 'shawn', NULL, 'American', FORMATDATETIME(DATEADD(yy,-30, CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, NULL, 412386),
(9, 'shaun', 'S9764275C', 'Singaporean', FORMATDATETIME(DATEADD(yy,-50,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, FORMATDATETIME(DATEADD(mm,-10,CURRENT_TIMESTAMP) ,'Y-MM-01'), 20000),
(10, 'lizzy', NULL, 'British', FORMATDATETIME(DATEADD(yy,-86,CURRENT_TIMESTAMP) ,'Y-MM-01'), NULL, NULL, 10000);