# winter-database-query

## Complex Select

```
SELECT DISTINCT table1.column1, COUNT(1), SUM(table1.column1), MAX(table2.column2), MIN(table2.column2), AVG(table3.column3)
FROM table1 LEFT JOIN table2 ON table1.column1 = table2.column2 LEFT JOIN table3 ON table1.column1 = table3.column3
WHERE table1.column1 = :parameterName AND table2.column2 IS NOT NULL OR (table3.column3 >= :parameterName AND table3.column3 <= :parameterName)
GROUP BY table1.column1, table2.column2 HAVING SUM(table1.column1) >= :parameterName AND MAX(table2.column2) <= :parameterName
ORDER BY SUM(table1.column1), MAX(table2.column2) ASC
LIMIT offset, limit
```
