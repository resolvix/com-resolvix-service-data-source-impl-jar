package com.resolvix.service.datasource.base.proxy;

import java.sql.SQLException;

public abstract class BaseStaticProxyImpl {

    @FunctionalInterface
    protected interface VoidNoArgumentMethod<E extends SQLException> {

        void apply() throws E;
    }

    @FunctionalInterface
    protected interface VoidOneArgumentMethod<E extends SQLException, U> {

        void accept(U u) throws E;
    }

    @FunctionalInterface
    protected interface VoidTwoArgumentMethod<E extends SQLException, U, V> {

        void accept(U u, V v) throws E;
    }

    @FunctionalInterface
    protected interface VoidThreeArgumentMethod<E extends SQLException, U, V, W> {

        void accept(U u, V v, W w) throws E;
    }

    @FunctionalInterface
    protected interface VoidFourArgumentMethod<E extends SQLException, U, V, W, X> {

        void accept(U u, V v, W w, X x) throws E;
    }

    @FunctionalInterface
    protected interface SupplierMethod<R, E extends SQLException> {

        R apply() throws E;
    }

    @FunctionalInterface
    protected interface OneArgumentFunction<R, E extends SQLException, U>  {

        R apply(U arg) throws E;
    }

    @FunctionalInterface
    protected interface TwoArgumentFunction<R, E extends SQLException, U, V> {

        R apply(U u, V v) throws E;
    }

    @FunctionalInterface
    protected interface ThreeArgumentFunction<R, E extends SQLException, U, V, W> {

        R apply(U u, V v, W w) throws E;
    }

    @FunctionalInterface
    protected interface FourArgumentFunction<R, E extends SQLException, U, V, W, X> {

        R apply(U u, V v, W w, X x) throws E;
    }

    protected <E extends SQLException> void invoke(
        Class<E> classE, VoidNoArgumentMethod<E> callable) throws E {
        callable.apply();
    }

    protected <E extends SQLException, U> void invoke(
        Class<E> classE, VoidOneArgumentMethod<E, U> callable,
        Class<U> classU, U u) throws E {
        callable.accept(u);
    }

    protected <E extends SQLException, U, V> void invoke(
        Class<E> classE, VoidTwoArgumentMethod<E, U, V> callable,
        Class<U> classU, U u, Class<V> classV, V v)
        throws E {
        callable.accept(u, v);
    }

    protected <E extends SQLException, U, V, W> void invoke(
        Class<E> classE, VoidThreeArgumentMethod<E, U, V, W> callable,
        Class<U> classU, U u, Class<V> classV, V v, Class<W> classW, W w)
        throws E {
        callable.accept(u, v, w);
    }

    protected <E extends SQLException, U, V, W, X> void invoke(
        Class<E> classE, VoidFourArgumentMethod<E, U, V, W, X> callable,
        Class<U> classU, U u, Class<V> classV, V v,
        Class<W> classW, W w, Class<X> classX, X x)
        throws E {
        callable.accept(u, v, w, x);
    }

    protected <R, E extends SQLException> R invoke(
        Class<R> classR, Class<E> classE, SupplierMethod<R, E> callable) throws E {
        return callable.apply();
    }

    protected abstract void handleSqlException(SQLException e);

    protected <R, E extends SQLException, V> R invoke(
        Class<R> classR, Class<E> classE, OneArgumentFunction<R, E, V> callable,
        Class<V> classV, V v) throws E {
        try {
            return callable.apply(v);
        } catch (SQLException e) {
            handleSqlException(e);
            throw e;
        }
    }

    protected <R, E extends SQLException, U, V> R invoke(
        Class<R> classR, Class<E> classE, TwoArgumentFunction<R, E, U, V> callableV,
        Class<U> classU, U argU, Class<V> classV, V argV) throws E {
        return callableV.apply(argU, argV);
    }

    protected <R, E extends SQLException, U, V, W> R invoke(
        Class<R> classR, Class<E> classE, ThreeArgumentFunction<R, E, U, V, W> callable,
        Class<U> classU, U u, Class<V> classV, V v, Class<W> classW, W w) throws E {
        return callable.apply(u, v, w);
    }

    protected <R, E extends SQLException, U, V, W, X> R invoke(
        Class<R> classR, Class<E> classE, FourArgumentFunction<R, E, U, V, W, X> callable,
        Class<U> classU, U u, Class<V> classV, V v,
        Class<W> classW, W w, Class<X> classX, X x) throws E {
        return callable.apply(u, v, w, x);
    }
}
