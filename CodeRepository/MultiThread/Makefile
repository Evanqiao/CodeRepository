# Makefile for Linux etc.

.PHONY: all clean
all: multi-thread

SHELL=/bin/bash
CC=gcc
CFLAGS=-O3 -Wall -std=c99
LDFLAGS=-lpthread

multi-thread: get_remote_control.o main.o
	${CC} $^ ${LDFLAGS} -o $@

clean:
	rm -f *.o  multi-thread 

test: multi-thread
	./multi-thread
