---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
select customer_id, cust_address_ntab
multiset intersect all cust_address2_ntab multiset_intersect
from customers_demo
order by customer_id

--@FAILURE: Encountered unexpected token: "intersect" "INTERSECT" recorded first on Aug 3, 2021, 7:20:08 AM
--@FAILURE: select customer_id,cust_address_ntab multiset recorded first on 10 Jun 2022, 15:49:34