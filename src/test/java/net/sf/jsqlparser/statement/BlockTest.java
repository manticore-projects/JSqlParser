/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import net.sf.jsqlparser.test.TestUtils;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Tobias Warneke (t.warneke@gmx.net)
 */
public class BlockTest {

    /**
     * Test of getStatements method, of class Block.
     */
    @Test
    public void testGetStatements() throws JSQLParserException {
        Statements stmts = CCJSqlParserUtil.parseStatements("begin\nselect * from feature;\nend");
        assertEquals("BEGIN\n"
                + "SELECT * FROM feature;\n"
                + "END;\n", stmts.toString());
    }

    @Test
    public void testBlock2() throws JSQLParserException {
        Statements stmts = CCJSqlParserUtil.parseStatements("begin\n"
                + "update table1 set a = 'xx' where b = 'condition1';\n"
                + "update table1 set a = 'xx' where b = 'condition2';\n"
                + "end;");
        assertEquals("BEGIN\n"
                + "UPDATE table1 SET a = 'xx' WHERE b = 'condition1';\n"
                + "UPDATE table1 SET a = 'xx' WHERE b = 'condition2';\n"
                + "END;\n"
                + "", stmts.toString());

    }

    @Test
    public void testBlock3() throws JSQLParserException {
        Statements stmts = CCJSqlParserUtil.parseStatements("begin\nselect * from feature;\nend");
        Block block = (Block) stmts.getStatements().get(0);
        assertFalse(block.getStatements().getStatements().isEmpty());
    }

    @Test
    public void testBlockToStringIsNullSafe() throws JSQLParserException {
        Block block = new Block();
        block.setStatements(null);
        assertEquals("BEGIN\n"
                + "END", block.toString());
    }

    @Test
    public void testDoBlocks() throws JSQLParserException {
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT \"a\" from dual", true);
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT $$a$$ from dual", true);
        TestUtils.assertSqlCanBeParsedAndDeparsed("SELECT $a$ from dual", true);

        String sqlStr="DO $$DECLARE r record;\n" +
                "BEGIN\n" +
                "    FOR r IN SELECT table_schema, table_name FROM information_schema.tables\n" +
                "             WHERE table_type = 'VIEW' AND table_schema = 'public'\n" +
                "    LOOP\n" +
                "        EXECUTE 'GRANT ALL ON ' || quote_ident(r.table_schema) || '.' || quote_ident(r.table_name) || ' TO webuser';\n" +
                "    END LOOP;\n" +
                "END$$;";


        sqlStr = "DO $$\n" +
                "    DECLARE example_var varchar := 'something-something';\n" +
                "    BEGIN\n" +
                "            INSERT INTO country(code) VALUES ('EE');\n" +
                "    END $$";

        Statements statements = CCJSqlParserUtil.parseStatements(sqlStr);
        assertEquals(1, statements.getStatements().size());

        DoBlock block = (DoBlock) statements.getStatements().get(0);

        TestUtils.assertSqlCanBeParsedAndDeparsed(sqlStr, true);

    }

}
