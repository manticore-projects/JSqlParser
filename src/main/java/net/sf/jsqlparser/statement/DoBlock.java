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

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * refer to https://www.postgresql.org/docs/current/sql-do.html
 */
public class DoBlock implements Statement {
    public static final Pattern codeBlockPattern = Pattern.compile("\\$\\$\\s*((.|\\s)*)\\$\\$");
    private String langName;
    private String code;

    private Statements statements;

    public DoBlock(String langName, String code) {
        this.langName = langName;
        this.code = Objects.requireNonNull(code);
        try {
            Matcher m = codeBlockPattern.matcher(this.code);
            if (m.matches() && m.groupCount()>1) {
                this.statements = CCJSqlParserUtil.parseStatements(m.group(1));
            }
        } catch (Exception ignore) {

        }
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws JSQLParserException {
        this.code = Objects.requireNonNull(code);
        Matcher m = codeBlockPattern.matcher(this.code);
        if (m.matches() && m.groupCount()>1) {
            this.statements = CCJSqlParserUtil.parseStatements(m.group(1));
        }
    }

    public Statements getStatements() {
        return statements;
    }

    public void setStatements(Statements statements) {
        this.statements = statements;
        this.code = statements.toString();
    }

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public StringBuilder appendTo(StringBuilder builder) {
        builder.append("DO");
        if (langName!=null && !langName.isEmpty()) {
            builder.append(" LANGUAGE").append(langName);
        }
        //builder.append(" $$").append( statements!=null ? statements.toString() : code ).append( "$$");
        builder.append(" ").append( statements!=null ? statements.toString() : code );

        return builder;
    };


    @Override
    public String toString() {
        return appendTo(new StringBuilder()).toString();
    }

}
