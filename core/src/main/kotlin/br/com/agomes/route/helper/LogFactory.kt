package br.com.agomes.route.helper

inline fun <reified T> loggerFor(): org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(T::class.java)
