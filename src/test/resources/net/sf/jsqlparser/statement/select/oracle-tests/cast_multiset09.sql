---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
update customers_demo
set cust_address_ntab = cust_address_ntab multiset union cust_address_ntab

--@FAILURE: Encountered unexpected token: "multiset" <S_IDENTIFIER> recorded first on Aug 3, 2021, 7:20:08 AM
--@FAILURE: update customers_demo set cust_address_ntab=cust_address_ntab recorded first on 10 Jun 2022, 15:49:34