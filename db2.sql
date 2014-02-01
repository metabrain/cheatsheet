-- SELECT TUPLES FROM A TABLE

SELECT DISTINCT ID
 FROM EMPLOYEE
 WHERE COUNTRY = 'UK'
	 AND AGE < 30
 ORDER BY SALARY

SELECT COUNT(*)
 FROM GEODB
 WHERE GEO_LATITUDE < 30 

SELECT ID, LASTNAME, SALARY
 FROM EMPLOYEE
 WHERE LASTNAME IN ('Hernandez', 'Jones', 'Roberts', 'Ruiz')
;

SELECT ID, LASTNAME, AGE
 FROM EMPLOYEE
 WHERE AGE BETWEEN 30 AND 40
;

-- SUBQUERIES
SELECT NAME
 FROM  EMPLOYEE
 WHERE SALARY > (SELECT AVG(SALARY) FROM EMPLOYEE)
 ORDER BY SALARY;

-- GROUP BY 
--   ALL COLUMNS IN SELECT OUTSIDE AN AGGREGATE FUNCTION 
--		HAVE TO BE ON THE GROUP BY CLAUSE !!
--   (AVG,SUM,COUNT,COUNT(*),COUNT,MIN,MAX) 

SELECT GEO_EUROPE, COUNT(*) AS NUM_OF_PLACES
 FROM GEODB
 GROUP BY GEO_EUROPE
;

SELECT C_NAME, COUNT(L_PARTKEY) NO_PARTS
 FROM LINEITEM, ORDERS, CUSTOMER
 WHERE L_ORDERKEY = O_ORDERKEY
	 AND O_CUSTKEY = C_CUSTKEY
	 AND C_CUSTKEY = 5
 GROUP BY C_NAME

SELECT L_SUPPKEY, L_PARTKEY, SUM(L_QUANTITY) AS QUANTITY
 FROM LINEITEM
 WHERE L_PARTKEY = 5
 AND L_SUPPKEY = 6
 GROUP BY L_SUPPKEY, L_PARTKEY
;

SELECT Country, COUNT(*)
 FROM Customers
 GROUP BY Country
 ORDER BY COUNT(*)  DESC
;

-- CONDITIONS ON AGGREGATE FUNCTIONS USING 'HAVING'

SELECT DEPT, AVG(SALARY)
 FROM EMPLOYEE
 GROUP BY DEPT
 HAVING AVG(SALARY) > 20000
;

-- JOINING TABLES FOR DATA EXTRACTION

SELECT BOOK.TITLE AS TITLE, COUNT(*) AS AUTHORS
 FROM  BOOK
 JOIN  BOOK_AUTHOR
   ON  BOOK.ISBN = BOOK_AUTHOR.ISBN
 GROUP BY BOOK.TITLE
;
/* OR... */
SELECT BOOK.TITLE AS TITLE, COUNT(*) AS AUTHORS
 FROM  BOOK, BOOK_AUTHOR
 WHERE BOOK.ISBN = BOOK_AUTHOR.ISBN
 GROUP BY BOOK.TITLE
;

-- if ISBN is the only column name in common, natural join is enough
SELECT TITLE AS TITLE, COUNT(*) AS AUTHORS
 FROM  BOOK
NATURAL JOIN  BOOK_AUTHOR
 GROUP BY TITLE
;

-- INSERT INTO A TABLE

INSERT INTO NATION (N_NATIONKEY, N_NAME, N_REGIONKEY, N_COMMENT)
VALUES (25, 'SPAIN', 3, 'test entry')
;

-- UPDATE DATA FROM A TABLE
UPDATE EMPLOYEE
 SET SALARY = 
 	(SELECT ROUND(AVG(SALARY)) FROM EMPLOYEE)
 WHERE NAME = 'Gus' AND LASTNAME = 'Frings'
;

-- DELETE FROM A TABLE
DELETE FROM NATION
WHERE N_NAME = 'SPAIN'

-- CREATE INDEXES

CREATE INDEX idx_name 
ON TABLE_NAME (COLUMN_NAME)
;

















SELECT L_SUPPKEY, L_PARTKEY, SUM(L_QUANTITY) AS QUANTITY
FROM LINEITEM
WHERE L_PARTKEY = 5
AND L_SUPPKEY = 6
GROUP BY L_SUPPKEY, L_PARTKEY
;
-- Nothing to point, just a normal TBSCAN and then a GRPBY to group
--	the results attained.



SELECT RC_NAME AS CUSTOMER_REGION,
	SUM(L_EXTENDEDPRICE*(1-L_DISCOUNT)) AS TURNOVER
FROM LINEITEM_FULL
GROUP BY RC_NAME
;

-- The relations used are REGION, NATION, CUSTOMER, ORDERS and LINEITEM.
-- The attribute used by the SORT operator is R_NAME.
-- The SORT is there for various reasons, one of the is to improve the speed
--	of the query since it's faster to sum groups of rows (since they are
--	ordered, the same RC_NAMEs will be next to each other and this can
--	be used to paralelize the summing of the TUROVERS) than to traverse
--	the table several times to sum all the different CUSTOMER_REGIONS.


DROP TABLE TURNOVER_MAT ;
CREATE TABLE TURNOVER_MAT AS (
	SELECT RC_NAME, RS_NAME, P_TYPE,
		SUM  (L_EXTENDEDPRICE*(1-L_DISCOUNT)) AS TURNOVER,
		COUNT(*) AS COUNT
	FROM LINEITEM_FULL
	GROUP BY RC_NAME, RS_NAME, P_TYPE
) DATA INITIALLY DEFERRED REFRESH IMMEDIATE
ENABLE QUERY OPTIMIZATION
MAINTAINED BY SYSTEM
;
REFRESH TABLE TURNOVER_MAT ;

CREATE TABLE NATION_MAT AS (
	SELECT N_NATIONKEY
	FROM NATION
)
DATA INITIALLY DEFERRED REFRESH DEFERRED
MAINTAINED BY SYSTEM
;


SELECT N_NAME 
FROM NATION
WHERE N_NAME LIKE 'A%'
;




