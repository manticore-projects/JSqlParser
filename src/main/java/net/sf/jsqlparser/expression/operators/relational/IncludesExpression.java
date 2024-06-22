/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression.operators.relational;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

public class IncludesExpression extends ASTNodeAccessImpl
        implements Expression {

    private Expression leftExpression;
    private Expression rightExpression;

    public IncludesExpression() {}

    public IncludesExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public IncludesExpression withLeftExpression(Expression expression) {
        this.setLeftExpression(expression);
        return this;
    }

    public final void setLeftExpression(Expression expression) {
        leftExpression = expression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }

    @Override
    public <T, S> T accept(ExpressionVisitor<T> expressionVisitor, S arguments) {
        return expressionVisitor.visit(this, arguments);
    }

    @Override
    public String toString() {
        StringBuilder statementBuilder = new StringBuilder();
        statementBuilder.append(leftExpression);

        statementBuilder.append(" ");
        statementBuilder.append("INCLUDES ");

        statementBuilder.append(rightExpression);
        return statementBuilder.toString();
    }

    public IncludesExpression withRightExpression(Expression rightExpression) {
        this.setRightExpression(rightExpression);
        return this;
    }

    public <E extends Expression> E getLeftExpression(Class<E> type) {
        return type.cast(getLeftExpression());
    }

    public <E extends Expression> E getRightExpression(Class<E> type) {
        return type.cast(getRightExpression());
    }
}
