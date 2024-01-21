package br.com.agomes.route.helper

import org.slf4j.LoggerFactory

inline fun <reified T> loggerFor(): org.slf4j.Logger = LoggerFactory.getLogger(T::class.java)
