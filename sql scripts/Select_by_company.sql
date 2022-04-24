CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'Company 1');
INSERT INTO company (id, name) VALUES (2, 'Company 2');
INSERT INTO company (id, name) VALUES (3, 'Company 3');
INSERT INTO company (id, name) VALUES (4, 'Company 4');
INSERT INTO company (id, name) VALUES (5, 'Company 5');

INSERT INTO person (id, name, company_id) VALUES (1, 'Person 1', 5);
INSERT INTO person (id, name, company_id) VALUES (2, 'Person 2', 5);
INSERT INTO person (id, name, company_id) VALUES (3, 'Person 3', 4);
INSERT INTO person (id, name, company_id) VALUES (4, 'Person 4', 4);
INSERT INTO person (id, name, company_id) VALUES (5, 'Person 5', 3);
INSERT INTO person (id, name, company_id) VALUES (6, 'Person 6', 3);
INSERT INTO person (id, name, company_id) VALUES (7, 'Person 7', 2);
INSERT INTO person (id, name, company_id) VALUES (8, 'Person 8', 2);
INSERT INTO person (id, name, company_id) VALUES (9, 'Person 9', 1);
INSERT INTO person (id, name, company_id) VALUES (10, 'Person 10', 1);
INSERT INTO person (id, name, company_id) VALUES (11, 'Person 11', 5);

SELECT p.name as Имя, c.name as Компания FROM person as p
join company as c
ON p.company_id = c.id
WHERE p.company_id NOT IN (5);

SELECT c.name as name, COUNT(p.name) AS counts
FROM person as p
join company as c
ON p.company_id = c.id
GROUP BY c.name
having count(p.name) = (
	select count(*)
	from person as p
	GROUP BY p.company_id
	order by count(p.company_id) desc
	limit 1
);