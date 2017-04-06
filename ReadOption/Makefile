# Makefile for Linux etc.

.PHONY: all clean
all: read-option

SHELL=/bin/bash
CC=gcc
CFLAGS=-O3 -Wall -std=c99
#LDFLAGS=-lm -fopenmp -pthread

read-option: getopt.o main.o
	${CC} $^  -o $@

clean:
	rm -f *.o read-option *.bin

test: read-option
	./read-option -e brdc0140.16n -o my_fifo -b 16 -s 2500000 -l 31.227601,121.402341,100
