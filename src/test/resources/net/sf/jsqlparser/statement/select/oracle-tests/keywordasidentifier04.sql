---
-- #%L
-- JSQLParser library
-- %%
-- Copyright (C) 2004 - 2019 JSQLParser
-- %%
-- Dual licensed under GNU LGPL 2.1 or Apache License 2.0
-- #L%
---
(select bs.keep keep, bs.keep_until keep_until 
from v$backup_set bs) 
union all 
(select null keep, null keep_until
from v$backup_piece bp)


--@FAILURE: Encountered unexpected token: "." "." recorded first on Aug 3, 2021, 7:20:08 AM
--@SUCCESSFULLY_PARSED_AND_DEPARSED first on 18 Oct 2021, 19:59:28