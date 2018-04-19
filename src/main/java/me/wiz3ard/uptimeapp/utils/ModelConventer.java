package me.wiz3ard.uptimeapp.utils;


public interface ModelConventer<E,D> {
    E convertFrom(D model);
    D convertTo(E model);
}
